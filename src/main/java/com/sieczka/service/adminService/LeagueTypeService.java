package com.sieczka.service.adminService;

import com.sieczka.model.LeagueType;

import java.util.List;

/**
 * Created by Patryk on 2017-12-09.
 */
public interface LeagueTypeService {

    LeagueType findByLeagueTypeName(String leagueTypeName);

    LeagueType findByLeagueTypeId(Long leagueTypeId);
    List<LeagueType> findAll();
}
