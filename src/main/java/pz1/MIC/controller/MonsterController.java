package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.Monster;
import pz1.MIC.service.MonsterService;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    private final MonsterService monsterService;

    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        monsterService.delete(id);
    }

    @PostMapping
    public Monster addMonster(@RequestBody Monster monster)
    {
        return monsterService.add(monster);
    }

    @GetMapping("{id}")
    public Monster getMonster(@PathVariable Long id)
    {
        return monsterService.show(id);
    }
}
