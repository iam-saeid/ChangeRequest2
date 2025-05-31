/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homeworkattempthuhuhu;

import java.io.*;
import java.util.*;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManager { // public class
    private static final String FILE_NAME = "SampleCSV.csv"; //
    private List<Employee> employees;

    public EmployeeManager() {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
        loadEmployeesFromFile();
    }

    private void loadEmployeesFromFile() {
        employees.clear();
        File file = new File(FILE_NAME);
          
        if (!file.exists()) {
            System.out.println("Employee file not found.");
            return;  
    }               

        try {
            CSVReader reader = new CSVReader(new FileReader (file));
            String[] row;
            String[] headers = reader.readNext();            
            
            while ((row = reader.readNext())!=null) {
                            
                String employeeID = row[0];
                String lastName = row[1]; 
                String firstName = row[2];
                String dateOfBirth = row[3]; 
                String position = row[4];
                double hourlyRate = Double.parseDouble(row[5].replace(",",""));
                double salary = Double.parseDouble(row[6].replace(",",""));
                String sss = row[7];
                String philHealth = row[8];
                String tinNum = row[9];
                String pagIbig = row[10];
                
                int id = Integer.parseInt(employeeID); //Convert ID to int because employee constructor asks for int
                
                employees.add(new Employee (id, lastName, firstName, dateOfBirth, position, hourlyRate, salary, sss, philHealth, tinNum, pagIbig));
                
            }
        
        reader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Please double-check your employee file.");
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

    //This writes in the CSV file
    private void saveEmployeesToFile() {
        File file = new File(FILE_NAME);
        if (employees == null) {
            System.out.println("\nError: Employees list is null! Creating a new list.");
            employees = new ArrayList<>();
            return;
        }

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeNext(new String[] {
            
            "Employee ID", "First Name", "Last Name", "Birthday" , "Position", 
            "Hourly Rate" , "Salary" , "SSS" , "PhilHealth" , "TIN" , "PAGIBIG"
                                       
        });
            
            for (Employee emp : employees) {
                writer.writeNext(new String[]{
                   String.valueOf(emp.getEmployeeId()),
                    emp.getLastName(),
                    emp.getName(),
                    emp.getBirthday(),
                    emp.getPosition(),
                    String.valueOf(emp.getHourlyRate()),
                    String.valueOf(emp.getSalary()),
                    emp.getSSS(),
                    emp.getPhilHealth(),
                    emp.getTinNum(),
                    emp.getPagIbig() 
                });
            }
            writer.close();        
        } catch (IOException e) {
            System.out.println("\nError saving employees: " + e.getMessage());
        }
    }

    public void addEmployee(int employeeId, String firstName, String lastName, String birthday, String position, double hourlyRate, 
                            double salary,String sss, String philHealth, String tinNum, String pagIbig) {
        employees.add(new Employee(employeeId, firstName, lastName, birthday, position, hourlyRate, salary, sss, philHealth, tinNum, pagIbig));
        saveEmployeesToFile();
        System.out.println("\nEmployee added successfully.");
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
            return;
        }
        for (Employee emp : employees) {
            emp.displayInfo();
        }
    }

    public void editEmployee(int employeeId, String newfirstName, String newlastName, String newBirthday, String newPosition, double newhourlyRate, double newSalary) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == employeeId) {
                if (!newfirstName.isEmpty()) emp.setFirstName(newfirstName);
                if (!newlastName.isEmpty()) emp.setLastName(newlastName);
                if (!newBirthday.isEmpty()) emp.setBirthday(newBirthday);
                if (!newPosition.isEmpty()) emp.setPosition(newPosition);
                if (newhourlyRate >= 0) emp.setHourlyRate(newhourlyRate);
                if (newSalary >= 0) emp.setSalary(newSalary);

                saveEmployeesToFile();
                System.out.println("\nEmployee updated successfully.");
                return;
            }
        }
        System.out.println("\nEmployee not found.");
    }

    public void deleteEmployee(int employeeId) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getEmployeeId() == employeeId) {
                iterator.remove();
                saveEmployeesToFile();
                System.out.println("\nEmployee deleted successfully.");
                return;
            }
        }
        System.out.println("\nEmployee not found.");
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return employee;
            }
        }
        return null; // Employee not found
    }
}   