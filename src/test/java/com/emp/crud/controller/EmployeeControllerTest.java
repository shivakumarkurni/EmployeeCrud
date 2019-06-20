package com.emp.crud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.emp.crud.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EmployeeController.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	 @Autowired
	  private MockMvc mvc;
	 
	 @Autowired
	 private EmployeeController empController;
	 
	 @Autowired
	 private EmployeeServiceImpl employeeService;
	 
	// @Autowired
	// private WebApplicationContext wac;
	 
	 @Before
	 public void setup() {
	  //   this.mvc = MockMvcBuilders.webAppContextSetup();
	 }
	 
	 @Test
	 public void getEmployeeByIdAPI() throws Exception
	 {
	   mvc.perform( MockMvcRequestBuilders
	       .get("/employees/{id}", 1)
	       .accept(MediaType.APPLICATION_JSON))
	       .andDo(print())
	       .andExpect(status().isOk())
	       .andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(1));
	 }
	

}
