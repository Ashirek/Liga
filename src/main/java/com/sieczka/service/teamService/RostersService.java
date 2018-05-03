package com.sieczka.service.teamService;

import com.sieczka.model.Rosters;

import java.util.List;

/**
 * Created by Patryk on 2018-01-16.
 */
public interface RostersService {
    Rosters findByTeams_TeamName(String teamName);
    Rosters findByRosterId(Long id);

    List<Rosters> findAll();
}
