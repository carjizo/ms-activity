package com.activity.controllers;

import com.activity.dtos.ActivityCreateDTO;
import com.activity.dtos.ActivityRequestPaginationDTO;
import com.activity.dtos.ActivityUpdateDTO;
import com.activity.entities.Activity;
import com.activity.services.ActivityService;
import com.activity.utils.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/all")
    private ResponseEntity<HashMap<String, Object>> getAll() throws Exception {
        return new ResponseEntity<>(activityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<HashMap<String, Object>> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(activityService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-document/{idDocument}")
    public ResponseEntity<HashMap<String, Object>> findByIdDocument(@PathVariable String idDocument) throws Exception {
        return new ResponseEntity<>(activityService.findByIdDocument(idDocument), HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<HashMap<String, Object>> create(@RequestBody ActivityCreateDTO createDTO) throws Exception {
        return new ResponseEntity<>(activityService.create(createDTO), HttpStatus.OK);
    }

    @PostMapping("/update")
    private ResponseEntity<HashMap<String, Object>> update(@RequestBody ActivityUpdateDTO updateDTO) throws Exception {
        HashMap<String, Object> rsp = activityService.update(updateDTO);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(activityService.deleteById(id), HttpStatus.OK);
    }

    @PostMapping("/list/pagination")
    public Page<Activity> listPagination(@RequestBody ActivityRequestPaginationDTO filtro) {
        return activityService.listPagination(filtro);
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=actividades_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Activity> activityList = activityService.allActivities();
        ExcelGenerator generator = new ExcelGenerator(activityList);
        generator.generateExcelFile(response);
    }
}
