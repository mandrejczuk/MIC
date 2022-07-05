package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.BaseStats;
import pz1.MIC.model.User;
import pz1.MIC.repository.CharacterRepository;
import pz1.MIC.model.Character;

@Service
public class CharacterService extends GenericManagementService<Character, CharacterRepository> {

    public CharacterService(CharacterRepository repository) {
        super(repository);
    }

    public Character findbyuserid(User user){
        Character character = repository.findFirstByUser(user);

        return character;
    }
    public Character save(Character character)
    {
        return repository.save(character);
    }

}
