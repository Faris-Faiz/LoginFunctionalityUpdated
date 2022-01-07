/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import database.ConnectDatabase;
import forms.LoginForm;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import admin.AdminPanel;
import lecturer.LecturerPanel;
import student.StudentCourses;
import student.StudentPanel;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
public class Timetable extends javax.swing.JFrame {
    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    LoginForm lf = new LoginForm();
    ViewCourses am =new ViewCourses();
    StudentCourses sc= new StudentCourses();
    /**
     * Creates new form timetable
     */
    public Timetable() {
        initComponents();
        displayModules();
    }
    public void displayModules(){
        String dm = "SELECT * FROM REGISTEREDMODULES where USERNAME='"+lf.getMatrixNumber()+"'";   
        
        try {
            ps = con.prepareStatement(dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String OCC = rs.getString("OCC");
                String DAY = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String ELEMENT =MODULES+"\n("+ACTIVITY.substring(0, 3)+")";
                int day = -1;
                
                if(DAY.equals("MONDAY")){
                    day =  0;
                } else if(DAY.equals("TUESDAY")){
                    day =  1;
                } else if(DAY.equals("WEDNESDAY")){
                    day =  2;
                } else if(DAY.equals("THURSDAY")){
                    day =  3;
                } else if(DAY.equals("FRIDAY")){
                    day =  4;
                }
                
                int time1 = Integer.parseInt(TIMESTART.substring(0,2));
                int time2 = Integer.parseInt(TIMEEND.substring(0,2));
                int time = 0;
                int duration = time2 - time1;
                if(duration<0){
                    duration =  duration + 12;
                }
                if(time1 >=8){
                    time = Math.abs(time1-7);
                } else if(time1 <=7){
                    time =Math.abs(time1+5);
                } 
                
                if(duration==1){
                    jTable1.setValueAt((Object)ELEMENT, day, time);
                } else if(duration==2){
                    jTable1.setValueAt((Object)ELEMENT, day, time);
                    jTable1.setValueAt((Object)ELEMENT, day, time+1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jTable1.setRowHeight(75);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Monday", null, null, null, null, null, null, null, null, null, null, null, null},
                {"Tuesday", null, "", "", null, null, null, null, null, null, null, null, null},
                {"Wednesday", null, "", null, null, null, null, null, null, null, null, null, null},
                {"Thursday", null, null, null, null, null, null, null, null, null, null, null, null},
                {"Friday", null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "8:00 - 9:00", "9:00 - 10:00", "10:00 -11:00", "11:00 - 12:00", "12:00 - 1:00", "1:00 - 2:00", "2:00 - 3:00", "3:00 - 4:00", "4:00 - 5:00", "5:00 - 6:00", "6:00 - 7:00", "7:00 - 8:00"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        if(lf.getStudent_type() > 0){ // for students to go back to student panel
            new StudentPanel().setVisible(true);
            dispose();
        }else if(lf.getStudent_type() == 0){ // for lecturers to go back to lecturer panel
            new LecturerPanel().setVisible(true);
            dispose();
        }else if(lf.getStudent_type() == -1){ // for admins to go back to admin panel
            new AdminPanel().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Timetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Timetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Timetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Timetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Timetable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
