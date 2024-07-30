/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import GUI.ICommon;
import java.awt.Label;
import javax.swing.JPanel;
import GUI.ITrans;
import Model.Board;
import Model.Square;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
/**
 *
 * @author tranv
 */
public class BoardPanel extends JPanel implements ICommon{
    private Label[][] lbSquare; //label đại diện cho các ô
    private ITrans listener; //đối tượng thực hiện sự kiện
    private int numSquareClosed; //Số lượng ô chưa mở
    
    public BoardPanel()
    {
        initComp();
        addComp();
        addEvent();
    }
    @Override
      public void initComp() 
      {
        setLayout(new GridLayout(Board.n_rows, Board.n_columns));
      }
      
    @Override
    public void addComp() {
      Border border = BorderFactory.createLineBorder(Color.gray, 1);
      lbSquare = new Label[Board.n_rows][Board.n_columns];
        for (Label[] lbSquare1 : lbSquare) {
            for (int j = 0; j < lbSquare[0].length; j++) {
                lbSquare1[j] = new Label();
                lbSquare1[j].setOpaque(true);
                lbSquare1[j].setBackground(new Color(242, 242, 242));
                lbSquare1[j].setBorder(border);
                lbSquare1[j].setHorizontalAlignment(JLabel.CENTER);
                lbSquare1[j].setVerticalAlignment(JLabel.CENTER);
                add(lbSquare1[j]);
            }
        }
    }
    
    @Override
    public void addEvent() {
      for (int i = 0; i < lbSquare.length; i++) {
        for (int j = 0; j < lbSquare[0].length; j++) {
          lbSquare[i][j].x = i;
          lbSquare[i][j].y = j;
          lbSquare[i][j].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
              Label label = (Label) e.getComponent();
              if (e.getButton() == MouseEvent.BUTTON1) {
                listener.play(label.x, label.y);
              } else if (e.getButton() == MouseEvent.BUTTON3) {
                listener.target(label.x, label.y);
              }
            }
          });
        }
      }
    }
    
    public void addListener(ITrans event) //thêm đối tượng thực hiện sự kiện
    {
        listener = event;
    }
    
    // cập nhật hiển thị
    public void updateBoard() {
      Font font = new Font("VNI", Font.PLAIN, 20);
      numSquareClosed = 0;
      Square[][] listSquare = listener.getListSquare();
      for (int i = 0; i < listSquare.length; i++) {
        for (int j = 0; j < listSquare[0].length; j++) {
          lbSquare[i][j].setFont(font);
          if (!listSquare[i][j].isOpen()) {
            lbSquare[i][j].setBackground(new Color(242, 242, 242));
            lbSquare[i][j].setForeground(Color.black);
            numSquareClosed++;
            if (!listSquare[i][j].isTarget()) {
              lbSquare[i][j].setText("");
            } else {
              lbSquare[i][j].setText("\uD83D\uDEA9"); // ki tu 'flag'
            }
          } else {
            if (listSquare[i][j].isHasMine()) {
              lbSquare[i][j].setText("\uD83D\uDCA3"); // ki tu 'bomb'
            } else {
              int numMineAround = listSquare[i][j].getnMineAroud();
              if (numMineAround == 0) {
                lbSquare[i][j].setText("");
              } else {
                lbSquare[i][j].setText(numMineAround + "");
                // đặt màu số cho dễ nhìn
                switch (numMineAround) {
                case 1:
                  lbSquare[i][j].setForeground(new Color(128, 128, 128));
                  break;
                case 2:
                  lbSquare[i][j].setForeground(new Color(255, 0, 0));
                  break;
                case 3:
                  lbSquare[i][j].setForeground(new Color(0, 204, 0));
                  break;
                case 4:
                  lbSquare[i][j].setForeground(new Color(102, 0, 255));
                  break;
                case 5:
                  lbSquare[i][j].setForeground(new Color(128, 128, 128));
                  break;
                case 6:
                  lbSquare[i][j].setForeground(new Color(255, 0, 0));
                  break;
                case 7:
                  lbSquare[i][j].setForeground(new Color(0, 204, 0));
                  break;
                case 8:
                  lbSquare[i][j].setForeground(new Color(102, 0, 255));
                  break;
                }
              }
            }
            lbSquare[i][j].setBackground(Color.white);
          }
        }
      }
    }
    
    //Lớp con để lưu vị trí của các Label trong GridLayout.
    private class Label extends JLabel {
        private int x;
        private int y;
    }

    //Lấy số lượng ô chưa mở
    public int getNumSquareClosed() {
      return numSquareClosed;
    }
}
