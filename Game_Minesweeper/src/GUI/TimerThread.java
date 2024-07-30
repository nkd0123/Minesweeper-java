/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author tranv
 */
public class TimerThread extends Thread{
    private int elapsedTime;
    private boolean running;
    private JLabel lbTimer;

    public TimerThread(JLabel lbTimer) {
        this.lbTimer = lbTimer;
        this.elapsedTime = 0;
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedTime++;
            SwingUtilities.invokeLater(() -> lbTimer.setText("Thời gian: " + elapsedTime + "s"));
        }
    }

    public void startTimer() {
        running = true;
        if (!this.isAlive()) {
            this.start();
        }
    }

    public void stopTimer() {
        running = false;
    }

    public void resetTimer() {
        running = false;
        elapsedTime = 0;
        SwingUtilities.invokeLater(() -> lbTimer.setText("Thời gian: 0s"));
    }
    public boolean isRunning() {
        return running;
    }

//    public int getElapsedTime() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    public int getElapsedTime() {
        return elapsedTime;
    }
}
