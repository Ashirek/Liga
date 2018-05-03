package com.sieczka.controller.Player;

import com.sieczka.model.Footballers;
import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;
import com.sieczka.service.adminService.FootballersService;
import com.sieczka.service.teamService.CustomRostersDetalisService;
import com.sieczka.service.teamService.RostersService;
import com.sieczka.service.teamService.StartersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/roster")
public class RosterController {

    @Autowired
    private CustomRostersDetalisService rostersDetalisService;

    @Autowired
    private RostersService rostersService;

    @Autowired
    private StartersService startersService;

    @Autowired
    private FootballersService footballersService;

    @RequestMapping(value = "/getallbyteam/{teamName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Footballers> getRostersByTeam(@PathVariable String teamName){

        Rosters rosters = this.rostersService.findByTeams_TeamName(teamName);
        List<Footballers> result = rosters.getFootballers();
        return result;

    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Rosters> getAllRosters(){
        return this.rostersService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity<?> addRoster(@RequestBody RostersCreator rostersCreator){
        String answer;
        Map<String, String> result = new HashMap<>();
        answer =rostersDetalisService.addRoster(rostersCreator.footballerLastNameAdd, rostersCreator.teamName);
        result.put("result", answer);
        return ResponseEntity.accepted().body(result);
    }

    @RequestMapping(value = "/del", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteRoster(@RequestBody RostersCreator rostersCreator){
        rostersDetalisService.deleteRoster(rostersCreator.footballerLastNameDel, rostersCreator.teamName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    static  class RostersCreator {
        public String footballerLastNameAdd;
        public String footballerLastNameDel;
        public String teamName;
    }

    @RequestMapping(value = "/getallbyposition/{teamName}/{position}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Footballers> getRostersByPositions(@PathVariable String teamName, @PathVariable String position){
        Rosters rosters = this.rostersService.findByTeams_TeamName(teamName);
        Starters starters = this.startersService.findStartersByRosters(rosters);
        List<Footballers> result = footballersService.findByPositionAndRosters(position, rosters);
        result.removeAll(starters.getFootballersStarters());
        return result;
    }
}
