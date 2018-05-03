package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2017-11-23.
 */
@Entity
@Table(name="Footballers")
public class Footballers {

    @Id
    @Column(name="footballer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long footballerId;


    @JsonIgnore
    @ManyToMany(mappedBy = "footballers")
    private  List<Rosters> rosters;

    @JsonManagedReference(value = "realteamstofootballers")
    @ManyToOne
    @JoinColumn(name = "real_team_id")
    private RealTeams realTeams;

    @JsonBackReference(value = "footballerstogameweekstats")
    @OneToMany(mappedBy = "footballers")
    private List<FootballerStats> footballerStats;

    @Column(name = "footballer_first_name")
    private String footballerFirstName;

    @Column(name = "footballer_last_Name")
    private String footballerLastName;

    @Column(name = "position")
    private String position;

    @Column(name = "price")
    private Double price;

    @Column(name = "points")
    private Integer points;


    @JsonIgnore
    @ManyToMany(mappedBy = "footballersStarters")
    private  List<Starters> starters;



    public Footballers() {
    }

    public Long getFootballerId() {
        return footballerId;
    }

    public void setFootballerId(Long footballerId) {
        this.footballerId = footballerId;
    }

    public List<Rosters> getRosters() {
        return rosters;
    }

    public void setRosters(List<Rosters> rosters) {
        this.rosters = rosters;
    }

    public RealTeams getRealTeams() {
        return realTeams;
    }

    public void setRealTeams(RealTeams realTeams) {
        this.realTeams = realTeams;
    }

    public List<FootballerStats> getFootballerStats() {
        return footballerStats;
    }

    public void setFootballerStats(List<FootballerStats> footballerStats) {
        this.footballerStats = footballerStats;
    }

    public String getFootballerFirstName() {
        return footballerFirstName;
    }

    public void setFootballerFirstName(String footballerFirstName) {
        this.footballerFirstName = footballerFirstName;
    }

    public String getFootballerLastName() {
        return footballerLastName;
    }

    public void setFootballerLastName(String footballerLastName) {
        this.footballerLastName = footballerLastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Starters> getStarters() {
        return starters;
    }

    public void setStarters(List<Starters> starters) {
        this.starters = starters;
    }
}
