package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name = "Game_week_footballer_stats")
public class FootballerStats {

    @Id
    @Column(name = "footballer_stats_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long footballerStatsId;


    @JsonManagedReference(value = "footballerstogameweekstats")
    @ManyToOne
    @JoinColumn(name="footballer_id")
    private Footballers footballers;

    @Column(name = "goals")
    private Integer goals;

    @Column(name = "assists")
    private Integer assists;

    @Column(name = "yellow_cards")
    private Integer yellowCards;

    @Column(name = "red_cards")
    private Integer redCards;

    @Column(name = "game_time")
    private Integer gameTime;

    @Column(name = "match_result")
    private String matchResult;

    @Column(name = "week_points")
    private Integer weekPoints;

    @JsonManagedReference(value = "gameweektogameweekstats")
    @ManyToOne
    @JoinColumn(name = "game_week_id")
    private GameWeek gameWeek;

    public FootballerStats() {
    }

    public Long getFootballerStatsId() {
        return footballerStatsId;
    }

    public void setFootballerStatsId(Long footballerStatsId) {
        this.footballerStatsId = footballerStatsId;
    }

    public Footballers getFootballers() {
        return footballers;
    }

    public void setFootballers(Footballers footballers) {
        this.footballers = footballers;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public GameWeek getGameWeek() {
        return gameWeek;
    }

    public void setGameWeek(GameWeek gameWeek) {
        this.gameWeek = gameWeek;
    }

    public Integer getWeekPoints() {
        return weekPoints;
    }

    public void setWeekPoints(Integer weekPoints) {
        this.weekPoints = weekPoints;
    }
}
