package com.sieczka.service.teamService;

import com.sieczka.model.*;
import com.sieczka.repository.adminRepository.FootballersRepository;
import com.sieczka.repository.adminRepository.GameWeekRepository;
import com.sieczka.repository.teamRepository.RostersRepository;
import com.sieczka.repository.teamRepository.StartersRepository;
import com.sieczka.repository.teamRepository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomRostersDetalisService {

    @Autowired
    private RostersRepository rostersRepository;

    @Autowired
    private TeamRepository teamRepository;


    @Autowired
    private FootballersRepository footballersRepository;

    @Autowired
    private StartersRepository startersRepository;


    public String addRoster(String footballerLastName, String teamName) {
        Boolean warunek =false;
        Boolean warunek2 =false;
        String answer;
        Rosters rosters = rostersRepository.findByTeams_TeamName(teamName);

        Double bank, price;
        if (rosters == null) {
            rosters = new Rosters();
            Footballers footballer = footballersRepository.findByFootballerLastName(footballerLastName);
            price = footballer.getPrice();
            Teams teams = teamRepository.findByTeamName(teamName);
            bank = teams.getBank();
            bank = bank - price;
            teams.setBank(bank);
            teamRepository.save(teams);
            List<Footballers> footballers = footballersRepository.findAll()
                    .stream()
                    .filter(footballers1 -> Objects.equals(footballers1.getFootballerLastName(), footballerLastName))
                    .collect(Collectors.toList());
            rosters.setFootballers(footballers);
            rosters.setTeams(teams);
            rostersRepository.save(rosters);
            answer = "success";

            Starters  starters = new Starters();
                List<Footballers> footballersStarters = footballersRepository.findAll()
                        .stream()
                        .filter(footballers1 -> Objects.equals(footballers1.getFootballerLastName(), footballerLastName))
                        .collect(Collectors.toList());

                starters.setFootballersStarters(footballersStarters);
                starters.setRosters(rosters);
            startersRepository.save(starters);
        }else {
            Footballers footballer = footballersRepository.findByFootballerLastNameAndRosters(footballerLastName, rosters);
            if (footballer == null) {
                Integer size;
                footballer = footballersRepository.findByFootballerLastName(footballerLastName);
                List<Footballers> test = footballersRepository.findByRostersAndRealTeams_RealTeamName(rosters, footballer.getRealTeams().getRealTeamName());
                size = test.size();
                if (size <3)
                {
                    String position = footballer.getPosition();
                    test = footballersRepository.findByPositionAndRosters(position, rosters);

                    size = test.size();
                    switch (position) {
                        case "Bramkarz": {
                            if (size < 2)
                                warunek = true;
                            if (size < 1)
                                warunek2 = true;
                            break;
                        }
                        case "Obronca": {
                            if (size < 6)
                                warunek = true;
                            if (size < 4)
                                warunek2 = true;
                            break;
                        }
                        case "Pomocnik": {
                            if (size < 6)
                                warunek = true;
                            if (size < 4)
                                warunek2 = true;
                            break;
                        }
                        case "Napastnik": {
                            if (size < 3)
                                warunek = true;
                            if (size < 2)
                                warunek2 = true;
                            break;
                        }
                    }
                    if (warunek) {

                        Teams teams = teamRepository.findByTeamName(teamName);
                        bank = teams.getBank();
                        List<Footballers> footballers = rosters.getFootballers();
                        footballers.add(footballer);
                        price = footballer.getPrice();
                        bank = bank - price;
                        if (bank < 0) {
                            answer = "error";
                        } else {
                            answer = "success";
                            teams.setBank(bank);
                            teamRepository.save(teams);
                            rosters.setFootballers(footballers);
                            rostersRepository.save(rosters);
                            if (warunek2) {
                                Starters starters = startersRepository.findStartersByRosters(rosters);
                                List<Footballers> footballersStarters = starters.getFootballersStarters();
                                footballersStarters.add(footballer);
                                starters.setFootballersStarters(footballersStarters);
                               // startersRepository.save(starters);
                            }
                        }
                    } else {
                        answer = "error";

                    }
                }
                else
                {
                    answer = "error";
                }



            } else {
                answer = "error";
            }
        }
            return answer;

        }
    public void deleteRoster(String footballerLastName, String teamName){
        Rosters rosters = rostersRepository.findByTeams_TeamName(teamName);
        Double bank,price;

        List<Footballers> footballers =rosters.getFootballers();
        Footballers footballer = footballersRepository.findByFootballerLastName(footballerLastName);
        Teams teams = teamRepository.findByTeamName(teamName);
        bank = teams.getBank();
        if (footballer !=null)
        {
            price=footballer.getPrice();
            bank = bank+price;
            footballers.remove(footballer);
        }
        teams.setBank(bank);
        teamRepository.save(teams);
        rosters.setFootballers(footballers);
        rostersRepository.save(rosters);
    }

    public void addRosterToStarters(String footballerLastNameAdd, String footballerLastNameDel, String teamName) {
        Rosters rosters = rostersRepository.findByTeams_TeamName(teamName);
        Starters starters = startersRepository.findStartersByRosters(rosters);
        Footballers footballerAdd = footballersRepository.findByFootballerLastName(footballerLastNameAdd);
        Footballers footballerDel = footballersRepository.findByFootballerLastName(footballerLastNameDel);
        if(starters == null){
            starters = new Starters();
            List<Footballers> footballersStarters = footballersRepository.findAll()
                    .stream()
                    .filter(footballers1 -> Objects.equals(footballers1.getFootballerLastName(), footballerLastNameAdd))
                    .collect(Collectors.toList());

            starters.setFootballersStarters(footballersStarters);
            starters.setRosters(rosters);
            } else {
        List<Footballers> footballersStarters = starters.getFootballersStarters();
        footballersStarters.add(footballerAdd);
        if (footballerDel != null)
        {
            footballersStarters.remove(footballerDel);
        }
        starters.setFootballersStarters(footballersStarters);


            }

        startersRepository.save(starters);

    }

}
