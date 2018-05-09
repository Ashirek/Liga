package com.sieczka.controller.Admin;

import com.sieczka.model.RealTeams;
import com.sieczka.service.adminService.CustomRealTeamDetailsService;
import com.sieczka.service.adminService.RealTeamService;
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
@RequestMapping(value="/realteam")
public class RealTeamController {

    @Autowired
    private CustomRealTeamDetailsService realTeamTypeDetailsService;

    @Autowired
    private RealTeamService realTeamService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTeam(@RequestBody RealTeamCreator realTeamCreator){
        realTeamTypeDetailsService.addTeam(realTeamCreator.realTeamName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    static class RealTeamCreator{
        public String realTeamName;
        public String leagueTypeName;
    }


    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<RealTeams> getAllRealTeams(){
        return this.realTeamService.findAll();
    }

    @RequestMapping(value = "/getallbyleaguename/{leagueName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<RealTeams> getRealTeamsByLeagueName(@PathVariable String leagueName){
        return this.realTeamService.findAll()
                .stream()
                .filter(realTeams -> Objects.equals(realTeams.getRealTeamLeague().getLeagueTypeName(),leagueName))
                .collect(Collectors.toList());
    }

}
