package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.ItemDefinition;
import pz1.MIC.repository.ItemDefinitionRepository;

@Service
public class ItemDefinitionService extends GenericManagementService<ItemDefinition, ItemDefinitionRepository> {
    public ItemDefinitionService(ItemDefinitionRepository repository) {
        super(repository);
    }
}
