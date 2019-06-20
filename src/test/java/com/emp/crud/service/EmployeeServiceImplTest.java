package com.emp.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.emp.crud.dto.EmployeeDTO;
import com.emp.crud.exception.ApplicationException;
import com.emp.crud.model.Employee;
import com.emp.crud.repository.EmployeeRepository;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl empService;

	@Mock
	EmployeeRepository empRepo;
	
	
	  @Mock ModelMapper modelMapper;
	  
	//  @Before public void setUp() { modelMapper=new ModelMapper(); }
	 

	int expecteEmpCount = 3;

	/*
	 * @BeforeClass public void setUp() { Employee emp = new Employee();
	 * expecteEmpCount = 3; }
	 */

	@Test
	public void getEmpListTest() {

		// Mockito.when(empRepo.findAll().size()).thenReturn(expecteEmpCount);
		List<Employee> employeeList = new ArrayList<Employee>();

		// List<Employee> empList = empService.getEmpList();

		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpAddress("bengaluru");
		emp.setEmpContact("1313");
		emp.setEmpEmail("asdfsd@gmail.com");
		emp.setEmpName("Shiva");
		employeeList.add(emp);

		Employee emp2 = new Employee();
		emp2.setEmpId(1);
		emp2.setEmpAddress("bengaluru");
		emp2.setEmpContact("1313");
		emp2.setEmpEmail("asdfsd@gmail.com");
		emp2.setEmpName("Shiva");
		employeeList.add(emp2);

		Mockito.when(empRepo.findAll()).thenReturn(employeeList);

		List<EmployeeDTO> empList = empService.getEmpList();
		assertEquals(2, empList.size());
	}

	@Test
	public void getEmpByIdTest() {

		long id = 3;

		Employee emp = new Employee();

		emp.setEmpId(3);
		emp.setEmpAddress("bengaluru");
		emp.setEmpContact("1313");
		emp.setEmpEmail("asdfsd@gmail.com");
		emp.setEmpName("Shiva");
		
		EmployeeDTO actualEmp = new EmployeeDTO();

		actualEmp.setEmpId(3);
		actualEmp.setEmpAddress("bengaluru");
		actualEmp.setEmpContact("1313");
		actualEmp.setEmpEmail("asdfsd@gmail.com");
		actualEmp.setEmpName("Shiva");
		
		
		Mockito.when(modelMapper.map(emp,EmployeeDTO.class)).thenReturn(actualEmp);
		
		/*
		 * actualEmp.setEmpId(3); actualEmp.setEmpAddress("bengaluru");
		 * actualEmp.setEmpContact("1313"); actualEmp.setEmpEmail("asdfsd@gmail.com");
		 * actualEmp.setEmpName("Shiva");
*/		
		 

		Mockito.when(empRepo.findByEmpId(Mockito.anyLong())).thenReturn(emp);
	//	actualEmp =  modelMapper.map(emp, EmployeeDTO.class);
		
		actualEmp = empService.getEmpById(emp.getEmpId());
		
		System.out.println("actualEmp "+actualEmp.getEmpId());

		assertEquals(emp.getEmpId(), actualEmp.getEmpId());
	}

	@Test
	public void updateEmployeeDetails() {

		EmployeeDTO emp = new EmployeeDTO();

		emp.setEmpId(1);
		emp.setEmpAddress("bengaluru");
		emp.setEmpContact("1313");
		emp.setEmpEmail("asdfsd@gmail.com");
		emp.setEmpName("Shiva");

		Employee empEntity = new Employee();

		empEntity.setEmpId(1);
		empEntity.setEmpAddress("bengaluru");
		empEntity.setEmpContact("1313");
		empEntity.setEmpEmail("asdfsd@gmail.com");
		empEntity.setEmpName("Shiva");

		Mockito.when(empRepo.findById(emp.getEmpId())).thenReturn(Optional.of(empEntity));

		Mockito.when(empRepo.save(empEntity)).thenReturn(empEntity);


		String actualEmp = empService.updateEmpDetails(emp);

		System.out.println("actualEmp "+actualEmp);

		assertEquals("employee details updated successfully", actualEmp);

		assertNotEquals("employee details updated successfullyafadf", actualEmp);


	}

	@Test
	public void deleteEmpByIdTest() {

		Employee emp = new Employee();

		emp.setEmpId(1);
		emp.setEmpAddress("bengaluru");
		emp.setEmpContact("1313");
		emp.setEmpEmail("asdfsd@gmail.com");
		emp.setEmpName("Shiva");


		Mockito.when(empRepo.findById((long) 1)).thenReturn(Optional.of(emp));
		//Mockito.verify(empRepo.deleteById(1L));

		//		doNothing().when(empRepo).deleteById(1L);
		//		EmployeeRepository repo = Mockito.mock(EmployeeRepository.class);
		//Mockito.when(empRepo.deleteById(1L)).t;

		String actaul=empService.deleteEmpById(emp.getEmpId());
		assertEquals("Employee deleted successfully", actaul);

		String actual2=empService.deleteEmpById(34L);
	//	System.out.println(actual2);
		assertEquals("Employee with id 34 Doesn't exist", actual2);

		//empService.deleteEmpById(1);

	}
	/*
	 * @Test public void deleteEmpByIdFalseTest() {
	 * 
	 * Employee emp = new Employee();
	 * 
	 * emp.setEmpId(76L); emp.setEmpAddress("bengaluru"); emp.setEmpContact("1313");
	 * emp.setEmpEmail("asdfsd@gmail.com"); emp.setEmpName("Shiva");
	 * 
	 * 
	 * Mockito.when(empRepo.findById(emp.getEmpId())).thenReturn(Optional.of(emp));
	 * //Mockito.verify(empRepo.deleteById(1L));
	 * 
	 * // doNothing().when(empRepo).deleteById(1L); // EmployeeRepository repo =
	 * Mockito.mock(EmployeeRepository.class);
	 * //Mockito.when(empRepo.deleteById(1L)).t;
	 * 
	 * String actaul=empService.deleteEmpById(34L);
	 * assertEquals("Employee with id 34 Doesn't exist", actaul);
	 * 
	 * //empService.deleteEmpById(1);
	 * 
	 * }
	 */


	@Test
	public void createEmpTest() {

		Employee emp = new Employee();

		emp.setEmpId(1);
		emp.setEmpAddress("bengaluru");
		emp.setEmpContact("1313");
		emp.setEmpEmail("asdfsd@gmail.com");
		emp.setEmpName("Shiva");
		/*
		 * Mockito.when(empRepo.save(emp)).thenReturn(emp);
		 * 
		 * String actualEmp = empService.createEmp(emp);
		 * 
		 * assertEquals("Employee Created",actualEmp);
		 * 
		 * assertNotEquals("Employee Createdas",actualEmp);
		 */
	}
	
	
	/*
	 * @Test public void getEmpByIdTest1() {
	 * 
	 * long empId = 1234L; Employee emp = new Employee(); emp.setEmpId(empId);
	 * 
	 * Mockito.when(empRepo.findByEmpId(empId)).thenReturn(emp); EmployeeDTO empDto
	 * = empService.getEmpById(emp.getEmpId()); assertEquals(empId,
	 * empDto.getEmpId()); }
	 */


}
