package EmployeesDetails.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "employees")
public class EmpDetails {
     @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
     @Column(unique = true)
	  @NotBlank(message = "Employee ID is mandatory")
     @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Employee ID must follow the required format")
	private String Emp_id; 
	
	  @NotBlank(message = "First name is mandatory")
	private String firstName; 
	  @NotBlank(message = "Last name is mandatory")
	private String lastName; 
	  @NotBlank(message = "Email is mandatory")
	  @Email(message = "Invalid email format")
	private String email;
	@ElementCollection
	 //@CollectionTable(name = "emp_details_phone_numbers", joinColumns = @JoinColumn(name = "Emp_id"))
	private List<String> phoneNumbers;
	 @NotNull(message = "Date of joining is mandatory")
	private String doj;

	    @NotNull(message = "Salary is mandatory")
	    @Positive(message = "Salary must be a positive number")
	private double salary;
	
	private double tax;
	
	
	// @JsonCreator //@JsonProperty("emp_id") String emp_id,
	public EmpDetails(
		 String emp_id,
			String firstName, 
			String lastName, 
			String email, 
			 List<String> phoneNumbers,
			 String doj, 
			 double salary) 
	{
		super();
		Emp_id = emp_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.doj = doj;
		this.salary = salary;
		this.tax = calculationTax(salary);
	}

	public EmpDetails(double tax) {
		super();
		this.tax = tax;
	}

	public EmpDetails(Long id) {
		super();
		this.id = id;
	}

	public String getEmp_id() {
		return Emp_id;
	}

	public void setEmp_id(String emp_id) {
		Emp_id = emp_id;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}


	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
		this.tax = calculationTax(salary);
	}
	
	private double calculationTax(double salary)
	{
		if(salary <= 250000)
		{
			return 0;
		}else if(salary <= 500000)
		{
			return salary * 0.05;
		}else if(salary  <= 1000000)
		{
			return salary * 0.10;
		}else
		{
			return salary * 0.20;
		}
		
		
	}

	
	
}
