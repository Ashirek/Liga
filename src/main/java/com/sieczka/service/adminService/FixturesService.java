package com.sieczka.service.adminService;

import com.sieczka.model.Fixtures;

import java.util.List;

/**
 * Created by Patryk on 2018-01-14.
 */
public interface FixturesService {
    List<Fixtures> findByGameWeek_LeagueType_LeagueTypeNameAndGameWeek_GameWeekNumber(String leagueTypeName, Integer gameWeekNumber);
    Fixtures findByHomeTeamNameAndAwayTeamName(String homeTeamName, String awayTeamName);
    List<Fixtures> findAll();
}
