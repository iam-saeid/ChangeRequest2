/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homeworkattempthuhuhu;

import javax.swing.*;
import java.awt.*;

public class ViewEmployeeDetails extends JFrame {
    Motorphpayrollsystem payrollSystem = new Motorphpayrollsystem();

    public ViewEmployeeDetails(Employee emp) {
        
        setTitle("Employee Details");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        add(new JLabel("ID:"));
        add(new JLabel(String.valueOf(emp.getEmployeeId())));

        add(new JLabel("Name:"));
        add(new JLabel(emp.getName()));

        add(new JLabel("Position:"));
        add(new JLabel(emp.getPosition()));

        add(new JLabel("Rate:"));
        add(new JLabel(String.valueOf(emp.getHourlyRate())));

        add(new JLabel("Gross Salary:"));
        add(new JLabel(String.format("%.2f", payrollSystem.calculateGrossSalary(emp.getEmployeeId()))));

        add(new JLabel("SSS Deduction:"));
        add(new JLabel(String.format("%.2f", payrollSystem.calculateSssDeduction(emp.getEmployeeId()))));

        add(new JLabel("Pag-IBIG Deduction:"));
        add(new JLabel(String.format("%.2f", payrollSystem.calculatePagibigDeduction(emp.getEmployeeId()))));

        add(new JLabel("PhilHealth Deduction:"));
        add(new JLabel(String.format("%.2f", payrollSystem.calculatePhilhealthDeduction(emp.getEmployeeId()))));

        add(new JLabel("Tax:"));
        add(new JLabel(String.format("%.2f", payrollSystem.calculateWithholdingTax(emp.getEmployeeId()))));

        add(new JLabel("Net Salary:"));
        add(new JLabel(String.format("%.2f", payrollSystem.netSalary(emp.getEmployeeId()))));
    
         //Added some padding around content
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setVisible(true);
    }
}

