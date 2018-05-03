package com.sieczka.service.adminService;

import com.sieczka.model.Fixtures;
import com.sieczka.model.GameWeek;
import com.sieczka.repository.adminRepository.FixturesRepository;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Patryk on 2018-01-14.
 */
@Service
public class CustomFixturesDetailsService {

    @Autowired
    FixturesRepository fixturesRepository;

    @Autowired
    GameWeekRepository gameWeekRepository;

    public void addFixture(String homeTeamName, String awayTeamName, Date gameTime, Integer gameWeekNumber, String leagueTypeName){

        //Fixtures fixtures = fixturesRepository.findByGameWeek_LeagueType_LeagueTypeNameAndGameWeek_GameWeekNumber(leagueTypeName, gameWeekNumber);

        GameWeek gameWeek = gameWeekRepository.findByLeagueType_LeagueTypeNameAndGameWeekNumber(leagueTypeName, gameWeekNumber);


            Fixtures fixtures = new Fixtures();
            fixtures.setHomeTeamName(homeTeamName);
            fixtures.setAwayTeamName(awayTeamName);
            fixtures.setGameTime(gameTime);
            fixtures.setGameWeek(gameWeek);
            fixturesRepository.save(fixtures);


    }

    public void putFixture(String homeTeamName, String awayTeamName, Integer homeTeamGoals, Integer awayTeamGoals){
        Fixtures fixtures = fixturesRepository.findByHomeTeamNameAndAwayTeamName(homeTeamName, awayTeamName);

        fixtures.setHomeTeamGoals(homeTeamGoals);
        fixtures.setAwayTeamGoals(awayTeamGoals);
        fixturesRepository.save(fixtures);
    }
}
