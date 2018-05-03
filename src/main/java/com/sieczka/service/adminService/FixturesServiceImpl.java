package com.sieczka.service.adminService;

import com.sieczka.model.Fixtures;
import com.sieczka.repository.adminRepository.FixturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patryk on 2018-01-14.
 */
@Service
public class FixturesServiceImpl implements FixturesService{
    @Autowired
    FixturesRepository fixturesRepository;

    @Override
    public List<Fixtures> findByGameWeek_LeagueType_LeagueTypeNameAndGameWeek_GameWeekNumber(String leagueTypeName, Integer gameWeekNumber) {
        List<Fixtures> fixtures = fixturesRepository.findByGameWeek_LeagueType_LeagueTypeNameAndGameWeek_GameWeekNumber(leagueTypeName, gameWeekNumber);
        return fixtures;
    }

    @Override
    public Fixtures findByHomeTeamNameAndAwayTeamName(String homeTeamName, String awayTeamName) {
        return fixturesRepository.findByHomeTeamNameAndAwayTeamName(homeTeamName, awayTeamName);
    }

    @Override
    public List<Fixtures> findAll() {
        List<Fixtures> resutl = fixturesRepository.findAll();
         return resutl;
    }
}
