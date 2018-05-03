package com.sieczka.service.adminService;

import com.sieczka.model.LeagueType;
import com.sieczka.repository.adminRepository.LeagueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2017-12-09.
 */
@Service
public class CustomLeagueTypeDetailsService {

    @Autowired
    private LeagueTypeRepository leagueTypeRepository;

    public void addLeague(String leagueTypeName){


         LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName(leagueTypeName);
        
        if (leagueType != null)
        {

        }
        else{
           leagueType = new LeagueType();
        leagueType.setLeagueTypeName(leagueTypeName);
        leagueTypeRepository.save(leagueType);
        }

    }
}
