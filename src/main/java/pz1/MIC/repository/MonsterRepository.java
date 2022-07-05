package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.Monster;

public interface MonsterRepository extends JpaRepository<Monster,Long> {
}
