package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name="Teams")
public class Teams {

    @Id
    @Column(name="team_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;

    @JsonManagedReference(value = "leaguetoteams")
   @ManyToOne
   @JoinColumn(name = "league_id")
   private League league;

    @JsonManagedReference(value = "userstoteams")
    @ManyToOne
    @JoinColumn(name="manager_id")
    private User user;


    @Column(name="team_name")
    private String teamName;

    @JsonManagedReference(value = "leaguetypetoteams")
    @ManyToOne
    @JoinColumn(name = "team_type")
    private LeagueType teamType;

    @JsonBackReference(value = "temastorosters")
    @OneToOne(mappedBy = "teams")
    private Rosters rosters;

    @Column(name = "bank")
    private Double bank;

    @Column(name = "team_points")
    private Integer teamPoints;

    public Teams() {
    }



    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LeagueType getTeamType() {
        return teamType;
    }

    public void setTeamType(LeagueType teamType) {
        this.teamType = teamType;
    }

    public Rosters getRosters() {
        return rosters;
    }

    public void setRosters(Rosters rosters) {
        this.rosters = rosters;
    }

    public Double getBank() {
        return bank;
    }

    public void setBank(Double bank) {
        this.bank = bank;
    }

    public Integer getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(Integer teamPoints) {
        this.teamPoints = teamPoints;
    }
}
