package pz1.MIC.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pz1.MIC.model.User;
import pz1.MIC.repository.*;

@SpringBootTest
public class UserSerivceTests {

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

    @Test
    public void addUserTest()
    {
        User user = new User();
        user.setUserName("test1");
        user.setPassword("123");
        assert userRepository.findAll().size() == 0;
        userService.add(user);
        assert userRepository.findAll().size() == 1;
    }

    @Test
    public void deleteUserTest()
    {
        User user = new User();
        user.setUserName("test1");
        user.setPassword("123");
        userService.add(user);
        userService.delete(user.getId());
      assert userService.getAll().size() == 0;
    }

}
