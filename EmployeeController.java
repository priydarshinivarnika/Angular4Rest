package com.business.world.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.business.world.entity.EmployeeEntity;
import com.business.world.service.IEmployeeService;
import com.business.world.util.ExcelReader;
import com.business.world.util.JsonReader;
import com.google.gson.Gson;

@RestController
public class EmployeeController {

	@Autowired
	private IEmployeeService empService;

	@GetMapping("/hello")
	public String hello() {
		return "Welcome to Spring Boot Project";
	}

	@PostMapping(value = "/insertEmployee")
	@ResponseBody
	public String insertRecord(@RequestBody String json) {
		System.out.println("------------Inside insertRecord----------");
		EmployeeEntity emp = JsonReader.jsonToEmployee(json);

		System.out.println(emp);
		String tempId = emp.getId();
		try {

			empService.createEmployee(tempId, emp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Employee -" + emp.getId() + "- exists in  record already";
		}
		return "Employee " + emp.getId() + " Inserted Successfully";

		/*
		 * System.out.println("Employee created : " + emp.getFirstName());
		 * empService.createEmployee(emp);
		 */

	}

	@GetMapping(value = "/getEmployee", produces = "application/json")
	@ResponseBody
	public List<String> getRecord() {
		System.out.println("-----------Inside getRecord------------");
		List<EmployeeEntity> empList = empService.getAllEmployees();
		List<String> emp = JsonReader.employeeToJson(empList);
		return emp;
	}

	@GetMapping(value = "/getEmployee/{id}")
	@ResponseBody
	public String getRecordById(@PathVariable("id") String id) {
		System.out.println("-----------Inside getRecord By Id------------");
		List<EmployeeEntity> empList = empService.getEmployeeById(id);
		List<String> emp = JsonReader.employeeToJson(empList);
		System.out.println("Found list from database : " + emp);
		return emp.get(0);
	}

	@PutMapping(value = "/updateEmployee/{id}", produces = "application/json")
	@ResponseBody
	public String updateRecord(@PathVariable("id") String id, @RequestBody String json) {
		System.out.println("---------Inside update Record-----------");
		EmployeeEntity emp = JsonReader.jsonToEmployee(json);

		System.out.println(emp);
		EmployeeEntity updatedEntity = null;
		try {
			updatedEntity = empService.updateEmployee(id, emp);
		} catch (Exception e) {
			e.printStackTrace();
			return "Employee -" + emp.getId() + "- could not be found in our records";
		}
		return new Gson().toJson(updatedEntity);
	}

	@DeleteMapping(value = "/deleteEmployee/{id}")
	public String deleteRecord(@PathVariable("id") String id) throws Exception {
		System.out.println("---------Inside delete Record by id-----------");
		empService.deleteEmployee(id);
		return "Employee -" + id + "- deleted";

	}
	
	
	
	//-----------------Methods From Excel--------------------//
	
	
	@PostMapping(value = "/insertExcelRecords")
	@ResponseBody
	public String insertRecordFromExcel() {
		System.out.println("------------insertRecordFromExcel----------");
		List<EmployeeEntity> emp = ExcelReader.excelToEntity();

		System.out.println(emp);
		String tempId = ((EmployeeEntity) emp).getId();
		try {

			empService.createEmployee(tempId, emp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Employee -" + emp.getId() + "- exists in  record already";
		}
		return "Employee " + emp.getId() + " Inserted Successfully";

		/*
		 * System.out.println("Employee created : " + emp.getFirstName());
		 * empService.createEmployee(emp);
		 */

	}
}
