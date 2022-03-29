package com.emppayrolljavaio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1. Created an EmployeePayrollService to Read and Write Employee Payroll to a Console 
 * 2. demonstrate File Operations like 1.Check File Exists, 2.Delete File and Check File Not Exist,
 *    3.Create Directory, 4.Create Empty File, 5.List Files, Directories as well as Files with Extension
 * 3. Watch Service to watch particular directory along with all Files and Sub Directories
 * 4. Employee Payroll Service to print the Employee Payrolls
 * 
 * @author Asus
 *
 */
public class EmployeePayrollService {

	/**
	 * Created Java enum for fixed value .
	 */
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	/**
	 * Created list for EmployeePayrollData
	 */
	private List<EmployeePayrollData> employeePayrollDatas;

	/**
	 * Default constructor
	 */
	public EmployeePayrollService() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param employeePayrollDatas - list of employee Payroll data
	 */
	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDatas) {
		this.employeePayrollDatas = employeePayrollDatas;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Employee payroll service!");
		/**
		 * List to store all employee payroll data
		 */
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();

		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);

		Scanner sc = new Scanner(System.in);
		/**
		 * called readEmployeePayrollData to read from console
		 */
		employeePayrollService.readEmployeePayrollData(sc);
		/**
		 * called writeEmployeePayrollData to write
		 */
		employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
	}

	/**
	 * Method to take user input
	 * 
	 * @param consoleInputReader - to store console input value
	 */
	private void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter employee Id : ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter employee Name : ");
		String name = consoleInputReader.next();
		System.out.println("Enter employee Salary : ");
		double salary = Double.parseDouble(consoleInputReader.nextLine());
		employeePayrollDatas.add(new EmployeePayrollData(id, name, salary));
	}

	/**
	 * Method to print employee payroll list
	 */
	public void writeEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
			System.out.println("Writing Employee payroll data to console " + employeePayrollDatas);
		else if(ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData(employeePayrollDatas);
	}
	
	/**
	 * Method to countEntries - Using File IO Count Number of Entries in the File
	 * 
	 * @param ioService
	 * @return number of entries
	 */
	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().countEntries();
		return 0;
	}
}
