package pz1.MIC.service;


import org.springframework.stereotype.Service;
import pz1.MIC.model.Race;
import pz1.MIC.repository.RaceRepository;

@Service
public class RaceService extends GenericManagementService<Race, RaceRepository> {
    public RaceService(RaceRepository repository) {
        super(repository);
    }
}
