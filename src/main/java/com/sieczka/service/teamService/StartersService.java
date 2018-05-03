package com.sieczka.service.teamService;

import com.sieczka.model.Rosters;
import com.sieczka.model.Starters;

/**
 * Created by Patryk on 2018-02-05.
 */
public interface StartersService {
    Starters findStartersByRosters(Rosters rosters);

}
