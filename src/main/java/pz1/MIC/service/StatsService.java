package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.dto.StatsDto;
import pz1.MIC.enums.ClassEnum;
import pz1.MIC.enums.RaceEnum;
import pz1.MIC.model.BaseStats;
import pz1.MIC.model.Character;
import pz1.MIC.model.MonsterDefinition;
import pz1.MIC.model.Stats;
import pz1.MIC.repository.BaseStatsRepository;
import pz1.MIC.repository.StatsRepository;

@Service
public class StatsService extends GenericManagementService<Stats, StatsRepository> {

    private final BaseStatsRepository baseStatsRepository;


    public StatsService(StatsRepository repository, BaseStatsRepository baseStatsRepository) {
        super(repository);
        this.baseStatsRepository = baseStatsRepository;
    }


    public Stats createStats(ClassEnum classEnum, RaceEnum raceEnum) {
        BaseStats classValues = baseStatsRepository.findFirstByName(classEnum.getDbValue());
        BaseStats raceValues = baseStatsRepository.findFirstByName(raceEnum.getDbValue());


        return Stats.builder()
                .agility(classValues.getAgility() + raceValues.getAgility())
                .armour(classValues.getArmour() + raceValues.getArmour())
                .blockChance(classValues.getBlockChance() + raceValues.getBlockChance())
                .critChance(classValues.getCritChance() + raceValues.getCritChance())
                .critDamage(classValues.getCritDamage() + raceValues.getCritDamage())
                .damage(classValues.getDamage() + raceValues.getDamage())
                .endurance(classValues.getEndurance()+ raceValues.getEndurance())
                .evasionChance(classValues.getEvasionChance() + raceValues.getEvasionChance())
                .exp(classValues.getExp() + raceValues.getExp())
                .gold(classValues.getGold() + raceValues.getGold())
                .health(classValues.getHealth() + raceValues.getHealth())
                .intelligence(classValues.getIntelligence() + raceValues.getIntelligence())
                .luck(classValues.getLuck() + raceValues.getLuck())
                .speed(classValues.getSpeed() + raceValues.getSpeed())
                .strength(classValues.getStrength() + raceValues.getStrength())
                .build();

    }
    public Stats update(Stats stats, StatsDto statsDto)
    {
        stats.setAgility(statsDto.getAgility());
        stats.setArmour(statsDto.getArmour());
        stats.setBlockChance(statsDto.getBlockChance());
        stats.setCritChance(statsDto.getCritChance());
        stats.setCritDamage(statsDto.getCritDamage());
        stats.setDamage(statsDto.getDamage());
        stats.setEndurance(statsDto.getEndurance());
        stats.setEvasionChance(statsDto.getEvasionChance());
        stats.setExp(statsDto.getExp());
        stats.setGold(statsDto.getGold());
        stats.setHealth(statsDto.getHealth());
        stats.setIntelligence(statsDto.getIntelligence());
        stats.setLuck(statsDto.getLuck());
        stats.setSpeed(statsDto.getSpeed());
        stats.setStrength(statsDto.getStrength());
      return   repository.save(stats);

    }
    public Stats updateClassChange(Stats stats,BaseStats currentBS,BaseStats newBS)
    {
        stats.setAgility(stats.getAgility() - currentBS.getAgility() + newBS.getAgility());
        stats.setArmour(stats.getArmour() - currentBS.getArmour() + newBS.getArmour());
        stats.setBlockChance(stats.getBlockChance() - currentBS.getBlockChance() + newBS.getBlockChance());
        stats.setCritChance(stats.getCritChance() - currentBS.getCritChance() + newBS.getCritChance());
        stats.setCritDamage(stats.getCritDamage() - currentBS.getCritDamage() + newBS.getCritDamage());
        stats.setDamage(stats.getDamage() - currentBS.getDamage() + newBS.getDamage());
        stats.setEndurance(stats.getEndurance() - currentBS.getEndurance() + newBS.getEndurance());
        stats.setEvasionChance(stats.getEvasionChance() - currentBS.getEvasionChance() + newBS.getEvasionChance());
        stats.setExp(stats.getExp() - currentBS.getExp() + newBS.getExp());
        stats.setGold(stats.getGold() - currentBS.getGold() + newBS.getGold());
        stats.setHealth(stats.getHealth() - currentBS.getHealth() + newBS.getHealth());
        stats.setIntelligence(stats.getIntelligence() - currentBS.getIntelligence() + newBS.getIntelligence());
        stats.setLuck(stats.getLuck() - currentBS.getIntelligence() + newBS.getIntelligence());
        stats.setSpeed(stats.getSpeed() - currentBS.getSpeed() + currentBS.getSpeed());
        stats.setStrength(stats.getStrength() - currentBS.getStrength() + newBS.getStrength());

        return   repository.save(stats);
    }

    public Stats createMonsterStatsBasedOnCharExp(BaseStats baseStats, Character character)
    {
        Long Multiplier = (character.getStats().getExp()+1000)/1000;
       return repository.save(
               Stats.builder()
                .id(repository.count()+1)
                .damage(baseStats.getDamage() * Multiplier)
                .critDamage(baseStats.getCritDamage() * Multiplier )
                .critChance(baseStats.getCritChance() * Multiplier)
                .blockChance(baseStats.getBlockChance() * Multiplier)
                .agility(baseStats.getAgility() * Multiplier)
                .strength(baseStats.getStrength() * Multiplier)
                .exp(baseStats.getExp() * Multiplier)
                .armour(baseStats.getArmour() * Multiplier)
                .luck(baseStats.getLuck() * Multiplier)
                .intelligence(baseStats.getIntelligence() * Multiplier)
                .health(baseStats.getHealth() * Multiplier)
                .gold(baseStats.getGold() * Multiplier)
                .evasionChance(baseStats.getEvasionChance() * Multiplier)
                .endurance(baseStats.getEndurance() * Multiplier)
                .speed(baseStats.getSpeed() * Multiplier)
                .build());
    }


    public Stats save(Stats stats)
    {
        return   repository.save(stats);
    }
}
