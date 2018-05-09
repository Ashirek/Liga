package com.sieczka.service.adminService;

import com.sieczka.model.GameWeek;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2018-01-13.
 */
@Service
public class GameWeekServiceImpl implements GameWeekService {

    @Autowired
    private GameWeekRepository gameWeekRepository;

    @Override
    public GameWeek findByGameWeekNumber( Integer gameWeekNumber) {
        GameWeek gameWeek = gameWeekRepository.findByGameWeekNumber( gameWeekNumber);
        return gameWeek;
    }


    @Override
    public List<GameWeek> findAll() {
        List<GameWeek> result = gameWeekRepository.findAll();
        return result;
    }
}
