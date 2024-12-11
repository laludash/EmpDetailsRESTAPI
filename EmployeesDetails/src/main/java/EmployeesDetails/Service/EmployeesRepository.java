package EmployeesDetails.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import EmployeesDetails.Entity.EmpDetails;
@Repository
public interface EmployeesRepository extends CrudRepository<EmpDetails, Long> {
  @Query(value = "select * from employees where Emp_id =? ", nativeQuery=true)
	List<EmpDetails> findByEmpId(String Emp_id);
}
