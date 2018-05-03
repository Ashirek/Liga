package com.sieczka.service.adminService;

import com.sieczka.model.LeagueType;
import com.sieczka.repository.adminRepository.LeagueTypeRepository;
import com.sieczka.service.adminService.LeagueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2017-12-09.
 */
@Service
public class LeagueTypeServiceImpl implements LeagueTypeService {
    @Autowired
    private LeagueTypeRepository leagueTypeRepository;

    @Override
    public LeagueType findByLeagueTypeName(String leagueTypeName) {
        LeagueType leagueType = leagueTypeRepository.findByLeagueTypeName(leagueTypeName);
        return leagueType;
    }

    @Override
    public LeagueType findByLeagueTypeId (Long leagueTypeId)   {
        LeagueType leagueType = leagueTypeRepository.findByLeagueTypeId(leagueTypeId);
        return leagueType;
    }

    @Override
    public List<LeagueType> findAll() {
        List<LeagueType> result = leagueTypeRepository.findAll();
        return result;
    }
}
