package cz.cvut.fit.tjv.capekj14.semestral.project.dao;

import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerJpaRepository extends JpaRepository <Player, Integer> {

    /*
    @Query("SELECT p FROM player_tjv p WHERE p.trainings = :training")
    List<Player> getAllPlayersOfTraining (Training training);
    */
}
