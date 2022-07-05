package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.ItemDefinition;
import pz1.MIC.service.ItemDefinitionService;

@RestController
@RequestMapping("/itemdef")
public class ItemDefinitionController {

    private final ItemDefinitionService itemDefinitionService;

    public ItemDefinitionController(ItemDefinitionService itemDefinitionService) {
        this.itemDefinitionService = itemDefinitionService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        itemDefinitionService.delete(id);
    }

    @PostMapping
    public  ItemDefinition addItemDefinition(@RequestBody ItemDefinition itemDefinition){
        return itemDefinitionService.add(itemDefinition);
    }

    @GetMapping("{id}")
    public ItemDefinition getItemDefinition(@PathVariable Long id)
    {
        return itemDefinitionService.show(id);
    }
}
