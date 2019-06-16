package View;

import MVC.Interface.ImessageForUser;
import Controller.*;
import javax.swing.*;

public class LogIn extends javax.swing.JFrame implements ImessageForUser {

    private SignIn mySignIn;
    private Menu myMenu;
    private LogInHandler control;
    private Session sesion;
    private VisibleFramesHandler visible;
    
    public LogIn() {
        initComponents();
        visible = new VisibleFramesHandler();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        userText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logInBtn = new javax.swing.JButton();
        SignInBtn = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        exitBtn = new javax.swing.JButton();
        forgotPasswordBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });
        jPanel2.add(userText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 220, 20));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 60, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 60, -1));

        logInBtn.setText("Log in");
        logInBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInBtnMouseClicked(evt);
            }
        });
        logInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInBtnActionPerformed(evt);
            }
        });
        jPanel2.add(logInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 100, -1));

        SignInBtn.setText("Sign up");
        SignInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInBtnActionPerformed(evt);
            }
        });
        jPanel2.add(SignInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 210, 100, -1));
        jPanel2.add(passwordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 220, -1));

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel2.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 80, -1));

        forgotPasswordBtn.setBackground(new java.awt.Color(51, 153, 255));
        forgotPasswordBtn.setForeground(new java.awt.Color(255, 255, 255));
        forgotPasswordBtn.setText("Forgot Password?");
        jPanel2.add(forgotPasswordBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 251, 220, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/invaderGif.gif"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -10, 370, 340));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/stars.gif"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed

    }//GEN-LAST:event_userTextActionPerformed

    private void SignInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInBtnActionPerformed
        mySignIn = new SignIn();
        visible.invisibleAndVisibleFrame(this, mySignIn);
    }//GEN-LAST:event_SignInBtnActionPerformed

    private void logInBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInBtnMouseClicked

    }//GEN-LAST:event_logInBtnMouseClicked

    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
        String error = NO;
        control = new LogInHandler();
        String pass = new String(this.passwordText.getPassword());
        sesion = new Session(this.userText.getText(), pass);
        ScoreAndUserCatcher.setGmail(this.userText.getText());
        error = control.logInManager(error, sesion);

        if (error.equals(NO)) {
        myMenu = new Menu();
        visible.invisibleAndVisibleFrame(this, myMenu);
        } else {
            JOptionPane.showMessageDialog(this, error);
        }
    }//GEN-LAST:event_logInBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
       System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignInBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton forgotPasswordBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logInBtn;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
