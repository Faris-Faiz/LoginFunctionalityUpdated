/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registers;

import forms.LoginForm;
import database.ConnectDatabase;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Faris Faiz
 */
public class StudentRegister extends javax.swing.JFrame {

    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    static String matrix;

    /**
     * Creates new form studentRegister
     */
    public StudentRegister() {
        initComponents();
        txtProg.addItem("COMPUTER SYSTEM AND NETWORK");
        txtProg.addItem("ARTIFICIAL INTELLIGENCE");
        txtProg.addItem("INFORMATION SYSTEMS");
        txtProg.addItem("SOFTWARE ENGINEERING");
        txtProg.addItem("DATA SCIENCE");
        txtMatrixNumber.setText(getMatrixNo());
        txtMatrixNumber.setEditable(false);
    }

    public String getMatrixNo() {
        try {
            //Getting incremental matrix number with student count
            String mxn = "SELECT COUNT(*) FROM app.logintable WHERE STDNT_TYPE>" + 0 + "";
            ps = con.prepareStatement(mxn);
            rs = ps.executeQuery();
            rs.next();
            int mx = Integer.parseInt(rs.getString(1));
            matrix = "U" + String.valueOf(2000000 + mx);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return matrix;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerStudentLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMatrixNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        txtLname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMuetb = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        combo_boxCSIT = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtProg = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        registerStudentLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        registerStudentLabel.setText("Register Student");

        jLabel2.setText("MATRIX NUMBER:");

        txtMatrixNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatrixNumberActionPerformed(evt);
            }
        });

        jLabel3.setText("FIRST NAME:");

        txtFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFnameActionPerformed(evt);
            }
        });

        jLabel4.setText("LAST NAME:");

        jLabel5.setText("SISWAMAIL:");

        jLabel6.setText("MUET BAND:");

        txtMuetb.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

        jLabel7.setText("PASSWORD:");

        jLabel8.setText("CONFIRM PASSWORD:");

        jLabel9.setText("DEPARTMENT");

        combo_boxCSIT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMPUTER SCIENCE", "INFORMATION TECHNOLOGY" }));
        combo_boxCSIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_boxCSITActionPerformed(evt);
            }
        });

        jLabel10.setText("PROGRAMME:");

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

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
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(registerStudentLabel)
                        .addComponent(jLabel2)
                        .addComponent(txtMatrixNumber)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtLname, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                        .addComponent(jLabel5)
                        .addComponent(txtEmail))
                    .addComponent(jLabel6)
                    .addComponent(txtMuetb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addComponent(txtPass)
                    .addComponent(jLabel8)
                    .addComponent(txtPass2)
                    .addComponent(jLabel9)
                    .addComponent(combo_boxCSIT, 0, 200, Short.MAX_VALUE)
                    .addComponent(txtProg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerStudentLabel)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatrixNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_boxCSIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMuetb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(registerButton)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatrixNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatrixNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatrixNumberActionPerformed

    private void txtFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFnameActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        //Initializing the inputs onto strings and ints
        //String username = txtUsername.getText();
        String fname = txtFname.getText().toUpperCase();
        String lname = txtLname.getText().toUpperCase();
        String email = txtEmail.getText();
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String pass = txtPass.getText();
        String cpass = txtPass2.getText();
        String matrix = getMatrixNo();
        int muet = (Integer) txtMuetb.getValue();
        int csit = combo_boxCSIT.getSelectedIndex() + 1;
        int studtype = -1;

        //Getting CSIT value with index value
        if (csit == 1) {
            studtype = txtProg.getSelectedIndex() + 1;
        } else if (csit == 2) {
            studtype = 6;
        }
        //Combining first name and last name for full name
        String fullname = fname + " " + lname;

        try {

            if (pass.equals(cpass)) {
                if (email.matches(EMAIL_PATTERN)) {
                    //Inserting inputs onto database(LOGINTABLE)
                    String reg = "INSERT INTO APP.LOGINTABLE(MATRIX_NUMBER,PASSWORD,STDNT_TYPE,MUET_BAND,FULLNAME,CSIT_LOGIN)VALUES('" + matrix + "','" + pass + "'," + studtype + "," + muet + ",'" + fullname + "'," + csit + ")";
                    Statement st;
                    st = con.createStatement();
                    st.execute(reg);

                    String lineSep = System.lineSeparator();
                    StringBuilder result = new StringBuilder();
                    result.append(fullname).append(" REGISTERED!").append(lineSep).append(lineSep);
                    result.append("MATRIX NUMBER : ").append(matrix).append(lineSep);

                    JOptionPane.showMessageDialog(null, result.toString());
                    //JOptionPane.showMessageDialog(null,"STUDENT REGISTERED");
                    new LoginForm().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Email is not valid");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password does not match");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "REGISTRATION FAILED");
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void combo_boxCSITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_boxCSITActionPerformed
        int csit = combo_boxCSIT.getSelectedIndex();
        if (csit == 0) {
            txtProg.removeAllItems();
            txtProg.addItem("COMPUTER SYSTEM AND NETWORK");
            txtProg.addItem("ARTIFICIAL INTELLIGENCE");
            txtProg.addItem("INFORMATION SYSTEMS");
            txtProg.addItem("SOFTWARE ENGINEERING");
            txtProg.addItem("DATA SCIENCE");
        } else if (csit == 1) {
            txtProg.removeAllItems();
            txtProg.addItem("MULTIMEDIA");
        }
    }//GEN-LAST:event_combo_boxCSITActionPerformed

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
            java.util.logging.Logger.getLogger(StudentRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> combo_boxCSIT;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel registerStudentLabel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtMatrixNumber;
    private javax.swing.JSpinner txtMuetb;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JComboBox<String> txtProg;
    // End of variables declaration//GEN-END:variables
}
