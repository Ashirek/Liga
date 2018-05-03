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
@RequestMapping(value = "/starter")
public class StarterController {
    @Autowired
    private StartersService startersService;

    @Autowired
    private CustomRostersDetalisService rostersDetalisService;

    @Autowired
    private FootballersService footballersService;

    @Autowired
    private RostersService rostersService;

    @RequestMapping(value = "/getallbypostion/{teamName}/{position}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Footballers> getStartersByPosition(@PathVariable String teamName, @PathVariable String position){
        Integer size;
        Rosters rosters = this.rostersService.findByTeams_TeamName(teamName);

        Starters starters = this.startersService.findStartersByRosters(rosters);

        List<Footballers> result= footballersService.findByPositionAndStarters(position, starters);
        /*size = result.size();
        if (size == 0)
        {
            result = footballersService.findByPositionAndRosters(position, rosters);
        }*/
        return result;
    }

    @RequestMapping (value = "/set", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> setStarter(@RequestBody StartersCreator rostersCreator){
        rostersDetalisService.addRosterToStarters(rostersCreator.footballerLastNameAdd, rostersCreator.footballerLastNameDel, rostersCreator.teamName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    static  class StartersCreator {
        public String footballerLastNameAdd;
        public String footballerLastNameDel;
        public String teamName;
        public String leagueTypeName;
        public Integer gameWeekNumber;



    }
}
