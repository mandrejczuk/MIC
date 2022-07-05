package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.Character;
import pz1.MIC.model.Monster;
import pz1.MIC.model.Quest;
import pz1.MIC.repository.QuestRepository;

import java.security.Principal;

@Service
public class QuestService extends GenericManagementService<Quest, QuestRepository> {
    public QuestService(QuestRepository repository) {
        super(repository);
    }

    public Quest findGuardByCharacter(Character character)
    {
        String desc = "Town is no longer safe. Town hall is recruiting brave adventurers to guard residents. Payment depends on your experience and time.";
        Quest quest = repository.findAllByCharacterEqualsAndDescriptionEquals(character,desc);
        return quest;
    }
    public Quest save(Quest quest)
    {
        return repository.save(quest);
    }

    public Quest createGuardQuest (Character character)
    {
        return  Quest.builder()
                .character(character)
                .description("Town is no longer safe. Town hall is recruiting brave adventurers to guard residents. Payment depends on your experience and time.")
                .exp(0L)
                .duration(60L)
                .name("Guard")
                .prize(100L)
                .questRarity(100F)
                .monster(null)
                .startDateTime(null)
                .build();
    }
    public Quest createDailyQuest(Character character, Monster monster)
    {
        return repository.save(
                Quest.builder()
                        .character(character)
                        .description("Defeat monster")
                        .exp(monster.getStats().getExp())
                        .duration(10L)
                        .name("Adventure")
                        .prize(monster.getStats().getGold())
                        .startDateTime(null)
                        .questRarity(10)
                        .monster(monster)
                        .build());
    }
}
