package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.Character;
import pz1.MIC.model.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    Quest findAllByCharacterEqualsAndDescriptionEquals(Character character, String description);
}
