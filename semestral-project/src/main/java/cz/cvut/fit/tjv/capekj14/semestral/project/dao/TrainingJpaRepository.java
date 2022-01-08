package cz.cvut.fit.tjv.capekj14.semestral.project.dao;

import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingJpaRepository extends JpaRepository <Training, Integer>{


}
