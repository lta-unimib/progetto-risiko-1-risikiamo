package com.project.progettorisikorisikiamobackend.Turno;
//dado a facce variabili

import java.security.SecureRandom;

public class Dice {
    private int value;
    private int sides;
    private static SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases

    public Dice(int sides) {
        this.sides = sides;
        this.value = 0;

    }

    public int roll() {
        this.value = random.nextInt(this.sides) + 1;

        return this.value;
    }

    public int getValue() {
        return this.value;
    }

    public int getSides() {
        return this.sides;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
}