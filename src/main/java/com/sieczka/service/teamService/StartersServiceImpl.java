package com.sieczka.service.teamService;

import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;
import com.sieczka.repository.teamRepository.StartersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2018-02-05.
 */
@Service
public class StartersServiceImpl implements StartersService{
    @Autowired
    StartersRepository startersRepository;

    @Override
    public Starters findStartersByRosters(Rosters rosters) {
        Starters starters = startersRepository.findStartersByRosters(rosters);
        return starters;
    }
}
