package pz1.MIC.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pz1.MIC.repository.*;

@SpringBootTest
public class CharacterServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CharacterEquipmentRepository characterEquipmentRepository;



    @BeforeEach
    public void clearRepositories(){

        itemRepository.deleteAll();
        questRepository.deleteAll();
        characterRepository.deleteAll();
        characterEquipmentRepository.deleteAll();
        userRepository.deleteAll();
    }
}
