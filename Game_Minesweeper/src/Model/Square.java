/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tranv
 */
public class Square {
    private boolean isOpen; //ô đã mở hay chưa
    private boolean hasMine; // ô chứa mìn or không
    private int nMineAroud; // số lượng mìn quanh ô
    private boolean isTarget; //đánh dấu ô
    
    public Square()
    {
        isOpen = false;
        hasMine = false;
        isTarget = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getnMineAroud() {
        return nMineAroud;
    }

    public void setnMineAroud(int nMineAroud) {
        this.nMineAroud = nMineAroud;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public void setTarget(boolean isTarget) {
        this.isTarget = isTarget;
    }
    

}
