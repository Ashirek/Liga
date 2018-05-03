package com.sieczka.service.adminService;

import com.sieczka.model.GameWeek;
import com.sieczka.model.LeagueType;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import com.sieczka.repository.adminRepository.LeagueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Patryk on 2018-01-13.
 */
@Service
public class CustomGameWeekDetailsService {
    @Autowired
    GameWeekRepository gameWeekRepository;

    @Autowired
    LeagueTypeRepository leagueTypeRepository;
    public void addGameWeek(Integer gameWeekNumber, String leagueTypeName, java.sql.Date gameWeekStart,  java.sql.Date gameWeekEnd){

        GameWeek gameWeek = new GameWeek();
        LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName(leagueTypeName);
        gameWeek.setGameWeekNumber(gameWeekNumber);
        gameWeek.setLeagueType(leagueType);
        gameWeek.setGameWeekStart(gameWeekStart);
        gameWeek.setGameWeekEnd(gameWeekEnd);
      /*  LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName("Premier League");
        gameWeek.setGameWeekNumber(1);
        gameWeek.setLeagueType(leagueType);
        gameWeek.setGameWeekStart(gameWeekStart);
        gameWeek.setGameWeekEnd(gameWeekEnd);*/
        gameWeekRepository.save(gameWeek);


    }
}
