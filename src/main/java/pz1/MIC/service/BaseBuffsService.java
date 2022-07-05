package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.BaseBuffs;
import pz1.MIC.repository.BaseBuffsRepository;
@Service
public class BaseBuffsService extends GenericManagementService<BaseBuffs, BaseBuffsRepository> {
    public BaseBuffsService(BaseBuffsRepository repository) {
        super(repository);
    }
}
