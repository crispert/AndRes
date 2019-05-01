/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samelongjigs.andres;

import com.samelongjigs.andres.ui.AndRes;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cris
 */
public class App {
    
    public static Properties STR;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        loadStrings();
        
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
            java.util.logging.Logger.getLogger(AndRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AndRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AndRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AndRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AndRes appFrame = new AndRes();
                appFrame.setTitle(STR.getProperty("app.title"));
                
                Dimension iniSize = Toolkit.getDefaultToolkit().getScreenSize();
                int fw = iniSize.width / 2;
                int fh = iniSize.height / 2;
                appFrame.setBounds(fw / 2, fh / 2, fw, fh);
                appFrame.setVisible(true);
            }
        });
    }
    
    static void loadStrings() {
        STR = new Properties();
        try {
            STR.load(App.class.getResourceAsStream("strings.properties"));
            STR.keySet().stream().forEach(k -> System.out.println("$" + k));
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
