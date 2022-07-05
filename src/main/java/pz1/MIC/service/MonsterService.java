package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.Character;
import pz1.MIC.model.Monster;
import pz1.MIC.model.MonsterDefinition;
import pz1.MIC.model.Stats;
import pz1.MIC.repository.MonsterRepository;

@Service
public class MonsterService extends GenericManagementService<Monster, MonsterRepository> {
    public MonsterService(MonsterRepository repository) {
        super(repository);
    }


    public Monster addMonster(MonsterDefinition monsterDefinition, Stats stats)
    {
        return repository.save(Monster.builder()
                .monsterDefinition(monsterDefinition)
                .id(repository.count()+1)
                .stats(stats)
                .build());
    }
}
