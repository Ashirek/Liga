package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2017-11-29.
 */
@Entity
@Table(name="League_type")
public class LeagueType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="league_type_id")
    private Long leagueTypeId;

    @Column(name = "league_type_name")
    private String leagueTypeName;

    @JsonBackReference(value = "leaguetypetoleague")
    @OneToMany(mappedBy = "leagueType2")
    private List<League> league;

    @JsonBackReference(value = "leaguetypetoteams")
    @OneToMany(mappedBy = "teamType")
    private List<Teams> teams;

    @JsonBackReference(value = "leaguetypetorealteams")
    @OneToMany(mappedBy = "realTeamLeague")
    private List<RealTeams> realTeams;

    @JsonBackReference(value = "leaguetypetogameweek")
    @OneToMany (mappedBy = "leagueType")
    private List<GameWeek> gameWeek;

    public LeagueType() {
    }

    public LeagueType(String leagueTypeName, List<League> league, List<Teams> teams, List<RealTeams> realTeams) {
        this.leagueTypeName = leagueTypeName;
        this.league = league;
        this.teams = teams;
        this.realTeams = realTeams;
    }

    public Long getLeagueTypeId() {
        return leagueTypeId;
    }

    public void setLeagueTypeId(Long leagueTypeId) {
        this.leagueTypeId = leagueTypeId;
    }

    public String getLeagueTypeName() {
        return leagueTypeName;
    }

    public void setLeagueTypeName(String leagueTypeName) {
        this.leagueTypeName = leagueTypeName;
    }

    public List<League> getLeague() {
        return league;
    }

    public void setLeague(List<League> league) {
        this.league = league;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }

    public List<RealTeams> getRealTeams() {
        return realTeams;
    }

    public void setRealTeams(List<RealTeams> realTeams) {
        this.realTeams = realTeams;
    }

    public List<GameWeek> getGameWeek() {
        return gameWeek;
    }

    public void setGameWeek(List<GameWeek> gameWeek) {
        this.gameWeek = gameWeek;
    }
}
