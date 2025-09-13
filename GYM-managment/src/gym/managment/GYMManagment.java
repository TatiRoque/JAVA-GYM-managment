/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym.managment;
import gym.managment.view.ClienteFrame;


/**
 *
 * @author Tatiana
 */
public class GYMManagment {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // Look & Feel (lo dejó NetBeans)…
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
    java.util.logging.Logger.getLogger(GYMManagment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
}


        // Abrir la ventana principal (por ahora, ClienteFrame)
        java.awt.EventQueue.invokeLater(() -> {
            new ClienteFrame().setVisible(true);
        });
    }
    
}
