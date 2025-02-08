package com.activity.services.impl;

import com.activity.dtos.ActivityCreateDTO;
import com.activity.dtos.ActivityRequestPaginationDTO;
import com.activity.dtos.ActivityResponseDTO;
import com.activity.dtos.ActivityUpdateDTO;
import com.activity.entities.Activity;
import com.activity.entities.ActivitySpecifications;
import com.activity.repositories.ActivityRepository;
import com.activity.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public HashMap<String, Object> findAll() throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Activity> activitiesdb = activityRepository.findAll();
            List<ActivityResponseDTO> activityResponseDTOS = activitiesdb
                    .stream()
                    .map(activity -> ActivityResponseDTO.builder()
                            .id(activity.getId())
                            .idDocument(activity.getIdDocument())
                            .fullName(activity.getFullName())
                            .clientFullName(activity.getClientFullName())
                            .clientPhone(activity.getClientPhone())
                            .amount(activity.getAmount())
                            .customerPayment(activity.getCustomerPayment())
                            .description(activity.getDescription())
                            .note(activity.getNote())
                            .status(activity.getStatus())
                            .build())
                    .toList();

            response.put("message", "Succes");
            response.put("response", activityResponseDTOS);
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> findById(Long id) throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        try {
            Optional<Activity> activitydb = activityRepository.findById(id);
            Optional<ActivityResponseDTO> activityResponseDTO = Optional.ofNullable(ActivityResponseDTO.builder()
                    .id(activitydb.get().getId())
                    .idDocument(activitydb.get().getIdDocument())
                    .fullName(activitydb.get().getFullName())
                    .clientFullName(activitydb.get().getClientFullName())
                    .clientPhone(activitydb.get().getClientPhone())
                    .amount(activitydb.get().getAmount())
                    .customerPayment(activitydb.get().getCustomerPayment())
                    .description(activitydb.get().getDescription())
                    .note(activitydb.get().getNote())
                    .status(activitydb.get().getStatus())
                    .build());
            response.put("message", "Succes");
            response.put("response", activityResponseDTO);
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }

    }

    @Override
    public HashMap<String, Object> create(ActivityCreateDTO createDTO) throws Exception{
        HashMap<String, Object> response = new HashMap<>();
        try {
            Activity activity = Activity.builder()
                    .idDocument(createDTO.getIdDocument())
                    .fullName(createDTO.getFullName().toUpperCase())
                    .clientFullName(createDTO.getClientFullName().toUpperCase())
                    .clientPhone(createDTO.getClientPhone())
                    .amount(createDTO.getAmount())
                    .customerPayment(createDTO.getCustomerPayment())
                    .description(createDTO.getDescription())
                    .note(createDTO.getNote())
                    .status(createDTO.getStatus().toUpperCase())
                    .build();
            activityRepository.save(activity);
            response.put("message", "Activity created successfully");
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }

    }

    @Override
    public HashMap<String, Object> update(ActivityUpdateDTO updateDTO) throws Exception{
        HashMap<String, Object> response = new HashMap<>();
        try {
            Optional<Activity> activitydb = activityRepository.findById(updateDTO.getId());
            if (activitydb.isEmpty()) {
                response.put("message", "Activity not registered");
                response.put("isSucces", false);
                return response;
            }

            activityRepository.update(
                    updateDTO.getId(), updateDTO.getIdDocument(), updateDTO.getFullName().toUpperCase(),
                    updateDTO.getClientFullName().toUpperCase(), updateDTO.getClientPhone(),
                    updateDTO.getAmount(), updateDTO.getCustomerPayment(),
                    updateDTO.getDescription(), updateDTO.getNote(), updateDTO.getStatus()
            );
            response.put("message", "Activity updated successfully");
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }

    }

    @Override
    public HashMap<String, Object> deleteById(Long id) throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        try {
            activityRepository.deleteById(id);
            response.put("message", "Successfully deleted activity");
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }

    }

    @Override
    public HashMap<String, Object> findByIdDocument(String idDocument) throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        try {
            Optional<Activity> activitydb = activityRepository.findByIdDocument(idDocument);
            Optional<ActivityResponseDTO> activityResponseDTO = Optional.ofNullable(ActivityResponseDTO.builder()
                    .id(activitydb.get().getId())
                    .idDocument(activitydb.get().getIdDocument())
                    .fullName(activitydb.get().getFullName())
                    .clientFullName(activitydb.get().getClientFullName())
                    .clientPhone(activitydb.get().getClientPhone())
                    .amount(activitydb.get().getAmount())
                    .customerPayment(activitydb.get().getCustomerPayment())
                    .description(activitydb.get().getDescription())
                    .note(activitydb.get().getNote())
                    .status(activitydb.get().getStatus())
                    .build());
            response.put("message", "Succes");
            response.put("response", activityResponseDTO);
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
            return response;
        }

    }

    @Override
    public Page<Activity> listPagination(ActivityRequestPaginationDTO filtro) {
        Pageable pageable = PageRequest.of(filtro.getPage(), filtro.getSize());
        return activityRepository.findAll(ActivitySpecifications.filtrarPorCriterios(filtro),pageable);
    }

    @Override
    public List<Activity> allActivities() {
        return activityRepository.findAll();
    }
}
