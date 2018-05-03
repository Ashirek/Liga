package com.sieczka.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patryk on 2018-02-05.
 */
@Entity
@Table(name= "Starters")
public class Starters {

    @Id
    @Column(name = "starters_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long startersId;

    @OneToOne
    private Rosters rosters;

    @ManyToMany
    @JoinTable(
            name="footballers_in_starters",
            joinColumns = {@JoinColumn(name = "starters_id")},
            inverseJoinColumns = {@JoinColumn(name = "footballer_id")}
    )
    private List<Footballers> footballersStarters;

    public Starters() {
    }

    public long getStartersId() {
        return startersId;
    }

    public void setStartersId(long startersId) {
        this.startersId = startersId;
    }

    public Rosters getRosters() {
        return rosters;
    }

    public void setRosters(Rosters rosters) {
        this.rosters = rosters;
    }

    public List<Footballers> getFootballersStarters() {
        return footballersStarters;
    }

    public void setFootballersStarters(List<Footballers> footballersStarters) {
        this.footballersStarters = footballersStarters;
    }
}

