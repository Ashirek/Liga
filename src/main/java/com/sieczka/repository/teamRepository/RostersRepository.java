package com.sieczka.repository.teamRepository;

import com.sieczka.model.Rosters;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Patryk on 2018-01-16.
 */
public interface RostersRepository extends JpaRepository<Rosters,Long> {
    Rosters findByTeams_TeamName(String teamName);

}
