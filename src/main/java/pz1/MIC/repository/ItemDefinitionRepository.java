package pz1.MIC.repository;

import org.hibernate.query.Query;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.BaseBuffs;
import pz1.MIC.model.ItemDefinition;

import java.util.List;

public interface ItemDefinitionRepository extends JpaRepository<ItemDefinition, Long> {

}
