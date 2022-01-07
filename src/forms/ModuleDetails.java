/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import admin.AdminPanel;
import database.ConnectDatabase;
import forms.LoginForm;
import admin.AdminAddOccurencePanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import lecturer.LecturerPanel;
import student.StudentCourses;
import student.StudentPanel;

/**
 *
 * @author Ikhmal Fakhri <u2000600@siswa.um.edu.my>
 */
public class ModuleDetails extends javax.swing.JFrame {

    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    LoginForm lf = new LoginForm();
    ViewCourses am = new ViewCourses();
    StudentCourses sc = new StudentCourses();
    int hours = -1;
    static int occ = -1, ecap = -1, edit = 0;
    static String type = "", eday = "", etime = "", etime1 = "", etime2 = "", elec = "", module2 = "", occurence2 = "", activity2 = "";

    public void setTable() {
        if (lf.getStudent_type() > 0) {
            editButton.setVisible(false);
            addButton.setVisible(false);
            deleteButton.setVisible(false);
            registerButton.setVisible(true);
        } else if (lf.getStudent_type() == 0) {
            editButton.setVisible(true);
            addButton.setVisible(false);
            deleteButton.setVisible(false);
            registerButton.setVisible(false);
        } else {
            registerButton.setVisible(false);
        }
    }

