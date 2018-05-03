package com.sieczka.service.adminService;

import com.sieczka.model.Footballers;
import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;
import com.sieczka.repository.adminRepository.FootballersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2018-01-02.
 */
@Service
public class FootballersServiceImpl implements FootballersService {

    @Autowired
    private FootballersRepository footballersRepository;

    @Override
    public Footballers findByFootballersFirstName(String footballersFirstName) {
        Footballers footballers = footballersRepository.findByFootballerFirstName(footballersFirstName);
        return footballers;
    }

    @Override
    public Footballers findByFootballerLastName(String footballerLastName) {
        Footballers footballers = footballersRepository.findByFootballerLastName(footballerLastName);
        return footballers;
    }

    @Override
    public Footballers findByFootballerLastNameAndRealTeams_RealTeamName(String lastName, String realTeamName) {
        return footballersRepository.findByFootballerLastNameAndRealTeams_RealTeamName(lastName,realTeamName);
    }

    @Override
    public Footballers findByFootballerLastNameAndRosters(String footballerLastName, Rosters rosters) {
        Footballers footballers = footballersRepository.findByFootballerLastNameAndRosters(footballerLastName, rosters);
        return footballers;

    }

    @Override
    public List<Footballers> findByRostersAndRealTeams_RealTeamName(Rosters rosters, String realTeamName) {
        return footballersRepository.findByRostersAndRealTeams_RealTeamName(rosters, realTeamName);
    }


    @Override
    public List<Footballers> findByPositionAndRosters(String position, Rosters rosters) {
       List<Footballers> footballers = footballersRepository.findByPositionAndRosters(position, rosters);
        return footballers;
    }

    @Override
    public List<Footballers> findByPositionAndStarters(String position, Starters starters) {
        List<Footballers> footballers = footballersRepository.findByPositionAndStarters(position, starters);
        return footballers;

    }

    @Override
    public List<Footballers> findByRealTeams_RealTeamName(String realTeamName) {
        return footballersRepository.findByRealTeams_RealTeamName(realTeamName);
    }


    @Override
    public List<Footballers> findAll() {
        List<Footballers> result =  footballersRepository.findAll();
        return result;
    }
}
