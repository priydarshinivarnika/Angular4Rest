package com.business.world.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.business.world.entity.AddressEntity;
import com.business.world.entity.EmployeeEntity;

public class ExcelReader {

	public static List<EmployeeEntity> excelToEntity() {
		List<EmployeeEntity> entityList = null;
		try {
			FileInputStream file = new FileInputStream(new File(
					"src/test/resources/EmployeeList.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			ArrayList<EmployeeEntity> employeeList = new ArrayList<>();
			// I've Header and I'm ignoring header for that I've +1 in loop
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				List<EmployeeEntity> e = new ArrayList<EmployeeEntity>();
				AddressEntity a = new AddressEntity();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						((EmployeeEntity) e).setFirstName(ce.getStringCellValue());
					}
					if (j == 1) {
						((EmployeeEntity) e).setId(ce.getStringCellValue());

					}
					if (j == 2) {
						((EmployeeEntity) e).setLastName(ce.getStringCellValue());
					}

					if (j == 3) {
						((EmployeeEntity) e).setSalary((int) ce.getNumericCellValue());
					}
					if (j == 4) {
						((AddressEntity) a).setCityName(ce.getStringCellValue());
					}
					if (j == 5) {
						((AddressEntity) a).setTelephoneNumber((long) ce.getNumericCellValue());
					}
					if (j == 6) {
						((AddressEntity) a).setZipCode((int) ce.getNumericCellValue());
					}

					if (j == 7) {
						((AddressEntity) a).setHouseNumber((int) ce.getNumericCellValue());
					}
					if (j == 8) {
						((AddressEntity) a).setStreetName(ce.getStringCellValue());
					}
					if (j == 9) {
						((AddressEntity) a).setAddressType(ce.getStringCellValue());
					}
					if (j == 10) {
						((AddressEntity) a).setStreetType(ce.getStringCellValue());
					}

				}
				((EmployeeEntity) e).setAddress(a);
				employeeList.addAll(e);

				

				file.close();
			}
			for (EmployeeEntity emp : employeeList) {
				System.out.println("ID:" + emp.getId() + " firstName:"
						+ emp.getFirstName() + " lastName:"
						+ emp.getLastName() + " salary:" + emp.getSalary()
						+ emp.getAddress().toString()+ "\n");
				entityList = (List<EmployeeEntity>) emp;
			}
			
		} catch (Exception e) {
			System.out.println("Incorrect datatype");
			e.printStackTrace();
		}
		return entityList;
	}
}
