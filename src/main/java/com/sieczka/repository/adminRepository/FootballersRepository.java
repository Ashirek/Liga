package com.sieczka.repository.adminRepository;

import com.sieczka.model.Footballers;
import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by Patryk on 2018-01-02.
 */
public interface FootballersRepository extends JpaRepository<Footballers, Long> {

    Footballers findByFootballerFirstName(String footballersFirstName);
    Footballers findByFootballerLastName(String footballerLastName);

    Footballers findByFootballerLastNameAndRealTeams_RealTeamName(String lastName, String realTeamName);



    Footballers findByFootballerLastNameAndRosters(String footballerLastName, Rosters rosters);

    List<Footballers> findByRostersAndRealTeams_RealTeamName(Rosters rosters, String realTeamName);

    List<Footballers> findByPositionAndStarters(String position, Starters starters);

    List<Footballers> findByPositionAndRosters(String position, Rosters rosters);

    List<Footballers> findByRealTeams_RealTeamName(String realTeamName);
}
