package com.project.progettorisikorisikiamobackend;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.Dice;



public class DiceTests {
    @Test // Test dado
    void testDice() {
        // Test per conferma coverege
        Dice d = new Dice(6);

        // test
        assertEquals(6, d.getSides());
        assertEquals(0, d.getValue());
        d.roll();
        assertTrue(d.getValue() > 0 && d.getValue() <= 6);
        d.setValue(5);
        assertEquals(5, d.getValue());
        d.setSides(10);
        assertEquals(10, d.getSides());

    }

}