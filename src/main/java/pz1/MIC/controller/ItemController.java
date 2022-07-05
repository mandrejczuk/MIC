package pz1.MIC.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.controller.request.IdHolder;
import pz1.MIC.controller.request.SellItemRequest;
import pz1.MIC.dto.ItemDto;
import pz1.MIC.model.Character;
import pz1.MIC.model.Item;
import pz1.MIC.model.Stats;
import pz1.MIC.model.User;
import pz1.MIC.repository.CharacterRepository;
import pz1.MIC.repository.ItemRepository;
import pz1.MIC.repository.StatsRepository;
import pz1.MIC.service.CharacterService;
import pz1.MIC.service.ItemService;
import pz1.MIC.service.StatsService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
@Builder
@RequiredArgsConstructor

public class ItemController {

    private final ItemService itemService;
    private final UserDetailsService userDetailsService;
    private final ModelMapper mapper;
    private final CharacterService characterService;
    private final StatsService statsService;




    @DeleteMapping
    public void delete(@RequestBody Long id){
        itemService.delete(id);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item)
    {
        return itemService.add(item);
    }
    @GetMapping("{id}")
    public ItemDto getItem(@PathVariable Long id)
    {
        return mapper.map(itemService.show(id), ItemDto.class);
    }

    @ApiOperation(value = "Delete item by id" , notes = "Based on item value add gold to character")
    @DeleteMapping("/sell")
    public void sellItem(@ApiParam(value = "idItem") @RequestBody SellItemRequest sellItemRequest, Principal principal){ // id item w  requescie
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Item item = itemService.show(sellItemRequest.getIdItem().getId());
        Character character = characterService.findbyuserid(userObj);
        Stats stats = character.getStats();
        stats.setGold(stats.getGold() + item.getPriceSell());
        itemService.delete(sellItemRequest.getIdItem().getId());
    }
    @ApiOperation(value = "Return list of  new items", notes = "Generate a list of items based on character level")
    @GetMapping("/shop")
    public List <ItemDto> generateShop(Principal principal)
    {
        List<Item> itemList = new ArrayList<Item>();
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));
        itemList.add(itemService.generateRandomItemBasedOnUserLvl(userObj));

        return itemList
                .stream()
                .map(i -> mapper.map(i, ItemDto.class))
                .collect(Collectors.toList());

    }

    @ApiOperation(value = "Update item's owner" , notes = "Updating characterEquipmentId in item to current logged character's characterEquipmentId, checks if character has required amount of gold to obtain item, recalculate stats after buying")
    @PutMapping("/buy/{id}")
    public ItemDto buyItem(@ApiParam(value = "idItem") @PathVariable Long id,Principal principal)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Item item = itemService.show(id);
        Character character = characterService.findbyuserid(userObj);
        Stats stats = character.getStats();;
        if (stats.getGold() - item.getPriceBuy() < 0)
        {
            throw new ArithmeticException("Masz za malo golda aby kupic item");
        }
        else
        {
            stats.setGold(stats.getGold() -  item.getPriceBuy());
            item.setCharacterEquipment(character.getCharacterEquipment());

        }
        characterService.add(character);
        statsService.add(stats);
        itemService.add(item);

        return mapper.map(item, ItemDto.class);
    }

}
