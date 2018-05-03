package com.sieczka.service.teamService;

import com.sieczka.model.Teams;
import com.sieczka.repository.teamRepository.TeamRepository;
import com.sieczka.service.teamService.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2018-01-03.
 */
@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Teams findByTeamsName(String teamsName) {
        Teams teams = teamRepository.findByTeamName(teamsName);
        return teams;
    }

    @Override
    public List<Teams> findByTeamType_LeagueTypeName(String leagueTypeName) {
        return teamRepository.findByTeamType_LeagueTypeName(leagueTypeName);
    }

    @Override
    public List<Teams> findByTeamType_LeagueTypeNameAndUser_Username(String leagueTypeName, String username) {
        return teamRepository.findByTeamType_LeagueTypeNameAndUser_Username(leagueTypeName,username);
    }


    @Override
    public List<Teams> findAll() {
        List<Teams> result = teamRepository.findAll();
        return result;
    }
}
