package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.Race;

public interface RaceRepository extends JpaRepository<Race,Long> {
}
