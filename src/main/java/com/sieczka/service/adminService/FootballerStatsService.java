package com.sieczka.service.adminService;

import com.sieczka.model.FootballerStats;

/**
 * Created by Patryk on 2018-02-19.
 */
public interface FootballerStatsService {
    FootballerStats findByGameWeek_GameWeekNumberAndFootballers_FootballerLastName(Integer gameWeekNumber, String footballerLastName);
}
