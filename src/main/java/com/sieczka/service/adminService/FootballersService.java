package com.sieczka.service.adminService;

import com.sieczka.model.Footballers;
import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;

import java.util.List;

/**
 * Created by Patryk on 2018-01-02.
 */
public interface FootballersService {

    Footballers findByFootballersFirstName(String footballersFirstName);
    Footballers findByFootballerLastName(String footballerLastName);
    Footballers findByFootballerLastNameAndRealTeams_RealTeamName(String lastName, String realTeamName);
    Footballers findByFootballerLastNameAndRosters(String footballerLastName, Rosters rosters);
    List<Footballers> findByRostersAndRealTeams_RealTeamName(Rosters rosters, String realTeamName);
    List<Footballers> findByPositionAndRosters(String position, Rosters rosters);
    List<Footballers> findByPositionAndStarters(String position, Starters starters);
    List<Footballers> findByRealTeams_RealTeamName(String realTeamName);
    List<Footballers> findAll();
}
