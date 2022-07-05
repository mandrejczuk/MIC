package pz1.MIC.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.controller.request.CharacterRequest;
import pz1.MIC.controller.request.IdHolder;
import pz1.MIC.dto.CharacterDto;
import pz1.MIC.dto.CharacterEquipmentDto;
import pz1.MIC.dto.CharacterListDto;
import pz1.MIC.dto.StatsDto;
import pz1.MIC.enums.ClassEnum;
import pz1.MIC.model.*;
import pz1.MIC.model.Character;
import pz1.MIC.service.*;

import java.beans.Transient;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final ModelMapper mapper;
    private final UserDetailsService userDetailsService;
    private final RaceService raceService;
    private final ClassService classService;
    private final StatsService statsService;
    private final CharacterEquipmentService characterEquipmentService;
    private final ItemService itemService;
    private final BaseStatsService baseStatsService;
    private final QuestService questService;


    @ApiOperation(value = "Delete character by id", notes = "")
    @DeleteMapping("/delete")
    public void delete(@ApiParam(value = "id") @RequestBody IdHolder idHolder){
        characterService.delete(idHolder.getId());
    }

    @ApiOperation(value = "Create new character", notes = "Create new character based on classId, raceId")
    @PostMapping("/add")
    public CharacterDto addCharacterv2(@RequestBody CharacterRequest request, Principal principal) {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = mapper.map(request, Character.class);
//        character.toBuilder()
//                .user(userObj)
//                .stats(statsService.createStats(request.getClassEnum(), request.getRaceEnum()))
//                .characterEquipment()
//                .freePoints(10)
//                .build();
        character.setUser(userObj);
        character.setStats(statsService.createStats(request.getClassEnum(), request.getRaceEnum()));
        CharacterEquipment characterEquipment = new CharacterEquipment();
        character.setCharacterEquipment(characterEquipmentService.add(characterEquipment));
        characterEquipmentService.createNewEq(characterEquipment,request.getClasses());
        character.setFreePoints(10);


        Quest quest = questService.createGuardQuest(character);
        characterService.add(character);
        questService.save(quest);


        return mapper.map(character, CharacterDto.class) ;
    }
    @ApiOperation(value = "Return a list containing informations about all characters", notes = "Provide information about all characters")
    @GetMapping("/all")
    public List<CharacterListDto> getAllCharacters()
    {
        return characterService.getAll()
                .stream()
                .map(i -> mapper.map(i, CharacterListDto.class))
                .collect(Collectors.toList());

    }

    @ApiOperation(value = "Return current logged character", notes = "Provide information about current logged character based on token")
    @GetMapping
    public CharacterDto getCharacter(Principal principalUser) {
        User userObj = (User) userDetailsService.loadUserByUsername(principalUser.getName());
        Character character = characterService.findbyuserid(userObj);
        return mapper.map(character, CharacterDto.class);
    }
    @ApiOperation(value = "Return a item list that current logged character has")
    @GetMapping("/items")
    public List<CharacterEquipmentDto> getCharacterItems(Principal principal)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character =  characterService.findbyuserid(userObj);
        return itemService.getCharacterItems(character.getCharacterEquipment())
                .stream()
                .map(i -> mapper.map(i, CharacterEquipmentDto.class))
                .collect(Collectors.toList());
    }
    @ApiOperation(value = "Return a item list that current logged character has", notes = "chyba do wywalenia ten endpoint jest taki sam jak getCharacterItems")
    @GetMapping("/shop")
    public List<CharacterEquipmentDto> getCharacterShopItems(Principal principal)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character =  characterService.findbyuserid(userObj);
        return itemService.getCharacterItems(character.getCharacterEquipment())
                .stream()
                .map(i -> mapper.map(i, CharacterEquipmentDto.class))
                .collect(Collectors.toList());
    }
    @ApiOperation(value = "Update class for character and calculate new stats")
    @PutMapping("/classChange")
    public void updateClass(@ApiParam(value = "idNewClass") Principal principal,@RequestBody IdHolder idNewClass)
    {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(user);
        BaseStats currentClassBaseStats = baseStatsService.show(classService.show(character.getClasses().getId()).getId());
        BaseStats newClassBaseStats = baseStatsService.show(classService.show(idNewClass.getId()).getId());
        statsService.updateClassChange(character.getStats(),currentClassBaseStats,newClassBaseStats);
        character.setClasses(classService.show(idNewClass.getId()));
        character = characterService.save(character);



    }



}
