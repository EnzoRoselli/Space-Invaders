package View;

import MVC.Interface.ImessageForUser;
import Controller.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConfirmationCode extends javax.swing.JFrame implements ImessageForUser {

    private String lastRandom;
    private Menu myMenu;
    private SignInHandler control;
    private User user;
    private VisibleFramesHandler visible;

    public ConfirmationCode(User user, String random) {
        initComponents();
        this.lastRandom = random;
        control = new SignInHandler();
        this.user = user;
        visible = new VisibleFramesHandler();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        insertCodeTxt = new javax.swing.JTextField();
        insertBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html>Already almost complete its active incorporation to the resistance against the space invaders spaceman, for join definitely to our forces enter the code that we have sent to your email<html>");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 24, 302, 117));
        jPanel1.add(insertCodeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 159, 136, -1));

        insertBtn.setText("Insert code");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });
        jPanel1.add(insertBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 227, 116, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/stars.gif"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 426, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed

        if (!lastRandom.equals(this.insertCodeTxt.getText())) {
            JOptionPane.showMessageDialog(null, "wrong code, check your inbox in gmail");
        } else {
            myMenu = new Menu();
            visible.invisibleAndVisibleFrame(this, myMenu);
            JOptionPane.showMessageDialog(this, control.showWelcomeMessage(user));
        }
    }//GEN-LAST:event_insertBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertBtn;
    private javax.swing.JTextField insertCodeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
