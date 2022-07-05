package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.MonsterDefinition;
import pz1.MIC.service.MonsterDefinitionService;

@RestController
@RequestMapping("/monsterdef")
public class MonsterDefinitionController {

    private final MonsterDefinitionService monsterDefinitionService;

    public MonsterDefinitionController(MonsterDefinitionService monsterDefinitionService) {
        this.monsterDefinitionService = monsterDefinitionService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        monsterDefinitionService.delete(id);
    }

    @PostMapping
    public MonsterDefinition addMonsterDefinition(@RequestBody MonsterDefinition monsterDefinition)
    {
        return monsterDefinitionService.add(monsterDefinition);
    }

    @GetMapping("{id}")
    public MonsterDefinition getMonsterDefinition(@PathVariable Long id)
    {
        return monsterDefinitionService.show(id);
    }
}
