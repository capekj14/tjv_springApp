package cz.cvut.fit.tjv.capekj14.semestral.project.dao;

import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtJpaRepository extends JpaRepository<Court, Integer> {
}
