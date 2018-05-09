package com.sieczka.repository.adminRepository;

import com.sieczka.model.GameWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Patryk on 2018-01-13.
 */
public interface GameWeekRepository extends JpaRepository<GameWeek,Long> {

    GameWeek findByGameWeekNumber(Integer gameWeekNumber);

}
