/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ThreadEnvio implements Runnable {
    private DatagramSocket ds;
    private String IP;
    private byte[] msg;
    private long time;
    private boolean continuar = true;
    
    public ThreadEnvio(String IP, long time) {
        try {
            this.IP = IP;
            this.time =time;
            ds = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ThreadEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMensaje(String msg) {
        this.msg = msg.getBytes();
    }

    @Override
    public void run() {
        DatagramPacket dp;

        try {
            while (continuar) {
                dp = new DatagramPacket(msg, msg.length, InetAddress.getByName(IP), 3030);
                ds.send(dp);
                Thread.sleep(time);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ThreadEnvio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadEnvio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cerrarConexion() {
        try {
            continuar = false;
            Thread.sleep(500);
            ds.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
