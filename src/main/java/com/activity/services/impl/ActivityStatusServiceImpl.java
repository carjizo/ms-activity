package com.activity.services.impl;

import com.activity.dtos.ActivityStatusAllDTO;
import com.activity.entities.ActivityStatus;
import com.activity.repositories.ActivityStatusRepository;
import com.activity.services.ActivityStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityStatusServiceImpl implements ActivityStatusService {
    @Autowired
    ActivityStatusRepository statusRepository;

    @Override
    public List<ActivityStatusAllDTO> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(activityStatus -> ActivityStatusAllDTO.builder()
                        .id(activityStatus.getId())
                        .statusName(activityStatus.getStatusName())
                        .build())
                .toList();
    }

    @Override
    public Optional<ActivityStatus> findByStatusName(String statusName) {
        return statusRepository.findByStatusName(statusName);
    }
}
