package View;

import MVC.Interface.ImessageForUser;
import Controller.*;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SignIn extends javax.swing.JFrame implements ImessageForUser {

    private ConfirmationCode confirmationCode;
    private SignInHandler control;
    private User user;
    private Maths number;
    private VisibleFramesHandler visible;
    private LogIn myLogIn;

    public SignIn() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
        number = new Maths();
        visible = new VisibleFramesHandler();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lastNameTxt = new javax.swing.JTextField();
        nickNameTxt = new javax.swing.JTextField();
        SignInBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        gmailTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        rePasswordTxt = new javax.swing.JPasswordField();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 60, -1));
        jPanel1.add(nameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 170, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Last name:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 98, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nickname:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 98, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 98, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Repeat password:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 130, -1));
        jPanel1.add(lastNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 170, -1));
        jPanel1.add(nickNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 170, -1));

        SignInBtn.setBackground(new java.awt.Color(51, 0, 204));
        SignInBtn.setForeground(new java.awt.Color(255, 255, 255));
        SignInBtn.setText("Sign up");
        SignInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInBtnActionPerformed(evt);
            }
        });
        jPanel1.add(SignInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 390, 150, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gmail:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 98, -1));

        gmailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gmailTxtActionPerformed(evt);
            }
        });
        jPanel1.add(gmailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 170, -1));

        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });
        jPanel1.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 170, -1));
        jPanel1.add(rePasswordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 170, -1));

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInBtnActionPerformed
        String error = NO;
        control = new SignInHandler();
        String pass = new String(this.passwordTxt.getPassword());
        String rePass = new String(this.rePasswordTxt.getPassword());

        user = new User(this.nameTxt.getText(), this.lastNameTxt.getText(), this.nickNameTxt.getText(),
                pass, rePass, this.gmailTxt.getText());
        error = control.signInManager(error, user, number.randomNumber());
        if (error.equals(NO)) {
            confirmationCode = new ConfirmationCode(user, number.getLastRandom());
            visible.invisibleAndVisibleFrame(this, confirmationCode);
        } else {
            JOptionPane.showMessageDialog(this, error);
        }
    }//GEN-LAST:event_SignInBtnActionPerformed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtActionPerformed

    private void gmailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmailTxtActionPerformed

    }//GEN-LAST:event_gmailTxtActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        myLogIn = new LogIn();
        visible.invisibleAndVisibleFrame(this, myLogIn);
    }//GEN-LAST:event_backBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignInBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField gmailTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField nickNameTxt;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JPasswordField rePasswordTxt;
    // End of variables declaration//GEN-END:variables
}
