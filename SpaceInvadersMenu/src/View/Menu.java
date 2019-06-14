package View;

import MVC.Interface.ImessageForUser;
import Controller.*;
import Model.SQL;
import Game.display.Display;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu extends javax.swing.JFrame implements ImessageForUser {

    private LogIn myLogIn;
    private SettingsMenu mySettings;
    private SQL statement;
    private JSON jsonHandler;
    private VisibleFramesHandler visible;
    //private Display game;
    
    public Menu() {
        initComponents();
        visible = new VisibleFramesHandler();
        setIconImage(new ImageIcon(getClass().getResource(ICON_ROUTE)).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        playBtn = new javax.swing.JButton();
        bestPlayersBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(null);

        playBtn.setText("Play");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });
        jPanel2.add(playBtn);
        playBtn.setBounds(169, 110, 120, 21);

        bestPlayersBtn.setText("Best Players");
        bestPlayersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bestPlayersBtnActionPerformed(evt);
            }
        });
        jPanel2.add(bestPlayersBtn);
        bestPlayersBtn.setBounds(169, 160, 120, 21);

        settingsBtn.setText("Settings");
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });
        jPanel2.add(settingsBtn);
        settingsBtn.setBounds(169, 210, 120, 21);

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel2.add(exitBtn);
        exitBtn.setBounds(169, 250, 120, 21);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/invaders.gif"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 460, 444);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        visible.invisibleFrame(this);
        Display.executeGame();
    }//GEN-LAST:event_playBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        myLogIn = new LogIn();
        visible.invisibleAndVisibleFrame(this, myLogIn);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        mySettings = new SettingsMenu();
        visible.invisibleAndVisibleFrame(this, mySettings);
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void bestPlayersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bestPlayersBtnActionPerformed
        statement = new SQL();
        jsonHandler = new JSON();
        ArrayList<String> players = statement.dataBaseToArray();
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        try {
            for (String e : players) {
                array.put(jsonHandler.getFormatoJson(e));
            }
            object.put("best players", players);
            jsonHandler.createJSON(object);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bestPlayersBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bestPlayersBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton settingsBtn;
    // End of variables declaration//GEN-END:variables
}
