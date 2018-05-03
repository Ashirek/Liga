package com.sieczka.service.adminService;

import com.sieczka.model.LeagueType;
import com.sieczka.model.RealTeams;
import com.sieczka.repository.adminRepository.LeagueTypeRepository;
import com.sieczka.repository.adminRepository.RealTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2017-12-26.
 */
@Service
public class CustomRealTeamDetailsService {



    @Autowired
    private RealTeamRepository realTeamRepository;

    @Autowired
    private LeagueTypeRepository leagueTypeRepository;

    public void addTeam(String realTeamName, String leagueTypeName){


        RealTeams realTeams = realTeamRepository.findByRealTeamName(realTeamName);
        LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName(leagueTypeName);

        if (realTeams != null)
        {

        }
        else{
            realTeams = new RealTeams();
            realTeams.setRealTeamName(realTeamName);
            realTeams.setRealTeamLeague(leagueType);
            realTeamRepository.save(realTeams);

        }

    }
}
