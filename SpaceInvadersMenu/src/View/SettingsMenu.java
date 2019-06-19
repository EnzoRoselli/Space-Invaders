package View;
import Controller.VisibleFramesHandler;
import static MVC.Interface.ImessageForUser.ICON_ROUTE;
import javax.swing.ImageIcon;

public class SettingsMenu extends javax.swing.JFrame {
    private Menu myMenu;
    private VisibleFramesHandler visible;
    
    public SettingsMenu() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
        visible = new VisibleFramesHandler();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        changePassBtn = new javax.swing.JButton();
        skinsBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(290, 360));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setLayout(null);

        changePassBtn.setText("Change password");
        changePassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassBtnActionPerformed(evt);
            }
        });
        jPanel1.add(changePassBtn);
        changePassBtn.setBounds(80, 80, 150, 21);

        skinsBtn.setText("Skins");
        jPanel1.add(skinsBtn);
        skinsBtn.setBounds(80, 150, 150, 21);

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn);
        exitBtn.setBounds(80, 210, 150, 21);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/pikachu2.gif"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 290, 350);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 290, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        myMenu = new Menu();
        visible.invisibleAndVisibleFrame(this, myMenu);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void changePassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassBtnActionPerformed
        
    }//GEN-LAST:event_changePassBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePassBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton skinsBtn;
    // End of variables declaration//GEN-END:variables
}
