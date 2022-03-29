package com.emppayrolljavaio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1. Created an EmployeePayrollService to Read and Write Employee Payroll to a Console
 * @author Asus
 *
 */
public class EmployeePayrollService {

	static ArrayList<EmployeePayrollData> employeePayrollDatas = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("Welcome to Employee payroll service!");
		
		Scanner sc = new Scanner(System.in);
		EmployeePayrollService.readEmployeePayrollData(sc);
		EmployeePayrollService.writeEmployeePayrollData();
	}
	
	/**
	 * Method to take user input
	 * @param consoleInputReader - to store console input value
	 */
	private static void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter employee Id : ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter employee Name : ");
		String name = consoleInputReader.next();
		System.out.println("Enter employee Salary : ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollDatas.add(new EmployeePayrollData(id, name, salary));
	}
	
	/**
	 * Method to print employee payroll list
	 */
	private static void writeEmployeePayrollData() {
		System.out.println("Writing Employee payroll data to console " + employeePayrollDatas);
	}
}
