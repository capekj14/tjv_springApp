package cz.cvut.fit.tjv.capekj14.semestral.project.bussiness;

import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.EntityStateException;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.PlayerService;
import cz.cvut.fit.tjv.capekj14.semestral.project.dao.PlayerJpaRepository;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class PlayerServiceTest {

    @MockBean
    PlayerJpaRepository repo;

    @Autowired
    PlayerService service;

    @Test
    void save() {
        Player karel = new Player(0, "karel", "karlovic" , 1);
        service.create(karel);
        Mockito.verify(repo, Mockito.times(1)).save(karel);
    }

    @Test
    void findById() {
        Player pepa = new Player(4, "pepa", "pep", 5);
        Mockito.when(repo.findById(pepa.getIdPlayer())).thenReturn(Optional.of(pepa));
        Assertions.assertEquals(Optional.of(pepa), service.findById(pepa.getIdPlayer()));
    }

    @Test
    void findByIdNotExists() {
        Assertions.assertTrue(service.findById(100000).isEmpty());
    }

    @Test
    void update() {
        Player karel = new Player(0, "karel", "karlovic" , 1);
        Mockito.when(service.exists(karel)).thenReturn(Boolean.TRUE);
        service.update(karel);
        Mockito.verify(repo, Mockito.times(1)).save(karel);
    }

    @Test
    void updateNotExisting() {
        Player karel = new Player(0, "karel", "karlovic" , 1);
        Mockito.when(service.exists(karel)).thenReturn(Boolean.FALSE);
        try {
            service.update(karel);
        }
        catch(EntityStateException ignored){}
        Mockito.verify(repo, Mockito.times(0)).save(karel);

    }
}
