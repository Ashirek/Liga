package com.sieczka.repository.teamRepository;

import com.sieczka.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Patryk on 2018-01-03.
 */
public interface TeamRepository extends JpaRepository<Teams, Long> {
    Teams findByTeamName(String teamName);


    List<Teams> findByUser_Username(String username);

}
