package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name="game_week")
public class GameWeek {

    @Id
    @Column(name="game_week_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameWeekId;

    @Column(name = "game_week_number")
    private Integer gameWeekNumber;

    @Column(name="game_week_start")
    private Date gameWeekStart;

    @Column(name="game_week_end")
    private Date gameWeekEnd;


    @JsonBackReference(value = "gameweektogameweekstats")
    @OneToMany(mappedBy = "gameWeek")
    private List<FootballerStats> footballerStats;

    @JsonBackReference(value = "gameweektofixtures")
    @OneToMany(mappedBy = "gameWeek")
    private List<Fixtures> fixtures;

    @JsonManagedReference(value = "leaguetypetogameweek")
    @ManyToOne
    @JoinColumn(name = "league_type_id")
    private LeagueType leagueType;

    public GameWeek() {
    }


    public GameWeek(Integer gameWeekNumber, java.sql.Date gameWeekStart, java.sql.Date gameWeekEnd, LeagueType leagueType) {
        this.gameWeekNumber = gameWeekNumber;
        this.gameWeekStart = gameWeekStart;
        this.gameWeekEnd = gameWeekEnd;
        this.leagueType = leagueType;
    }

    public Long getGameWeekId() {
        return gameWeekId;
    }

    public void setGameWeekId(Long gameWeekId) {
        this.gameWeekId = gameWeekId;
    }

    public Integer getGameWeekNumber() {
        return gameWeekNumber;
    }

    public void setGameWeekNumber(Integer gameWeekNumber) {
        this.gameWeekNumber = gameWeekNumber;
    }

    public Date getGameWeekStart() {
        return gameWeekStart;
    }

    public void setGameWeekStart(java.sql.Date gameWeekStart) {
        this.gameWeekStart = gameWeekStart;
    }

    public Date getGameWeekEnd() {
        return gameWeekEnd;
    }

    public void setGameWeekEnd(java.sql.Date gameWeekEnd) {
        this.gameWeekEnd = gameWeekEnd;
    }


    public List<FootballerStats> getFootballerStats() {
        return footballerStats;
    }

    public void setGameWeekFootballerStats(List<FootballerStats> footballerStats) {
        this.footballerStats = footballerStats;
    }

    public List<Fixtures> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixtures> fixtures) {
        this.fixtures = fixtures;
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }
}
