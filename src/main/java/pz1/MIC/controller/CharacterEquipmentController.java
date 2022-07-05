package pz1.MIC.controller;

import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.CharacterEquipment;
import pz1.MIC.service.CharacterEquipmentService;

@RestController
@RequestMapping("/chareq")
public class CharacterEquipmentController {

    private final CharacterEquipmentService characterEquipmentService;

    public CharacterEquipmentController(CharacterEquipmentService characterEquipmentService) {
        this.characterEquipmentService = characterEquipmentService;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        characterEquipmentService.delete(id);
    }

    @PostMapping
    public CharacterEquipment addCharacterEquipment(@RequestBody CharacterEquipment characterEquipment)
    {
        return characterEquipmentService.add(characterEquipment);
    }

    @GetMapping("{id}")
    public CharacterEquipment getCharacterEquipment(@PathVariable Long id)
    {
        return characterEquipmentService.show(id);
    }
}

