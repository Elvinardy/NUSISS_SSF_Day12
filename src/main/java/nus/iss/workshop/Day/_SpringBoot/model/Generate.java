package nus.iss.workshop.Day._SpringBoot.model;

import java.io.Serializable;

public class Generate implements Serializable {
    
    private int numberVal;      //setting of getter and setter for numberVal

    public void setNumberVal(int numberVal) {
        this.numberVal = numberVal;

    }

    public int getNumberVal() {
        return this.numberVal;
    }

}
