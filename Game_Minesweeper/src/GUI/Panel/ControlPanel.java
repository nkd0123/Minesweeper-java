/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import GUI.Gui;
import GUI.ICommon;
import GUI.ITrans;
import GUI.Start;
import GUI.TimerThread;
import Model.Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.swing.JOptionPane;
import Model.Player;
import Rank.RankDao;

/**
 *
 * @author tranv
 */
public class ControlPanel extends JPanel implements ICommon{
    //public static final boolean Win = true;
    //public static final boolean Close = false;
    private JLabel lbNumSquareClosed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private JButton btnBack;
    private Gui gui;
    
    private JLabel lbTimer;
    private TimerThread timer;
    private int elapsedTime; // thời gian đã trôi qua
    private String playerName;
    private ITrans listener;
    
    public ControlPanel(String name) 
    {
        initComp();
        addComp();
        addEvent();
        initTimer();
        playerName = name;
                
    }
    
    @Override
    public void initComp() {
      setLayout(null);
    }
    
    @Override
    public void addComp() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        
        lbNumSquareClosed = new JLabel();
        lbNumSquareClosed.setFont(font);
        lbNumSquareClosed.setText("Số ô chưa mở: " + Board.n_rows * Board.n_columns);
        lbNumSquareClosed.setBounds(10, 10, 250, 40);
        add(lbNumSquareClosed);
        
        lbNotify = new JLabel();
        lbNotify.setFont(font);
        lbNotify.setBounds(270, 10, 200, 40);
        add(lbNotify);
        
         lbTimer = new JLabel(); // Tạo nhãn hiển thị thời gian
        lbTimer.setFont(font);
        lbTimer.setBounds(10, 40, 200, 40);
        lbTimer.setText("Thời gian: " + elapsedTime +"s");
        add(lbTimer);
        
        btnRestart = new JButton();
        btnRestart.setFont(font);
        btnRestart.setText("Chơi lại");
        btnRestart.setBounds(490, 10, 200, 40);
        add(btnRestart);
        
        btnBack =new JButton();
        btnBack.setFont(font);
        btnBack.setText("Quay lại");
        btnBack.setBounds(490, 60, 200, 40);
        add(btnBack);
        
        
    }
    
    
    @Override
    public void addEvent() {
      btnRestart.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          listener.restart();
          lbNumSquareClosed.setText("Số ô chưa mở: " + Board.n_rows * Board.n_columns);
          lbNotify.setText("");
          resetTimer();
          startTimer();
        }
      });
      btnBack.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
           Gui gui = (Gui)SwingUtilities.getWindowAncestor(ControlPanel.this);
           gui.dispose();
           Start batdau=new Start();
           batdau.setVisible(true);
            
        }
    });
    }
   
    
    //-----------------------Timer--------------------------------
    private void initTimer() {
        //elapsedTime = 0;
        //timer = new Timer(1000, new ActionListener() { // Timer sẽ cập nhật mỗi giây
         //@Override
          //public void actionPerformed(ActionEvent e) {
           // elapsedTime++;
            //lbTimer.setText("Thời gian: " + elapsedTime + "s");
          //}
        //});
        timer = new TimerThread(lbTimer);
      }

    public void startTimer() {
      //if (!timer.isRunning()) 
      //{
        //elapsedTime = 0;
        //lbTimer.setText("Thời gian: 0s");
        //timer.start();
      //}
      if (timer != null && !timer.isRunning()) {
            timer.startTimer();
        }
    }

    public void stopTimer() {
      //timer.stop();
      if (timer != null) {
            timer.stopTimer();
        }
    }

    public void resetTimer() {
      //elapsedTime = 0;
      //lbTimer.setText("Thời gian: 0s");
      if (timer != null) {
            timer.resetTimer();
            timer = new TimerThread(lbTimer);
        }
    }
  //----------------------------------Timer------------------------------
    
    public void addListener(ITrans event) 
    {
        listener = event;
    }
    
    public void updateStatus(int numSquareClosed) 
    {
        lbNumSquareClosed.setText("Số ô chưa mở: " + numSquareClosed);
        if (numSquareClosed == Board.n_mines) {
          lbNotify.setText("THẮNG");
          lbNotify.setForeground(Color.blue);
          stopTimer();//thắng thì dừng đồng hồ
          
          
         elapsedTime = timer.getElapsedTime();
            Player pl = new Player();
            pl.setTimeWin(elapsedTime+1);
              pl.setPlayerName(playerName);
            RankDao.UpdatePlyer(pl);
            JOptionPane.showMessageDialog(this, "Thanh Cong");
            
        } else if (numSquareClosed == 0) {
          lbNotify.setText("THUA");
          lbNotify.setForeground(Color.red);
          stopTimer();//thua thì dừng đồng hồ
        }
    }
}
