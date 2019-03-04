package com.business.world.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.world.dao.IDatabaseAccess;
import com.business.world.entity.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IDatabaseAccess daoAccess;

	@Override
	public Integer createEmployee(String id, EmployeeEntity emp) throws Exception {
		return daoAccess.createEmployee(id, emp);
		}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> empList = daoAccess.getAllEmployees();
		return empList;
	}

	public List<EmployeeEntity> getEmployeeById(String id) {
		List<EmployeeEntity> empObj = daoAccess.getEmployeeById(id);
		return empObj;
	}

	@Override
	public EmployeeEntity updateEmployee(String id, EmployeeEntity emp) throws Exception {
		return daoAccess.updateEmployee(id, emp);
	}

	@Override
	public void deleteEmployee(String id) throws Exception {
		daoAccess.deleteEmployee(id);

	}

}
