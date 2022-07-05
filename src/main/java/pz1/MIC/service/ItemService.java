package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.*;
import pz1.MIC.model.Character;
import pz1.MIC.repository.CharacterRepository;
import pz1.MIC.repository.ItemDefinitionRepository;
import pz1.MIC.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService extends GenericManagementService<Item, ItemRepository> {

    private final ItemDefinitionRepository itemDefinitionRepository;
    private final CharacterRepository characterRepository;

    public ItemService(ItemRepository repository, ItemDefinitionRepository itemDefinitionRepository, CharacterRepository characterRepository) {
        super(repository);
        this.itemDefinitionRepository = itemDefinitionRepository;
        this.characterRepository = characterRepository;
    }


    public List <Item> getCharacterItems(CharacterEquipment characterEquipment)
    {
        return repository.findAllByCharacterEquipment(characterEquipment);
    }
    public Item generateRandomItemBasedOnUserLvl(User user)
    {
        Character character = characterRepository.findFirstByUser(user);
        Long count = itemDefinitionRepository.count();
        Long randomWithMathRandom = (long) ((Math.random() * (count - 1) + 1));
        ItemDefinition itemDefinition = itemDefinitionRepository.getById(randomWithMathRandom);
        Item item = new Item();
        item.setItemDefinition(itemDefinition);
        item.setPriceSell(character.getStats().getExp()*randomWithMathRandom);
        item.setPriceBuy(character.getStats().getExp()*(randomWithMathRandom+5));
        item.setStats(Stats.builder()
                .agility(itemDefinition.getBaseStats().getAgility() + (itemDefinition.getBaseBuffs().getAgilityAmp() * character.getStats().getExp()))
                .armour(itemDefinition.getBaseStats().getArmour() + (itemDefinition.getBaseBuffs().getArmourAmp() * character.getStats().getExp()))
                .blockChance(itemDefinition.getBaseStats().getBlockChance() + (itemDefinition.getBaseBuffs().getBlockChanceAmp() * character.getStats().getExp()))
                .critChance(itemDefinition.getBaseStats().getCritChance() + (itemDefinition.getBaseBuffs().getCritChanceAmp() * character.getStats().getExp()))
                .critDamage(itemDefinition.getBaseStats().getCritDamage() + (itemDefinition.getBaseBuffs().getCritDamageAmp() * character.getStats().getExp()))
                .damage(itemDefinition.getBaseStats().getDamage() + (itemDefinition.getBaseBuffs().getDamageAmp() * character.getStats().getExp()))
                .endurance(itemDefinition.getBaseStats().getEndurance() + (itemDefinition.getBaseBuffs().getEnduranceAmp() * character.getStats().getExp()))
                .evasionChance(itemDefinition.getBaseStats().getEvasionChance() + (itemDefinition.getBaseBuffs().getEvasionChanceAmp() * character.getStats().getExp()))
                .exp(itemDefinition.getBaseStats().getExp() + (itemDefinition.getBaseBuffs().getExp() * character.getStats().getExp()))
                .gold(itemDefinition.getBaseStats().getGold() + (itemDefinition.getBaseBuffs().getGold() * character.getStats().getExp()))
                .health(itemDefinition.getBaseStats().getHealth() + (itemDefinition.getBaseBuffs().getHealthAmp() * character.getStats().getExp()))
                .intelligence(itemDefinition.getBaseStats().getIntelligence() + (itemDefinition.getBaseBuffs().getIntelligenceAmp() * character.getStats().getExp()))
                .luck(itemDefinition.getBaseStats().getLuck() + (itemDefinition.getBaseBuffs().getLuckAmp() * character.getStats().getExp()))
                .speed(itemDefinition.getBaseStats().getSpeed() + (itemDefinition.getBaseBuffs().getSpeedAmp() * character.getStats().getExp()))
                .strength(itemDefinition.getBaseStats().getStrength() + (itemDefinition.getBaseBuffs().getStrengthAmp() * character.getStats().getExp()))
                .build());
        return repository.save(item);
    }

//    public List<Item> getCharacterShop(CharacterEquipment )


}
