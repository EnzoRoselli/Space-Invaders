package View;

import Controller.BestPlayer;
import Controller.OnlyBestPlayerUser;
import Controller.VisibleFramesHandler;
import static MVC.Interface.ImessageForUser.ICON_ROUTE;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class BestPlayers extends javax.swing.JFrame {

    private BestPlayer bestPlayerHandler;
    private VisibleFramesHandler visible;
    private Menu myMenu;

    public BestPlayers() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
        visible = new VisibleFramesHandler();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBestPlayers = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableBestPlayers.setAutoCreateRowSorter(true);
        jTableBestPlayers.setBackground(new java.awt.Color(102, 102, 102));
        jTableBestPlayers.setForeground(new java.awt.Color(255, 255, 255));
        jTableBestPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", "  "}
            },
            new String [] {
                "Nickname", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBestPlayers.setSelectionBackground(new java.awt.Color(0, 51, 255));
        jScrollPane1.setViewportView(jTableBestPlayers);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 340, 180));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 140, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/banana.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 370));

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BEST PLAYERS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 180, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/starwars.gif"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 370, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        visible.invisibleAndVisibleFrame(this, new Menu());
    }//GEN-LAST:event_btnBackActionPerformed
    public void listBestPlayers(ArrayList<OnlyBestPlayerUser> users) {
        String matrix[][] = new String[users.size()][2];

        for (int i = 0; i < users.size(); i++) {
            matrix[i][0] = users.get(i).getNickName();
            matrix[i][1] = users.get(i).getScore().toString();
        }

        jTableBestPlayers.setModel(new javax.swing.table.DefaultTableModel(
                matrix,
                new String[]{
                    "Nickname", "Score"
                }
        ));

        visible.visibleFrame(this);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBestPlayers;
    // End of variables declaration//GEN-END:variables
}
