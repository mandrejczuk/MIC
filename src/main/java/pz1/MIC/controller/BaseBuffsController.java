package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.BaseBuffs;
import pz1.MIC.service.BaseBuffsService;

@RestController
@RequestMapping("/basebuffs")
public class BaseBuffsController {

    private final BaseBuffsService baseBuffsService;

    public BaseBuffsController(BaseBuffsService baseBuffsService) {
        this.baseBuffsService = baseBuffsService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        baseBuffsService.delete(id);
    }

    @PostMapping
    public BaseBuffs addBaseBuffs(@RequestBody BaseBuffs baseBuffs) {
        return baseBuffsService.add(baseBuffs);
    }

    @GetMapping("{id}")
    public BaseBuffs getBaseBuffs(@PathVariable Long id){
        return baseBuffsService.show(id);
    }

}
