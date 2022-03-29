package com.emppayrolljavaio;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.emppayrolljavaio.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {
	
	/**
	 * Method to find the number of entries given3Employees here When Written To
	 * File Should Match the EmployeeEntries
	 */
	@Test
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {

		/**
		 * created the array of employee Payroll data to store the entries
		 */
		EmployeePayrollData[] arrayOfEmp = { new EmployeePayrollData(1, "Deven Mali", 100000.0),
				new EmployeePayrollData(2, "Pavan Patil", 200000.0),
				new EmployeePayrollData(3, "Kaplesh Mali", 300000.0) };
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmp));

		/**
		 * calling the method writeEmployeeData from the IO_File
		 */
		employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);

		/**
		 * calling the method countEntries from the IO_File
		 */
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		assertEquals(3, entries);
	}
}