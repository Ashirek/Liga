package com.sieczka.service.adminService;

import com.sieczka.model.GameWeek;

import java.util.List;

/**
 * Created by Patryk on 2018-01-13.
 */
public interface GameWeekService {
    GameWeek findByGameWeekNumber(Integer gameWeekNumber);
    List<GameWeek> findAll();
}
