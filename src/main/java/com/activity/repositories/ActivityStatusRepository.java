package com.activity.repositories;

import com.activity.entities.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityStatusRepository extends JpaRepository<ActivityStatus, Long> {
    Optional<ActivityStatus> findByStatusName(String statusName);
}
