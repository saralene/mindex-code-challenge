package com.mindex.challenge.handler;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;

    public ReportingStructure getDirectReports(String employeeId) {
        Set<String> directReportIds = new HashSet<>();
        Employee employee = employeeService.read(employeeId);

        if(!CollectionUtils.isEmpty(employee.getDirectReports())) {
            getDirectReports(employee.getDirectReports(), directReportIds);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(directReportIds.size());

        return reportingStructure;
    }

    public void getDirectReports(List<Employee> employeeList, Set<String> directReportIds) {
        employeeList.stream().forEach(employee -> {
            directReportIds.add(employee.getEmployeeId());
            employee = employeeService.read(employee.getEmployeeId());
            List<Employee> directReports = employee.getDirectReports();
            if(!CollectionUtils.isEmpty(directReports)) {
                getDirectReports(directReports, directReportIds);
            }
        });
    }
}
