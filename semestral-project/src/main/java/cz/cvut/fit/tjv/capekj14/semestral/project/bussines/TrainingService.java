package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

import cz.cvut.fit.tjv.capekj14.semestral.project.dao.TrainingJpaRepository;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingService extends AbstractCrudService<Integer, Training>{
    public TrainingService(TrainingJpaRepository repo) { super(repo); }

    @Override
    public boolean exists(Training entity) {

        if(entity.getIdTraining() == null)
            return false;
        return repository.existsById(entity.getIdTraining());
    }
}
