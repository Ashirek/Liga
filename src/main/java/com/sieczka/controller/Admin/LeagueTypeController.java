package com.sieczka.controller.Admin;

import com.sieczka.model.LeagueType;
import com.sieczka.service.adminService.CustomLeagueTypeDetailsService;
import com.sieczka.service.adminService.LeagueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/leaguetype")
public class LeagueTypeController {
    @Autowired
    private CustomLeagueTypeDetailsService leagueTypeDetailsService;

    @Autowired
    private LeagueTypeService leagueTypeService;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addLeague(@RequestBody LeagueTypeCreator leagueTypeCreator) {

        leagueTypeDetailsService.addLeague(leagueTypeCreator.leagueTypeName);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }
    static class LeagueTypeCreator{
        public String leagueTypeName;

    }

    @RequestMapping(value="/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<LeagueType> getAllLeagueType(){
        return this.leagueTypeService.findAll();
    }
}
