/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homeworkattempthuhuhu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeTable extends JFrame {
    private Motorphpayrollsystem motorPH;
    private EmployeeManager manageEmployee;

    public EmployeeTable(Motorphpayrollsystem motorPH, EmployeeManager manageEmployee) {
        this.motorPH = motorPH;
        this.manageEmployee = manageEmployee;

        setTitle("Employee List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Table headers
        String[] columnNames = {"ID", "Last Name", "First name", "SSS" , "PhilHealth" , "TIN" , "PAGIBIG"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
             @Override
             public boolean isCellEditable(int row, int column) {
                   return false; // Make all cells non-editable
                }
            };

        // Populate rows from employees
        List<Employee> employees = manageEmployee.getEmployees();
        
        if (employees != null) {
        for (Employee emp : employees) {
            Object[] row = {
                String.valueOf(emp.getEmployeeId()),
                emp.getLastName(),
                emp.getName(),
                emp.getSSS(),
                emp.getPhilHealth(),
                emp.getTinNum(),
                emp.getPagIbig()
            };
            model.addRow(row);
        }
      }        
        

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add View Employee Button
        JButton viewButton = new JButton("View Employee");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String empIDStr = table.getValueAt(selectedRow, 0).toString();
                    try {
                        int empID = Integer.parseInt(empIDStr);
                        Employee selected = manageEmployee.getEmployeeById(empID);
                        if (selected != null) {
                            new ViewEmployeeDetails(selected); 
                        } else {
                            JOptionPane.showMessageDialog(null, "Employee not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Employee ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an employee on the table first.");
                }
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.SOUTH);                 
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
    }
}

