package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

import cz.cvut.fit.tjv.capekj14.semestral.project.dao.PlayerJpaRepository;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerService extends AbstractCrudService <Integer, Player>{
    public PlayerService(PlayerJpaRepository repo) {
        super(repo);
    }


    @Override
    public boolean exists(Player entity) {
        if(entity.getIdPlayer() == null)
            return false;
        return repository.existsById(entity.getIdPlayer());
    }
    /*
    public List<Player> getAllPlayersOfTraining (Training training) {
        return repository.getAllPlayersOfTraining(training);
    }
    */
}
