package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2017-11-29.
 */
@Entity
@Table(name="Real_teams")
public class RealTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="real_team_id")
    private Long realTeamId;

    @JsonBackReference(value = "realteamstofootballers")
    @OneToMany(mappedBy = "realTeams")
    private List<Footballers> footballers;

    @JsonManagedReference(value = "leaguetypetorealteams")
    @ManyToOne
    @JoinColumn(name = "real_league_id")
    private LeagueType realTeamLeague;

    @Column(name = "real_team_name")
    private String realTeamName;


    public RealTeams() {
    }

    public Long getRealTeamId() {
        return realTeamId;
    }

    public void setRealTeamId(Long realTeamId) {
        this.realTeamId = realTeamId;
    }

    public List<Footballers> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballers> footballers) {
        this.footballers = footballers;
    }

    public LeagueType getRealTeamLeague() {
        return realTeamLeague;
    }

    public void setRealTeamLeague(LeagueType realTeamLeague) {
        this.realTeamLeague = realTeamLeague;
    }

    public String getRealTeamName() {
        return realTeamName;
    }

    public void setRealTeamName(String realTeamName) {
        this.realTeamName = realTeamName;
    }

}

