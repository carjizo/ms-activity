package com.activity.services;

import com.activity.dtos.ActivityCreateDTO;
import com.activity.dtos.ActivityRequestPaginationDTO;
import com.activity.dtos.ActivityUpdateDTO;
import com.activity.entities.Activity;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface ActivityService {

    public HashMap<String, Object> findAll() throws Exception;

    public HashMap<String, Object> findById(Long id) throws Exception;

    public HashMap<String, Object> create(ActivityCreateDTO createDTO) throws Exception;

    public HashMap<String, Object> update(ActivityUpdateDTO updateDTO) throws Exception;

    public HashMap<String, Object> deleteById(Long id) throws Exception;

    public HashMap<String, Object> findByIdDocument(String idDocument) throws Exception;

    public Page<Activity> listPagination(ActivityRequestPaginationDTO filtro);
    public List<Activity> allActivities();
}