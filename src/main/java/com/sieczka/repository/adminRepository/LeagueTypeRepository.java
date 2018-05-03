package com.sieczka.repository.adminRepository;

import com.sieczka.model.LeagueType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Patryk on 2017-12-09.
 */
public interface LeagueTypeRepository extends JpaRepository<LeagueType, Long> {
    LeagueType findByLeagueTypeName(String leagueTypeName);
    LeagueType findByLeagueTypeId(Long leagueTypeId);
}
