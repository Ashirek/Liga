package com.sieczka.service.adminService;

import com.sieczka.model.GameWeek;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2018-01-13.
 */
@Service
public class CustomGameWeekDetailsService {
    @Autowired
    GameWeekRepository gameWeekRepository;


    public void addGameWeek(Integer gameWeekNumber, java.sql.Date gameWeekStart,  java.sql.Date gameWeekEnd){

        GameWeek gameWeek = new GameWeek();
        gameWeek.setGameWeekNumber(gameWeekNumber);
        gameWeek.setGameWeekStart(gameWeekStart);
        gameWeek.setGameWeekEnd(gameWeekEnd);
        gameWeekRepository.save(gameWeek);


    }
}
