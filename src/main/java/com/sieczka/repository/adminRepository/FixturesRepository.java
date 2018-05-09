package com.sieczka.repository.adminRepository;

import com.sieczka.model.Fixtures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Patryk on 2018-01-14.
 */
public interface FixturesRepository extends JpaRepository<Fixtures,Long> {
    Fixtures findByHomeTeamNameAndAwayTeamName(String homeTeamName, String awayTeamName);
    List<Fixtures> findByGameWeek_GameWeekNumber(Integer gameWeekNumber);
}
