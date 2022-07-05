package pz1.MIC.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.dto.CharacterDto;
import pz1.MIC.dto.StatsDto;
import pz1.MIC.model.Character;
import pz1.MIC.model.Stats;
import pz1.MIC.model.User;
import pz1.MIC.service.CharacterService;
import pz1.MIC.service.StatsService;

import java.security.Principal;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;
    private final ModelMapper mapper;
    private final UserDetailsService userDetailsService;
    private final CharacterService characterService;

//    public StatsController(StatsService statsService) {
//        this.statsService = statsService;
//    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        statsService.delete(id);
    }

    @PostMapping("/add")
    public Stats addStats(@RequestBody Stats stats) {
        return statsService.add(stats);
    }

    @GetMapping("{id}")
    public StatsDto getStats(@PathVariable Long id) {
        return mapper.map(statsService.show(id), StatsDto.class);
    }

    @PutMapping("/update/{p}")
    public StatsDto updateStats(@RequestBody StatsDto statsDto, @PathVariable int p, Principal principal) {

        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);

        Stats stats = character.getStats();
//        System.out.println("DEBUGOWANIE      " +statsDto.getBlockChance() +"     "+ statsDto.getCritDamage());


        if (!calcSum(
                stats, statsDto, character.getFreePoints(), p
                ))
            return statsDto;

        character.setFreePoints(p);
        stats = statsService.update(stats, statsDto);
//        System.out.println();
//        System.out.println("DEBUGOWANIE      " +stats.getBlockChance() +"     "+ stats.getCritDamage());

        return mapper.map(stats, StatsDto.class);
    }

    private boolean calcSum(Stats oldStats, StatsDto newStats, int oldFreePoints, int newFreePoints) {
        Long oldSum = 0L;
        Long newSum = 0L;

        oldSum += oldStats.getAgility() +
                oldStats.getArmour() +
                oldStats.getEndurance() +
                oldStats.getIntelligence() +
                oldStats.getLuck() +
                oldStats.getStrength();

        newSum += newStats.getAgility() +
                newStats.getArmour() +
                newStats.getEndurance() +
                newStats.getIntelligence() +
                newStats.getLuck() +
                newStats.getStrength();

        return (oldSum + oldFreePoints) == (newSum + newFreePoints);
    }

    @PutMapping("/updateexp")
    public  StatsDto updateExp(Principal principal, @RequestBody int exp)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);
        Stats stats = character.getStats();
        stats.setExp(stats.getExp() + Long.valueOf(exp));
        statsService.save(stats);
        return mapper.map(stats, StatsDto.class);
    }
}