package com.sieczka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Rosters")

public class Rosters {

    @Id
    @Column(name="roster_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rosterId;


    @ManyToMany
    @JoinTable(
            name="footballers_in_roster",
            joinColumns = {@JoinColumn(name = "roster_id")},
            inverseJoinColumns = {@JoinColumn(name = "footballer_id")}
    )
    private List<Footballers> footballers;


    @JsonManagedReference(value = "temastorosters")
   @OneToOne
    //@JoinColumn(name = "teams_id")
    private Teams teams;

    @OneToOne(mappedBy = "rosters")
    private Starters starters;

    public Rosters() {
    }

    public Rosters(List<Footballers> footballers, GameWeek gameWeek, Teams teams) {
        this.footballers = footballers;
        this.teams = teams;
    }

    public long getRosterId() {
        return rosterId;
    }

    public void setRosterId(long rosterId) {
        this.rosterId = rosterId;
    }

    public List<Footballers> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballers> footballers) {
        this.footballers = footballers;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public Starters getStarters() {
        return starters;
    }

    public void setStarters(Starters starters) {
        this.starters = starters;
    }
}
