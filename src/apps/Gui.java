/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Gui extends javax.swing.JFrame {
    public static final int PORT = 3030;
    public static final String IP_FIJA = "192.168.1.131";
    private String IP;
    private DatagramSocket ds;    
    private String oldMsg = "";
    private ThreadEnvio he;

    public Gui() {
        try {
            initComponents(); 
            IP = JOptionPane.showInputDialog(this,"Indique IP del servidor", "NodeMcu v3", JOptionPane.INFORMATION_MESSAGE);
            if (IP.length()==0) IP = IP_FIJA; // Default IP router fixed
            ds = new DatagramSocket(PORT);
            he = new ThreadEnvio(IP, 250);
            he.setMensaje("ST");
            new Thread(he).start();            
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelControles = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonControlesUp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonControlesLeft = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jButtonControlesRight = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonControlesDown = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanelTop = new javax.swing.JPanel();
        jToggleButtonLuz = new javax.swing.JToggleButton();
        jButtonClaxon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Android Conectado!!!");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelControles.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));
        jPanelControles.setLayout(new java.awt.GridLayout(3, 3));
        jPanelControles.add(jLabel1);

        jButtonControlesUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/up_arrow.png"))); // NOI18N
        jButtonControlesUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesUp);
        jPanelControles.add(jLabel2);

        jButtonControlesLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/left_arrow.png"))); // NOI18N
        jButtonControlesLeft.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesLeft);

        jButtonStop.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButtonStop.setText("STOP");
        jButtonStop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonStop);

        jButtonControlesRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/right_arrow.png"))); // NOI18N
        jButtonControlesRight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesRight);
        jPanelControles.add(jLabel3);

        jButtonControlesDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/down_arrow.png"))); // NOI18N
        jButtonControlesDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesDown);
        jPanelControles.add(jLabel4);

        getContentPane().add(jPanelControles, java.awt.BorderLayout.CENTER);

        jPanelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanelTop.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jToggleButtonLuz.setText("Luces");
        jToggleButtonLuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLuzActionPerformed(evt);
            }
        });
        jPanelTop.add(jToggleButtonLuz);

        jButtonClaxon.setText("Claxon");
        jButtonClaxon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClaxonActionPerformed(evt);
            }
        });
        jPanelTop.add(jButtonClaxon);

        getContentPane().add(jPanelTop, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void botonPulsado(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonPulsado
        String s = null;

        switch (evt.getKeyChar()) {
            case '8':
                s = "AD"; //ADELANTE";
                break;
            case '4':
                s = "IZ"; //IZQUIERDA";
                break;
            case '6':
                s = "DE"; //DERECHA";
                break;
            case '5':
                s = "ST"; //STOP";
                break;
            case '2':
                s="AT"; // ATRAS
                break;
        }

        if (!oldMsg.equals(s)) { // Solo enviamos los cambios
            he.setMensaje(s);
            System.out.println(s);
        }
        oldMsg = s;


    }//GEN-LAST:event_botonPulsado

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        he.cerrarConexion();        
    }//GEN-LAST:event_formWindowClosing

    private void jButtonClaxonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClaxonActionPerformed
        try {
            he.setMensaje("CL1");
            Thread.sleep(100);
            he.setMensaje("CL0");
            oldMsg = "CL";
        } catch (InterruptedException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonClaxonActionPerformed

    private void jToggleButtonLuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLuzActionPerformed
        if (jToggleButtonLuz.isSelected()) {
            he.setMensaje("LU1");
            oldMsg = "LU1";
        } else {
            he.setMensaje("LU0");
            oldMsg = "LU1";
        }
    }//GEN-LAST:event_jToggleButtonLuzActionPerformed

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClaxon;
    private javax.swing.JButton jButtonControlesDown;
    private javax.swing.JButton jButtonControlesLeft;
    private javax.swing.JButton jButtonControlesRight;
    private javax.swing.JButton jButtonControlesUp;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JToggleButton jToggleButtonLuz;
    // End of variables declaration//GEN-END:variables
}
