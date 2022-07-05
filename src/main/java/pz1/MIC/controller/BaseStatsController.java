package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.BaseStats;
import pz1.MIC.service.BaseStatsService;
@RestController
@RequestMapping("/basestats")
public class BaseStatsController {

    private final BaseStatsService baseStatsService;

    public BaseStatsController(BaseStatsService baseStatsService) {
        this.baseStatsService = baseStatsService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        baseStatsService.delete(id);
    }

    @PostMapping
    public BaseStats addBaseStats(@RequestBody BaseStats baseStats)
    {
        return baseStatsService.add(baseStats);
    }

    @GetMapping("{id}")
    public BaseStats getBaseStats(@PathVariable Long id)
    {
        return baseStatsService.show(id);
    }
}
