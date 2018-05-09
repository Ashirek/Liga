package com.sieczka.service.adminService;

import com.sieczka.model.FootballerStats;
import com.sieczka.model.Footballers;
import com.sieczka.model.GameWeek;
import com.sieczka.repository.adminRepository.FootballerStatsRepository;
import com.sieczka.repository.adminRepository.FootballersRepository;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Patryk on 2018-02-19.
 */
@Service
public class CustomFootballerStatsService {


    @Autowired
    private    FootballerStatsRepository footballerStatsRepository;

    @Autowired
    private    FootballersService footballersService;

    @Autowired
    private    GameWeekService gameWeekService;

    public void addFootballerStats(String footballerLastName, String realTeamName, Integer gameWeekNumber, Integer goals, Integer assists, Integer yCards, Integer rCards, Integer gameTime, String matchResult){
        Integer points;
        points=goals * 3 + assists * 2 + yCards * (-2) + rCards * (-3);
        if (gameTime == 90){
            points = points + 3;
        } else if (gameTime > 0){
            points = points + 1;
        }
        if (Objects.equals(matchResult, "win")){
           points = points + 3;
        } else if (Objects.equals(matchResult, "draw")){
            points = points + 1;
        }
        FootballerStats footballerStats = new FootballerStats();
        Footballers footballers = footballersService.findByFootballerLastNameAndRealTeams_RealTeamName(footballerLastName, realTeamName);
        footballerStats.setFootballers(footballers);
        GameWeek gameWeek = gameWeekService.findByGameWeekNumber(gameWeekNumber);
        footballerStats.setGameWeek(gameWeek);
        footballerStats.setGoals(goals);
        footballerStats.setAssists(assists);
        footballerStats.setYellowCards(yCards);
        footballerStats.setRedCards(rCards);
        footballerStats.setGameTime(gameTime);
        footballerStats.setMatchResult(matchResult);
        footballerStats.setWeekPoints(points);
        footballerStatsRepository.save(footballerStats);

    }
}
