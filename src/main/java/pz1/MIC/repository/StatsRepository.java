package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.Stats;

public interface StatsRepository extends JpaRepository<Stats,Long> {
}
