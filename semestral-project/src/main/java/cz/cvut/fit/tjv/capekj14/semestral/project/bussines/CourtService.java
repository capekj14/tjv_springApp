package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

import cz.cvut.fit.tjv.capekj14.semestral.project.dao.CourtJpaRepository;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Court;
import org.springframework.stereotype.Component;

@Component
public class CourtService extends AbstractCrudService<Integer, Court>{
    public CourtService(CourtJpaRepository repo) {
        super(repo);
    }

    @Override
    public boolean exists(Court entity) {

        if(entity.getIdCourt() == null)
            return false;
        return repository.existsById(entity.getIdCourt());
    }
}
