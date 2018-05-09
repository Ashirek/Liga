package com.sieczka.service.adminService;

import com.sieczka.model.RealTeams;
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



    public void addTeam(String realTeamName){


        RealTeams realTeams = realTeamRepository.findByRealTeamName(realTeamName);

        if (realTeams != null)
        {

        }
        else{
            realTeams = new RealTeams();
            realTeams.setRealTeamName(realTeamName);
            realTeamRepository.save(realTeams);

        }

    }
}
