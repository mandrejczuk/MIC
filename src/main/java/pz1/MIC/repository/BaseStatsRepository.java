package pz1.MIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pz1.MIC.model.BaseStats;

public interface BaseStatsRepository extends JpaRepository<BaseStats,Long> {

    BaseStats findFirstByName(String name);
}
