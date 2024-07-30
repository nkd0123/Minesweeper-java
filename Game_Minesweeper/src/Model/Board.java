/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Random;

/**
 *
 * @author tranv
 */
public class Board {
    //Kích thước bảng
    public static final int n_rows = 16;
    public static final int n_columns = 16;
    
    public static final int n_mines = (n_rows*n_columns)/8; //số lượng mìn
    public int timeSecond;
    private Square[][] square; // mảng 2 chiều chứa các ô
    //private Timer timer;
    
    public Board() //Khởi tạo bảng, đặt mìn ngẫu nhiên, và tính số mìn xung quanh mỗi ô.
    {
        this.square = new Square[n_rows][n_columns];
        for (Square[] square1 : square) {
            for (int j = 0; j < square[0].length; j++) {
                square1[j] = new Square();
            }
        }
        
        //Đặt mìn vào vị trí ngẫu nhiên
        for(int i = 0; i < n_mines; i++)
        {
            int x = genRan(n_rows);
            int y = genRan(n_columns);
            //Nếu có mìn thì đặt ô khác
            while(square[x][y].isHasMine())
            {
                x = genRan(n_rows);
                y = genRan(n_columns);
            }
            square[x][y].setHasMine(true);
        }
        
        //Ghi số lượng mìn xung quanh vào ô
        for(int i =0; i< square.length; i++)
        {
            for(int j = 0; j < square[0].length; j++)
            {
                int count =0;//Dem so luong min 
                for(int m = -1; m <= 1; m++)
                {
                    if(i + m < 0)
                        m++;
                    if(i + m > n_rows - 1)
                        break;
                    for(int n = -1; n <= 1; n++)
                    {
                        if(j + n < 0)
                            n++;
                        if(j + n > n_columns - 1)
                            break;
                        if(!(m == 0 && n == 0) && square[i + m][j + n].isHasMine())
                            count++;
                    }
                }
                square[i][j].setnMineAroud(count);
            }
        }
    }
    
    private int genRan(int x) //Sinh số ngẫu nhiên trong khoảng.
    {
        Random r = new Random();
        return r.nextInt(x);
    }
    
    public Square[][] getListSquare() //Trả về mảng các ô.
    {
        return square;
    }
    
    public boolean play(int x, int y) //Mở ô tại vị trí (x, y) và thực hiện các hành động tương ứng.
    {
        if(!square[x][y].isOpen())
        {
            square[x][y].setOpen(true);
            if(square[x][y].isHasMine())
                return false;
            //DE quy mo cac o xung quanh
            if(square[x][y].getnMineAroud() == 0)
            {
                for(int m = -1; m <= 1; m++)
                {
                    if(x + m < 0)
                        m++;
                    if(x + m > n_rows - 1)
                        break;
                    for(int n = -1; n <= 1; n++)
                    {
                        if(y + n < 0)
                            n++;
                        if(y + n > n_columns - 1)
                            break;
                        play(x + m, y + n);
                    }
                }
            }
        }
        return true;
    }
    
    public void target(int x, int y) //Đánh dấu hoặc gỡ đánh dấu ô tại vị trí (x, y).
    {
        if(!square[x][y].isOpen())
        {
            if(!square[x][y].isTarget())
                square[x][y].setTarget(true);
            else
                square[x][y].setTarget(false);
        }
    }
    
    public void showAllSquares() //Mở tất cả các ô.
    {
        //for(int i = 0; i < square.length; i++)
        for (Square[] square1 : square) {
            for (int j = 0; j < square[0].length; j++) {
                square1[j].setOpen(true);
            }
        }
    }
}

