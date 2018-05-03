package com.sieczka.controller.Player;

import com.sieczka.model.*;
import com.sieczka.repository.teamRepository.RostersRepository;
import com.sieczka.repository.teamRepository.TeamRepository;
import com.sieczka.service.UserService;
import com.sieczka.service.adminService.FootballersService;
import com.sieczka.service.teamService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Patryk on 2018-01-03.
 */
@RestController
@RequestMapping(value = "/team")
public class TeamController {


    @Autowired
    private TeamsService teamsService;

    @Autowired
    private CustomTeamsDetalisService teamsDetalisService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createTeam(@RequestBody TeamsCreator teamsCreator) {

        teamsDetalisService.createTeam(teamsCreator.username, teamsCreator.teamName, teamsCreator.leagueTypeName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);

    }

    static class TeamsCreator {
        public String username;
        public String teamName;
        public String leagueTypeName;
    }

    @RequestMapping(value = "/getallbyleague/{leagueName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Teams> getTeamsByLeague(@PathVariable String leagueName) {


        return teamsService.findAll()
                .stream()
                .filter(teams -> Objects.equals(teams.getTeamType().getLeagueTypeName(), leagueName))
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/getallbyusername/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Teams> getTeamsByUsername(@PathVariable String username) {


        return teamsService.findAll()
                .stream()
                .filter(teams -> Objects.equals(teams.getUser().getUsername(), username))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/getallbyleagueandusername/{leagueTypeName}/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Teams> getTeamsByLeagueAndUsername(@PathVariable String leagueTypeName, @PathVariable String username) {

        User user = userService.findByUsername(username);
        return teamsService.findByTeamType_LeagueTypeName(leagueTypeName)
                .stream()
                .filter(teams -> Objects.equals(teams.getUser(), user))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Teams> getAllTeams() {
        return this.teamsService.findAll();
    }



    @RequestMapping(value = "/getbank/{teamName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public Double getTeamBank(@PathVariable String teamName){
        Teams teams = teamsService.findByTeamsName(teamName);
        Double bank;
        bank = teams.getBank();
        bank = (double)Math.round(bank*100)/100;
        return bank;
    }



    @RequestMapping(value = "/addpoints", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPoints(@RequestBody AddPoints addPoints) {

        teamsDetalisService.addPoints(addPoints.leagueTypeName, addPoints.gameWeekNumber);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);

    }

    static class AddPoints{
        public String leagueTypeName;
        public Integer gameWeekNumber;
    }





}


