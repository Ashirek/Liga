package com.sieczka.controller.Admin;

import com.sieczka.model.Footballers;
import com.sieczka.service.adminService.CustomFootballersDetailsService;
import com.sieczka.service.adminService.FootballersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/footballer")
public class FootballerController {
    @Autowired
    private CustomFootballersDetailsService footballersDetailsService;

    @Autowired
    private FootballersService footballersService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Footballers> getAllFootballers(){
        return this.footballersService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addFootballer(@RequestBody FootballerCreator footballerCreator)    {
       String answer= footballersDetailsService.addFootballer(footballerCreator.footballersFirstName, footballerCreator.footballersLastName, footballerCreator.position, footballerCreator.realTeamName);
        Map<String, String> result = new HashMap<>();
        result.put("result", answer);
        return ResponseEntity.accepted().body(result);
    }

    static class FootballerCreator{
        public String footballersFirstName;
        public String footballersLastName;
        public String position;
        public String realTeamName;
    }

    @RequestMapping(value = "/getallbyleague/{leagueName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Footballers> getFootballersByLeague(@PathVariable String leagueName){
        return this.footballersService.findAll()
                .stream()
                .filter(footballers -> Objects.equals(footballers.getRealTeams().getRealTeamLeague().getLeagueTypeName(),leagueName))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/getallbyrealteam/{realTeamName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Footballers> getFootballersByRealTeam(@PathVariable String realTeamName){
        return this.footballersService.findAll()
                .stream()
                .filter(footballers -> Objects.equals(footballers.getRealTeams().getRealTeamName(),realTeamName))
                .collect(Collectors.toList());
    }


}
