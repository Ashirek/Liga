package com.sieczka.service.adminService;

import com.sieczka.model.GameWeek;

import java.util.List;

/**
 * Created by Patryk on 2018-01-13.
 */
public interface GameWeekService {
    GameWeek findByLeagueType_LeagueTypeNameAndGameWeekNumber(String leagueTypeName, Integer gameWeekNumber);
    List<GameWeek> findGameWeekByLeagueType_LeagueTypeName(String leagueTypeName);
    List<GameWeek> findAll();
}
