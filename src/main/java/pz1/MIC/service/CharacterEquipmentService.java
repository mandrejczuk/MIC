package pz1.MIC.service;

import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import pz1.MIC.controller.request.IdHolder;
import pz1.MIC.model.*;
import pz1.MIC.repository.CharacterEquipmentRepository;
import pz1.MIC.repository.ItemDefinitionRepository;
import pz1.MIC.repository.ItemRepository;

import javax.persistence.EntityManager;

@Service
public class CharacterEquipmentService extends GenericManagementService<CharacterEquipment, CharacterEquipmentRepository> {

    private final ItemDefinitionRepository itemDefinitionRepository;
    private final ItemRepository itemRepository;

    public CharacterEquipmentService(CharacterEquipmentRepository repository,ItemDefinitionRepository itemDefinitionRepository,ItemRepository itemRepository) {
        super(repository);
        this.itemDefinitionRepository = itemDefinitionRepository;
        this.itemRepository = itemRepository;
    }



public void createNewEq(CharacterEquipment characterEquipment, IdHolder classid){

    Item weapon = new Item();
    weapon.setSlotNum(7L);
    weapon.setPriceBuy(0L);
    weapon.setPriceSell(0L);
    weapon.setImgRef("");
    weapon.setCharacterEquipment(characterEquipment);
    weapon.setItemDefinition(itemDefinitionRepository.getById(classid.getId()));
    ItemDefinition itemDefinitionWeapon = itemDefinitionRepository.getById(classid.getId());
    BaseStats baseStatsWeapon = itemDefinitionWeapon.getBaseStats();
    BaseBuffs baseBuffsWeapon = itemDefinitionWeapon.getBaseBuffs();
    weapon.setStats(Stats.builder()
                    .agility(baseStatsWeapon.getAgility() * baseBuffsWeapon.getAgilityAmp())
                    .armour(baseStatsWeapon.getArmour() * baseBuffsWeapon.getArmourAmp())
                    .blockChance(baseStatsWeapon.getBlockChance() * baseBuffsWeapon.getBlockChanceAmp())
                    .critChance(baseStatsWeapon.getCritChance() * baseBuffsWeapon.getCritChanceAmp())
                    .critDamage(baseStatsWeapon.getCritDamage() * baseBuffsWeapon.getCritDamageAmp())
                    .damage(baseStatsWeapon.getDamage() * baseBuffsWeapon.getDamageAmp())
                    .endurance(baseStatsWeapon.getEndurance() * baseBuffsWeapon.getEnduranceAmp())
                    .evasionChance(baseStatsWeapon.getEvasionChance() * baseBuffsWeapon.getEvasionChanceAmp())
                    .exp(baseStatsWeapon.getExp() * baseBuffsWeapon.getExp())
                    .gold(baseStatsWeapon.getGold() * baseBuffsWeapon.getGold())
                    .health(baseStatsWeapon.getHealth() * baseBuffsWeapon.getHealthAmp())
                    .intelligence(baseStatsWeapon.getIntelligence() * baseBuffsWeapon.getIntelligenceAmp())
                    .luck(baseStatsWeapon.getLuck() * baseBuffsWeapon.getLuckAmp())
                    .speed(baseStatsWeapon.getSpeed() * baseBuffsWeapon.getSpeedAmp())
                    .strength(baseStatsWeapon.getStrength() * baseBuffsWeapon.getStrengthAmp())
                                            .build());

    Item armour = new Item();
    armour.setSlotNum(7L);
    armour.setPriceBuy(0L);
    armour.setPriceSell(0L);
    armour.setImgRef("");
    armour.setCharacterEquipment(characterEquipment);
    armour.setItemDefinition(itemDefinitionRepository.getById(classid.getId()+3));
    ItemDefinition itemDefinitionArmour = itemDefinitionRepository.getById(classid.getId()+3);
    BaseStats baseStatsArmour = itemDefinitionArmour.getBaseStats();
    BaseBuffs baseBuffsArmour = itemDefinitionArmour.getBaseBuffs();

    armour.setStats(Stats.builder()
            .agility(baseStatsArmour.getAgility() * baseBuffsArmour.getAgilityAmp())
            .armour(baseStatsArmour.getArmour() * baseBuffsArmour.getArmourAmp())
            .blockChance(baseStatsArmour.getBlockChance() * baseBuffsArmour.getBlockChanceAmp())
            .critChance(baseStatsArmour.getCritChance() * baseBuffsArmour.getCritChanceAmp())
            .critDamage(baseStatsArmour.getCritDamage() * baseBuffsArmour.getCritDamageAmp())
            .damage(baseStatsArmour.getDamage() * baseBuffsArmour.getDamageAmp())
            .endurance(baseStatsArmour.getEndurance() * baseBuffsArmour.getEnduranceAmp())
            .evasionChance(baseStatsArmour.getEvasionChance() * baseBuffsArmour.getEvasionChanceAmp())
            .exp(baseStatsArmour.getExp() * baseBuffsArmour.getExp())
            .gold(baseStatsArmour.getGold() * baseBuffsArmour.getGold())
            .health(baseStatsArmour.getHealth() * baseBuffsArmour.getHealthAmp())
            .intelligence(baseStatsArmour.getIntelligence() * baseBuffsArmour.getIntelligenceAmp())
            .luck(baseStatsArmour.getLuck() * baseBuffsArmour.getLuckAmp())
            .speed(baseStatsArmour.getSpeed() * baseBuffsArmour.getSpeedAmp())
            .strength(baseStatsArmour.getStrength() * baseBuffsArmour.getStrengthAmp())
            .build());

    Item secondHand = new Item();
    secondHand.setSlotNum(7L);
    secondHand.setPriceBuy(0L);
    secondHand.setPriceSell(0L);
    secondHand.setImgRef("");
    secondHand.setCharacterEquipment(characterEquipment);
    secondHand.setItemDefinition(itemDefinitionRepository.getById(classid.getId()+6));
    ItemDefinition itemDefinitionHand = itemDefinitionRepository.getById(classid.getId()+6);
    BaseStats baseStatsHand = itemDefinitionHand.getBaseStats();
    BaseBuffs baseBuffsHand = itemDefinitionHand.getBaseBuffs();

    secondHand.setStats(Stats.builder()
            .agility(baseStatsHand.getAgility() * baseBuffsHand.getAgilityAmp())
            .armour(baseStatsHand.getArmour() * baseBuffsHand.getArmourAmp())
            .blockChance(baseStatsHand.getBlockChance() * baseBuffsHand.getBlockChanceAmp())
            .critChance(baseStatsHand.getCritChance() * baseBuffsHand.getCritChanceAmp())
            .critDamage(baseStatsHand.getCritDamage() * baseBuffsHand.getCritDamageAmp())
            .damage(baseStatsHand.getDamage() * baseBuffsHand.getDamageAmp())
            .endurance(baseStatsHand.getEndurance() * baseBuffsHand.getEnduranceAmp())
            .evasionChance(baseStatsHand.getEvasionChance() * baseBuffsHand.getEvasionChanceAmp())
            .exp(baseStatsHand.getExp() * baseBuffsHand.getExp())
            .gold(baseStatsHand.getGold() * baseBuffsHand.getGold())
            .health(baseStatsHand.getHealth() * baseBuffsHand.getHealthAmp())
            .intelligence(baseStatsHand.getIntelligence() * baseBuffsHand.getIntelligenceAmp())
            .luck(baseStatsHand.getLuck() * baseBuffsHand.getLuckAmp())
            .speed(baseStatsHand.getSpeed() * baseBuffsHand.getSpeedAmp())
            .strength(baseStatsHand.getStrength() * baseBuffsHand.getStrengthAmp())
            .build());

    itemRepository.save(weapon);
    itemRepository.save(armour);
    itemRepository.save(secondHand);
}

}
