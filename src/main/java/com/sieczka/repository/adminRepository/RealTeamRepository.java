package com.sieczka.repository.adminRepository;

import com.sieczka.model.RealTeams;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Patryk on 2017-12-20.
 */
public interface RealTeamRepository extends JpaRepository<RealTeams, Long> {
    RealTeams findByRealTeamName(String realTeamName);
    RealTeams findByRealTeamId(Long RealTeamId);


}
