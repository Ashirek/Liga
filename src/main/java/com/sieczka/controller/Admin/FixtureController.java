package com.sieczka.controller.Admin;

import com.sieczka.model.Fixtures;
import com.sieczka.service.adminService.CustomFixturesDetailsService;
import com.sieczka.service.adminService.FixturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patryk on 2018-02-11.
 */
@RestController
@RequestMapping(value="/fixture")
public class FixtureController {
    @Autowired
    private CustomFixturesDetailsService fixturesDetailsService;
    @Autowired
    private FixturesService fixturesService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addFixture(@RequestBody FixtureCreator fixtureCreator){
        fixturesDetailsService.addFixture(fixtureCreator.homeTeamName, fixtureCreator.awayTeamName, fixtureCreator.gameTime, fixtureCreator.gameWeekNumber,fixtureCreator.leagueTypeName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putFixture(@RequestBody FixtureCreator fixtureCreator){
        fixturesDetailsService.putFixture(fixtureCreator.homeTeamName, fixtureCreator.awayTeamName, fixtureCreator.homeTeamGoals,fixtureCreator.awayTeamGoals);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @RequestMapping(value = "/getallby/{leagueName}/{gameWeekNumber}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Fixtures>  getFixturesByLeagueNameAndGameWeekNumber (@PathVariable String leagueName, @PathVariable Integer gameWeekNumber){

        return fixturesService.findByGameWeek_LeagueType_LeagueTypeNameAndGameWeek_GameWeekNumber(leagueName, gameWeekNumber);
    }

    static class FixtureCreator{
        public String homeTeamName;
        public String awayTeamName;
        public Date gameTime;
        public Integer gameWeekNumber;
        public String leagueTypeName;
        public Integer homeTeamGoals;
        public Integer awayTeamGoals;
    }

}
