package com.sieczka.service.adminService;

import com.sieczka.model.FootballerStats;
import com.sieczka.repository.adminRepository.FootballerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2018-02-19.
 */
@Service
public class FootballerStatsServiceImpl implements FootballerStatsService {
    @Autowired
    FootballerStatsRepository footballerStatsRepository;

    @Override
    public FootballerStats findByGameWeek_GameWeekNumberAndFootballers_FootballerLastName(Integer gameWeekNumber, String footballerLastName) {
        return footballerStatsRepository.findByGameWeek_GameWeekNumberAndFootballers_FootballerLastName(gameWeekNumber,footballerLastName);
    }
}
