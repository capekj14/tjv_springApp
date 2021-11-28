package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

import cz.cvut.fit.tjv.capekj14.semestral.project.dao.PlayerJpaRepository;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerService extends AbstractCrudService <String, Player>{
    public PlayerService(PlayerJpaRepository repo) {
        super(repo);
    }

    @Override
    public boolean exists(Player entity) {
        return repository.existsById(entity.getIdPlayer());
    }
}
