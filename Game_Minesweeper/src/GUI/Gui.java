/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.Panel.BoardPanel;
import GUI.Panel.ControlPanel;

import javax.swing.JFrame;
import Model.Board;
import Model.Square;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author tranv
 */
public class Gui extends JFrame implements ICommon, ITrans{
    private static final String Title = "MineSweeper";
    private static final int w = 730;
    private static final int h = 700;
    private BoardPanel boardPanel;
    private ControlPanel controlPanel;
    private Board board;
    private boolean isGameStarted;
    
    private String playerName;
    
    
    public Gui(String name)
    {
        playerName = name;
        board = new Board();
        isGameStarted = false;
        initComp();
        addComp();
        addEvent();
        controlPanel.startTimer();
    }
    
    @Override
    public void initComp() {
        setTitle(Title);
        setSize(w,h);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addComp() {
        boardPanel = new BoardPanel();
        boardPanel.setBounds(10, 150, 700, 500);
        add(boardPanel);
        boardPanel.addListener(this);

        controlPanel = new ControlPanel(playerName);
        controlPanel.setBounds(10, 0, 700, 120);
        add(controlPanel);
        controlPanel.addListener(this);
        
        
    }

    @Override
    public void addEvent() 
    {
      WindowListener wd = new WindowAdapter() 
      {
        @Override
        public void windowClosing(WindowEvent e) 
        {
          int kq = JOptionPane.showConfirmDialog(Gui.this, "Bạn có muốn thoát không?",
           "Thông báo", JOptionPane.YES_NO_OPTION);
          if (kq == JOptionPane.YES_OPTION) {
            dispose();
          }
        }
      };
      addWindowListener(wd);
    }

    @Override
    public Square[][] getListSquare() {
        return board.getListSquare();
    }

    @Override
    public void play(int x, int y) 
    {
        if (!isGameStarted) 
        {
            controlPanel.startTimer(); // Bắt đầu đồng hồ khi bắt đầu chơi
            isGameStarted = true;
        }
        boolean check = board.play(x, y);
        if (!check) 
        { 
            board.showAllSquares();
            isGameStarted = false;
            controlPanel.stopTimer();
        }
        boardPanel.updateBoard();
        // cập nhật số ô chưa mở vào controlPanel
        int numSquareClosed = boardPanel.getNumSquareClosed();
        controlPanel.updateStatus(numSquareClosed);
    }

    @Override
    public void target(int x, int y) 
    {
        board.target(x, y);
        boardPanel.updateBoard();
    }

    @Override
    public void restart()
    {
        board = new Board();
        boardPanel.updateBoard();
        isGameStarted  =false;
        controlPanel.resetTimer(); //Đặt lại đồng hồ
    }
    
}
