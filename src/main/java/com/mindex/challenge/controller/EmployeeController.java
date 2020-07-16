package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.handler.EmployeeHandler;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeHandler employeeHandler;

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);
        return employeeService.create(employee);
    }

    @PostMapping("/employee/{id}/compensation")
    public Compensation create(@RequestBody Compensation compensation, @PathVariable String id) {
        return compensationService.create(compensation, id);
    }

    @GetMapping("employee/{id}/compensation")
    public Compensation readCompensation(@PathVariable String id) {
        return compensationService.read(id);
    }

    @GetMapping("/employee/{id}/directReports")
    public ReportingStructure directReports(@PathVariable String id) {
        LOG.debug("Received request for directReports for id [{}]", id);
        return employeeHandler.getDirectReports(id);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);
        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
}
