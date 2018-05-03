package com.sieczka.service.adminService;

import com.sieczka.model.RealTeams;

import java.util.List;

/**
 * Created by Patryk on 2017-12-20.
 */
public interface RealTeamService {

    RealTeams findByRealTeamName(String realTeamName);

    RealTeams findByRealTeamId(Long realTeamId);

    List<RealTeams> findAll();
}
