package com.sieczka.service.teamService;

import com.sieczka.model.Teams;

import java.util.List;

/**
 * Created by Patryk on 2018-01-03.
 */
public interface TeamsService {
    Teams findByTeamsName(String teamsName);

    List<Teams> findByTeamType_LeagueTypeName(String leagueTypeName);
    List<Teams> findByTeamType_LeagueTypeNameAndUser_Username(String leagueTypeName, String username);

    List<Teams> findAll();
}
