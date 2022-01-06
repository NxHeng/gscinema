/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gscinema;

import javax.swing.JToggleButton;

/**
 *
 * @author e-hen
 */
public class Seat {
    private String num;
    private boolean isBooked;
    private int showid;
    JToggleButton button;
    public Seat(String num, boolean isBooked, int showid, JToggleButton button){
        this.num = num;
        this.isBooked = isBooked;
        this.showid = showid;
        this.button = button;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public JToggleButton getButton() {
        return button;
    }

    public void setButton(JToggleButton button) {
        this.button = button;
    }
    
}
