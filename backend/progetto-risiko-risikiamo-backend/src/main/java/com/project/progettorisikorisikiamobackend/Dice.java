package com.project.progettorisikorisikiamobackend;
//dado a facce variabili
public class Dice{
    private int value;
    private int sides;
    public Dice(int sides){
        this.sides = sides;
        this.value = 0;
    }
    public int roll(){
        this.value = (int)(Math.random()*this.sides)+1;
        return this.value;
    }
    public int getValue(){
        return this.value;
    }
    public int getSides(){
        return this.sides;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void setSides(int sides){
        this.sides = sides;
    }
}