package pz1.MIC.service;

import org.springframework.stereotype.Service;
import pz1.MIC.model.Class;
import pz1.MIC.repository.ClassRepository;

@Service
public class ClassService extends GenericManagementService<Class, ClassRepository> {
    public ClassService(ClassRepository repository) {
        super(repository);
    }
}
