package com.sieczka.service.adminService;

import com.sieczka.model.Footballers;
import com.sieczka.model.RealTeams;
import com.sieczka.repository.adminRepository.FootballersRepository;
import com.sieczka.repository.adminRepository.RealTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2018-01-02.
 */
@Service
public class CustomFootballersDetailsService {

    @Autowired
    private FootballersRepository footballersRepository;

    @Autowired
    private FootballersService footballersService;

    @Autowired
    private RealTeamRepository realTeamRepository;

    @Autowired
    private  RealTeamService realTeamService;

    public String addFootballer(String footballerFirstName, String footballerLastName, String position, String realTeamName){
        String answer;
        Footballers footballers = footballersService.findByFootballerFirstName(footballerFirstName);
        RealTeams realTeams = realTeamService.findByRealTeamName(realTeamName);

        if (footballers != null)
        {
            answer = "error";

        }
        else{
            answer = "succes";
            footballers = new Footballers();
            footballers.setFootballerFirstName(footballerFirstName);
            footballers.setFootballerLastName(footballerLastName);
            footballers.setPosition(position);
            footballers.setRealTeams(realTeams);
            footballersRepository.save(footballers);

        }
        return answer;

    }
}
