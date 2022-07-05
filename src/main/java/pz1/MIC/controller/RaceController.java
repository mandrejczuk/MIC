package pz1.MIC.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.model.Race;
import pz1.MIC.service.RaceService;

import java.util.List;

@RestController
@RequestMapping("/race")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/all")
    public List<Race> getAll(@RequestParam long id) {

        return raceService.showAll(1, 10).getContent();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        raceService.delete(id);
    }

    @PostMapping
    public Race addRace(@RequestBody Race race){
        return raceService.add(race);
    }

    @GetMapping("{id}")
    public Race getRace(@PathVariable Long id)
    {
        return raceService.show(id);
    }
}
