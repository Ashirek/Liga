package com.sieczka.controller.Admin;

import com.sieczka.model.GameWeek;
import com.sieczka.service.adminService.CustomGameWeekDetailsService;
import com.sieczka.service.adminService.GameWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/gameweek")
public class GameWeekController {
    @Autowired
    private CustomGameWeekDetailsService gameWeekDetailsService;

    @Autowired
    private GameWeekService gameWeekService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<GameWeek> getAllGameWeek(){
        return this.gameWeekService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addGameWeek(@RequestBody GameWeekCreator gameWeekCreator){
        gameWeekDetailsService.addGameWeek(gameWeekCreator.gameWeekNumber, gameWeekCreator.leagueTypeName, gameWeekCreator.gameWeekStart, gameWeekCreator.gameWeekEnd);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @RequestMapping(value = "/getallbyleaguetypename/{leagueTypeName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<GameWeek> getGameWeeksByLeagueTypeName (@PathVariable String leagueTypeName){
        return this.gameWeekService.findGameWeekByLeagueType_LeagueTypeName(leagueTypeName);
    }

    static class GameWeekCreator{
        public Integer gameWeekNumber;
        public String leagueTypeName;
        public java.sql.Date gameWeekStart;
        public java.sql.Date gameWeekEnd;
    }
}