    public void updateTable() {
        String update = "SELECT * FROM APP.TIMETABLE_MODULES ORDER BY OCCURENCE";
        try {
            con.prepareStatement(update);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ModuleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayModules() {
        String dm = "";
        if (lf.getStudent_type() <= 0) {
            dm = "SELECT * FROM TIMETABLE_MODULES where modules='" + am.getModuleCode() + "' ORDER BY OCCURENCE";
        } else {
            dm = "SELECT * FROM TIMETABLE_MODULES where modules='" + sc.getModuleCode() + "' ORDER BY OCCURENCE";
        }
        try {
            ps = con.prepareStatement(dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ACTUAL = rs.getString("ACTUAL");
                String MODULES = rs.getString("MODULES");
                String OCCURENCE = rs.getString("OCCURENCE");
                String DAY = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String LECTURER = rs.getString("LECTURER");
                String TARGET = rs.getString("STUDENTCAP");

                String tbData[] = {MODULES, OCCURENCE, DAY, TIMESTART, TIMEEND, ACTIVITY, LECTURER, TARGET, ACTUAL};
                DefaultTableModel tblModel = (DefaultTableModel) modulesTable.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Creates new form module_Details
     */
    public ModuleDetails() {

        initComponents();
        displayModules();
        setCode();
        setCredit();
        setTable();

    }

    public boolean checkTime() {
        String q1 = "SELECT * FROM APP.REGISTEREDMODULES WHERE USERNAME='" + lf.getMatrixNumber() + "'";

        int index = modulesTable.getSelectedRow();
        TableModel model = modulesTable.getModel();

        String day = model.getValueAt(index, 2).toString();
        String time1 = model.getValueAt(index, 3).toString();
        String time2 = model.getValueAt(index, 4).toString();

        LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareEnd = LocalTime.parse(time2.substring(0, 5));

        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {

                String DAY2 = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                activity2 = rs.getString("ACTIVITYTYPE");
                module2 = rs.getString("MODULE");
                occurence2 = rs.getString("OCC");

                LocalTime targetStart = LocalTime.parse(TIMESTART.substring(0, 5));
                LocalTime targetEnd = LocalTime.parse(TIMEEND.substring(0, 5));

                if (day == null ? DAY2 == null : day.equals(DAY2)) {

                    boolean NoClashClassAfterEnd = (targetStart.isAfter(compareEnd) || targetStart.equals(compareEnd));
                    boolean NoClashClassBeforeStart = (targetEnd.isBefore(compareStart) || targetEnd.equals(compareStart));

                    if (NoClashClassAfterEnd || NoClashClassBeforeStart) {

                        continue;
                    } else {

                        return true;
                    }
                }
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public void setCode() {
        if (lf.getStudent_type() <= 0) {
            courseCode.setText("COURSE: " + am.getModuleCode());
        } else {
            courseCode.setText("COURSE: " + sc.getModuleCode());
        }
    }

    public void setCredit() {
        if (lf.getStudent_type() <= 0) {
            courseCredit.setText("CREDIT: " + am.getCredit());
        } else {
            courseCredit.setText("CREDIT: " + sc.getCredit());
        }
    }

    public void setEdit(int occ, int scap, String type, String day, String time, int edit, String time1, String time2) {
        this.occ = occ;
        this.type = type;
        this.eday = day;
        this.etime = time;
        //this.elec = lec;
        this.ecap = scap;
        this.edit = edit;
        this.etime1 = time1;
        this.etime2 = time2;
    }

    public int getEOcc() {
        return occ;
    }

    public String getEType() {
        return type;
    }

    public String getEDay() {
        return eday;
    }

    public String getETime() {
        return etime;
    }

    public String getETime1() {
        return etime1;
    }

    public String getETime2() {
        return etime2;
    }

    public String getELec() {
        return elec;
    }

    public int getECap() {
        return ecap;
    }

    public int getEdit() {
        return edit;
    }

    public void deleteB() {
        DefaultTableModel dtm = (DefaultTableModel) modulesTable.getModel();
        try {
            int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String type = model.getValueAt(index, 5).toString();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());

            PreparedStatement st = con.prepareStatement("DELETE FROM app.TIMETABLE_MODULES WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='" + type + "'");
            st.executeUpdate();
            dtm.removeRow(index);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
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
        modulesTable = new javax.swing.JTable();
        courseCredit = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        courseCode = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocation(new java.awt.Point(0, 0));

        modulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULES", "OCC", "DAY", "TIME START", "TIME END", "ACTIVITY", "MODULE TUTOR", "TARGET", "ACTUAL"
            }
        ));
        modulesTable.setColumnSelectionAllowed(true);
        modulesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modulesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(modulesTable);
        modulesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (modulesTable.getColumnModel().getColumnCount() > 0) {
            modulesTable.getColumnModel().getColumn(1).setMaxWidth(75);
            modulesTable.getColumnModel().getColumn(3).setMinWidth(90);
            modulesTable.getColumnModel().getColumn(3).setMaxWidth(90);
            modulesTable.getColumnModel().getColumn(4).setMinWidth(90);
            modulesTable.getColumnModel().getColumn(4).setMaxWidth(90);
            modulesTable.getColumnModel().getColumn(5).setResizable(false);
            modulesTable.getColumnModel().getColumn(6).setMinWidth(150);
            modulesTable.getColumnModel().getColumn(7).setMinWidth(75);
            modulesTable.getColumnModel().getColumn(7).setMaxWidth(75);
        }

        courseCredit.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        courseCredit.setText("CREDIT    :");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        courseCode.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        courseCode.setText("COURSE  : ");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(courseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(courseCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                        .addComponent(editButton))
                    .addComponent(courseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseCredit)
                    .addComponent(registerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modulesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modulesTableMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_modulesTableMouseClicked

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        if (lf.getStudent_type() > 0) {
            new StudentPanel().setVisible(true);
            dispose();
        } else if (lf.getStudent_type() == 0) {
            new LecturerPanel().setVisible(true);
            dispose();
        } else if (lf.getStudent_type() == -1) {
            new AdminPanel().setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_backButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        edit = 0;
        new AdminAddOccurencePanel().setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) modulesTable.getModel();
        try {
            int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String type = model.getValueAt(index, 5).toString();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());

            PreparedStatement st = con.prepareStatement("DELETE FROM app.VALID_MODULES WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='" + type + "'");
            st.executeUpdate();
            dtm.removeRow(index);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        try {
            AdminAddOccurencePanel as = new AdminAddOccurencePanel();
            int index = modulesTable.getSelectedRow();
            TableModel model = modulesTable.getModel();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
            int scap = Integer.parseInt(model.getValueAt(index, 7).toString());
            int edit = 1;
            String typ = model.getValueAt(index, 5).toString();
            String day = model.getValueAt(index, 2).toString();
            String time1 = model.getValueAt(index, 3).toString();
            String time2 = model.getValueAt(index, 3).toString();
            String time = time1.substring(0, 5);
            //String lec = model.getValueAt(index, 6).toString();
            setEdit(occ, scap, typ, day, time, edit, time1, time2);
            as.editMod();
            as.setVisible(true);
            dispose();
        } catch (Exception e) {
            System.out.println("273 md");
            JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
        int index = modulesTable.getSelectedRow();
        TableModel model = modulesTable.getModel();

        int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
        String type = model.getValueAt(index, 5).toString();
        String modulecode = model.getValueAt(index, 0).toString();
        String day = model.getValueAt(index, 2).toString();
        String time1 = model.getValueAt(index, 3).toString();
        String time2 = model.getValueAt(index, 4).toString();
        String username = lf.getMatrixNumber();
        String actual1 = "";
        try {
            Statement st;
            st = con.createStatement();
            String checkCap = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and OCC=" + occ + " and ACTIVITYTYPE='" + type + "'";
            ResultSet rs3 = st.executeQuery(checkCap);
            rs3.next();
            actual1 = rs3.getString(1);
            System.out.println(actual1);

        } catch (SQLException e) {
            System.out.println("failed md484");
        }

        try {
            Statement st;
            Statement st1;
            st = con.createStatement();
            String strQuery3 = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and USERNAME='" + username + "'";
            ResultSet rs3 = st.executeQuery(strQuery3);
            rs3.next();
            String Countrow3 = rs3.getString(1);
            System.out.println(Countrow3);

            boolean Clash = checkTime();

            if (Clash) {
                JOptionPane.showMessageDialog(null, "CLASHES WITH " + activity2 + " FOR " + module2 + " OCCURENCE " + occurence2);
            } else if (Countrow3.equals("0")) {
                String reg = "INSERT INTO APP.REGISTEREDMODULES(USERNAME,MODULE,OCC,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND) SELECT '" + username + "',MODULES,OCCURENCE,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND FROM APP.TIMETABLE_MODULES WHERE OCCURENCE=" + occ + " AND  MODULES='" + modulecode + "'";
                st = con.createStatement();
                st.execute(reg);

                int actual = Integer.parseInt(actual1) + 1;

                String setActual = "UPDATE APP.TIMETABLE_MODULES SET ACTUAL=" + actual + " WHERE MODULES='" + modulecode + "' AND OCCURENCE=" + occ + "";
                st1 = con.createStatement();
                st1.execute(setActual);

                JOptionPane.showMessageDialog(null, "MODULE REGISTERED");
            } else {
                JOptionPane.showMessageDialog(null, "MODULE ALREADY REGISTERED");

            }
        } catch (SQLException e) {
            System.out.println("failed md401");
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_registerButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ModuleDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuleDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuleDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuleDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuleDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel courseCode;
    private javax.swing.JLabel courseCredit;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modulesTable;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}
