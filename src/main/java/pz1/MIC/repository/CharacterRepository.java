package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.Character;
import pz1.MIC.model.User;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    Character findFirstByUser(User user);
}
