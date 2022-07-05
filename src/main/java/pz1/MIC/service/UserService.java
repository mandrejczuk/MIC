package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.User;
import pz1.MIC.repository.UserRepository;

import java.util.List;

@Service
public class UserService extends GenericManagementService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User updateUserName(String newUserName,Long id)
    {
        User user = repository.getById(id);
        user.setUserName(newUserName);
        return repository.save(user);
    }


}
