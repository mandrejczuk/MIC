package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.BaseStats;
import pz1.MIC.repository.BaseStatsRepository;
@Service
public class BaseStatsService extends GenericManagementService<BaseStats, BaseStatsRepository> {
    public BaseStatsService(BaseStatsRepository repository) {
        super(repository);
    }
}
