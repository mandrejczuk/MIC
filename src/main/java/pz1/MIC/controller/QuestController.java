package pz1.MIC.controller;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.controller.request.IdHolder;
import pz1.MIC.controller.request.StartTimeAdventureRequest;
import pz1.MIC.controller.request.StartTimeGuardRequest;
import pz1.MIC.dto.QuestDto;
import pz1.MIC.model.*;
import pz1.MIC.model.Character;
import pz1.MIC.service.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;
    private final UserDetailsService userDetailsService;
    private final CharacterService characterService;
    private final ModelMapper mapper;
    private final MonsterService monsterService;
    private final MonsterDefinitionService monsterDefinitionService;
    private final StatsService statsService;



    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id){
        questService.delete(id);
    }

    @PostMapping
    public Quest addQuest(@RequestBody Quest quest){
        return questService.add(quest);
    }

    @GetMapping("{id}")
    public Quest getQuest(@PathVariable Long id)
    {
        return questService.show(id);
    }

    @PutMapping("/guard")
    public QuestDto updateGuardQuest(Principal principal)
    {
        //przy tworzeniu postaci tworzy sie warta i za kazdym razem gdy postac chce isc na warte prize(gold) ponownie się przelicza w zaleznosci od expa postaci
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);
        Quest quest = questService.findGuardByCharacter(character);
        Long gold = 100L +((character.getStats().getExp())/100L);
        quest.setPrize(gold);
        questService.save(quest);
        return mapper.map(quest,QuestDto.class);
    }

    @PutMapping("/guardStart")
    public LocalDateTime setStartTime(@RequestBody StartTimeGuardRequest localDateTime, Principal principal)
    {

        //json example
        //{
        //  "startDateTime": "2028-02-28 22:05:59"
        //}
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);
        Quest quest = questService.findGuardByCharacter(character);
       quest.setStartDateTime(localDateTime.getStartDateTime());
        questService.save(quest);
        System.out.println(localDateTime.toString());
        System.out.println(localDateTime.getStartDateTime());
        return quest.getStartDateTime();

    }

    @PostMapping("/adventure")
    public QuestDto postAdventureQuest(Principal principal)
    {
        //w momencie kl
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);
        //random monster def
        MonsterDefinition monsterDefinition = monsterDefinitionService.randomDefMonster();

        //generate stats based on user lvl

        Stats stats = statsService.createMonsterStatsBasedOnCharExp(monsterDefinition.getBaseStats(),character);

        //create monster
        Monster monster = monsterService.addMonster(monsterDefinition,stats);
        //create quest
        Quest quest = questService.createDailyQuest(character,monster);
        return mapper.map(quest,QuestDto.class);
    }

    @PutMapping("/adventureSetTime")
    public QuestDto setStartTimeAdventureQuest(@RequestBody StartTimeAdventureRequest startTimeAdventureRequest)
    {
        //json example
//        {
//            "startDateTime": "2028-04-12 18:15:51",
//                "id": 45
//        }
        Quest quest = questService.show(startTimeAdventureRequest.getId());
        quest.setStartDateTime(startTimeAdventureRequest.getStartDateTime());
        questService.save(quest);
        return mapper.map(quest,QuestDto.class);
    }

    @DeleteMapping("/endAdventureQuest")
    public void endAdventureQuest(@RequestBody IdHolder idHolder)
    {
        Quest quest = questService.show(idHolder.getId());
        Character character = quest.getCharacter();
        character.getStats().setExp(quest.getExp()+character.getStats().getExp());
        character.getStats().setGold(character.getStats().getGold() + quest.getPrize());
        characterService.save(character);
        questService.delete(quest.getId());
    }
    @PutMapping("/endGuardQuest")
    public void endGuardQuest(Principal principal)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character character = characterService.findbyuserid(userObj);
        Quest quest = questService.findGuardByCharacter(character);
        character.getStats().setExp(quest.getExp()+character.getStats().getExp());
        character.getStats().setGold(character.getStats().getGold() + quest.getPrize());
        quest.setStartDateTime(null);
        characterService.save(character);

    }



}
