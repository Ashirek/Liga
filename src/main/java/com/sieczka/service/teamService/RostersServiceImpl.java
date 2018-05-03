package com.sieczka.service.teamService;

import com.sieczka.model.Rosters;
import com.sieczka.repository.teamRepository.RostersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2018-01-16.
 */
@Service
public class RostersServiceImpl implements RostersService {

    @Autowired
    private RostersRepository rostersRepository;

    @Override
    public Rosters findByTeams_TeamName(String teamName) {
        Rosters rosters = rostersRepository.findByTeams_TeamName(teamName);
        return rosters;
    }


    @Override
    public Rosters findByRosterId(Long id) {
        Rosters rosters = rostersRepository.findByRosterId(id);
        return rosters;
    }

    @Override
    public List<Rosters> findAll() {
        List<Rosters> result = rostersRepository.findAll();
        return result;
    }
}
