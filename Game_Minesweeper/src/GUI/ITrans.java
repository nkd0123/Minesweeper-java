/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Model.Square;
/**
 *
 * @author tranv
 */
public interface ITrans {
    Square[][] getListSquare(); // lấy ds các ô
    void play(int x, int y); // xử lí sự kiện chơi tại vị trí (x,y)
    void target(int x, int y); // Xử lý sự kiện đánh dấu tại vị trí (x, y).
    void restart();
}
