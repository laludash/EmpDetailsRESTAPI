package EmployeesDetails.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EmployeesDetails.Entity.EmpDetails;
import EmployeesDetails.Service.EmployeesRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmpDetailsController {

	
	@Autowired
	EmployeesRepository er;
	
	
	@PostMapping("/employeestwo")
	 public ResponseEntity<EmpDetails> createEmployee(@RequestBody EmpDetails employee) {
		EmpDetails savedEmployee = er.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
	
	
	 @GetMapping("/employees/{Emp_id}/tax-deductions")
	 public ResponseEntity<List<EmpDetails>> getTaxDeductions(
	            @PathVariable String Emp_id)
	 {
		 List<EmpDetails> li = er.findByEmpId(Emp_id);
		 if (li.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
		 
		 return ResponseEntity.ok(li);

	 }
	 
	 @GetMapping("/employees/getEmpDetails")
	    public List<EmpDetails> getAllEmployees() {
			return (List<EmpDetails>) er.findAll();
		 
	 }

}
