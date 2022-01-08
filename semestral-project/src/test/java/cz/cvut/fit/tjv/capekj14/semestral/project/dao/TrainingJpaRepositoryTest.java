package cz.cvut.fit.tjv.capekj14.semestral.project.dao;


import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TrainingJpaRepositoryTest {

    @Autowired
    private PlayerJpaRepository repoP;

    @Autowired
    private TrainingJpaRepository repoT;

    @Test
    void findAllPlayersOnTraining(){
        Player karel = new Player(0 , "karel", "karlovic", 1);
        Player pepa = new Player(1, "pepa", "pep", 15 );
        Training training = new Training (3, 1);
        Training training2 = new Training (7, 2);
        repoT.save(training);
        repoP.save(karel);
        repoP.save(pepa);
        //Assertions.assertEquals(1,repoT.findAllPlayersOfTraining(training));
        //Assertions.assertTrue(repoT.findAllPlayersOfTraining(training2).isEmpty());

    }
}
