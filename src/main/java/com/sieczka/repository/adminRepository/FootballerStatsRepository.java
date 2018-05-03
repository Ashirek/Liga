package com.sieczka.repository.adminRepository;

import com.sieczka.model.FootballerStats;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Patryk on 2018-02-19.
 */
public interface FootballerStatsRepository extends JpaRepository<FootballerStats, Long> {
   FootballerStats findByGameWeek_GameWeekNumberAndFootballers_FootballerLastName(Integer gameWeekNumber, String footballerLastName);
}
