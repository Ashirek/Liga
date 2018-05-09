package com.sieczka.service.adminService;

import com.sieczka.model.RealTeams;
import com.sieczka.repository.adminRepository.RealTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2017-12-20.
 */
@Service
public class RealTeamServiceImpl implements RealTeamService {

    @Autowired
    private RealTeamRepository realTeamRepository;

    @Override
    public RealTeams findByRealTeamName(String realTeamName) {
        RealTeams realTeams = realTeamRepository.findByRealTeamName(realTeamName);
        return realTeams;
    }


    @Override
    public List<RealTeams> findAll() {
        List<RealTeams> result = realTeamRepository.findAll();
        return result;
    }
}
