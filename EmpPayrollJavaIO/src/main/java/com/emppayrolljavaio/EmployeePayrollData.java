package com.emppayrolljavaio;

public class EmployeePayrollData {

	public int id;
	public String name;
	public double salary;
	
	/**
	 *Parameterized constructor 
	 * @param id - employee id
	 * @param name - employee name
	 * @param salary - employee salary
	 */
	public EmployeePayrollData(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	/**
	 * getter and setter method
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * toString Method
	 */
	@Override
	public String toString() {
		return "EmployeePayrollData [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
	
}
