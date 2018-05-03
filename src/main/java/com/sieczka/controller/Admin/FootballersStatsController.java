package com.sieczka.controller.Admin;

import com.sieczka.service.adminService.CustomFootballerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patryk on 2018-02-20.
 */
@RestController
@RequestMapping(value = "/footballerstats")
public class FootballersStatsController {

    @Autowired
    public CustomFootballerStatsService footballerStatsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addFootballerStats(@RequestBody  FootballerStatsCreator footballerStatsCreator){
        footballerStatsService.addFootballerStats(footballerStatsCreator.footballerLastName,footballerStatsCreator.realTeamName,
                footballerStatsCreator.leagueTypeName, footballerStatsCreator.gameWeekNumber,footballerStatsCreator.goals,
                footballerStatsCreator.assists, footballerStatsCreator.yellowCards, footballerStatsCreator.redCards,
                footballerStatsCreator.gameTime, footballerStatsCreator.matchResult);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);

    }

    static class FootballerStatsCreator{
        public String footballerLastName;
        public String realTeamName;
        public String leagueTypeName;
        public Integer gameWeekNumber;
        public Integer goals;
        public Integer assists;
        public Integer yellowCards;
        public Integer redCards;
        public Integer gameTime;
        public String matchResult;
    }
}
