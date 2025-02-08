package com.activity.services;

import com.activity.dtos.ActivityStatusAllDTO;
import com.activity.entities.ActivityStatus;

import java.util.List;
import java.util.Optional;

public interface ActivityStatusService {
    List<ActivityStatusAllDTO> findAll();
    Optional<ActivityStatus> findByStatusName(String statusName);
}
