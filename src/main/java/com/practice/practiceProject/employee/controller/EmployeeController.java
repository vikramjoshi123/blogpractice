package com.practice.practiceProject.employee.controller;

import com.practice.practiceProject.employee.entity.EmployeeEntity;
import com.practice.practiceProject.utils.handler.ResponseHandler;
import com.practice.practiceProject.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employee")
    public ResponseEntity<Object> getAllEmployees() {
        try {
            List<EmployeeEntity> employeeEntityList = new ArrayList<>();
            employeeEntityList = employeeRepository.findAll();

            logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - EmployeeController - getAllEmployees - Response - " + employeeEntityList.toString());

            return ResponseHandler.response("Success", employeeEntityList, HttpStatus.OK);
        } catch (Error e) {
            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeEntity employee) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.save(employee);

            logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - EmployeeController - saveEmployee - Response - " + employeeEntity);

            return ResponseHandler.response("Success", employeeEntity, HttpStatus.OK);
        } catch (Error e) {

            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId) {
        try {
            if (employeeRepository.findById(employeeId).isPresent()) {
                employeeRepository.deleteById(employeeId);
                logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - EmployeeController - deleteEmployee - Response - " + "Deleted Successfully!");

                return ResponseHandler.response("Success", "Deleted Successfully!", HttpStatus.OK);
            } else {
                logger.info("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")) + " - EmployeeController - deleteEmployee - Response - " + "Already Deleted Or No data found!");

                return ResponseHandler.response("Already Deleted", "Already Deleted Or No data found!", HttpStatus.BAD_REQUEST);
            }

        } catch (Error e) {

            return ResponseHandler.response("Error", "", HttpStatus.NO_CONTENT);
        }
    }


}
