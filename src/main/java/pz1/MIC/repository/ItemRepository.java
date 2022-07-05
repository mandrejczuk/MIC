package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.CharacterEquipment;
import pz1.MIC.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List <Item>  findAllByCharacterEquipment(CharacterEquipment characterEquipment);
}
