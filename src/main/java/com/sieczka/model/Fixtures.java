package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name="Fixtures")
public class Fixtures {

    @Id
    @Column(name = "fixtures_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fixturesId;


    @Column(name = "home_team_name")
    private String homeTeamName;

   @Column(name = "away_team_name")
    private String awayTeamName;

   @Column (name = "home_team_goals")
    private Integer homeTeamGoals;

    @Column (name = "away_team_goals")
    private Integer awayTeamGoals;

    @Column (name = "game_time")
    private Date gameTime;

    @JsonManagedReference(value = "gameweektofixtures")
    @ManyToOne
    @JoinColumn(name = "game_week_id")
    private GameWeek gameWeek;

    public Fixtures() {
    }

    public Long getFixturesId() {
        return fixturesId;
    }

    public void setFixturesId(Long fixturesId) {
        this.fixturesId = fixturesId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public GameWeek getGameWeek() {
        return gameWeek;
    }

    public void setGameWeek(GameWeek gameWeek) {
        this.gameWeek = gameWeek;
    }
}