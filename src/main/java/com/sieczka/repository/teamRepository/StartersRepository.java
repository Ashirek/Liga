package com.sieczka.repository.teamRepository;

import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Patryk on 2018-02-05.
 */
public interface StartersRepository  extends JpaRepository<Starters,Long> {
    Starters findStartersByRosters(Rosters rosters);
}
