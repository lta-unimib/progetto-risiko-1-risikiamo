package com.project.progettorisikorisikiamobackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.obiettivi.Obiettivi;

public class ObiettiviTests {
    @Test // Testa il metodo distribuisciObiettivi()
    public void testDistribuisciObiettivi()  {
        Obiettivi obj = new Obiettivi();
        List<Integer> player = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            player.add(i);
        }
        HashMap<Integer, Enum> solution= obj.distribuisciObiettivi(player);
        assertEquals(6, solution.size());
        obj= new Obiettivi();
        player = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            player.add(i);
        }
        solution= obj.distribuisciObiettivi(player);
        assertEquals(5, solution.size());
        obj= new Obiettivi();
        player = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            player.add(i);
        }
        solution= obj.distribuisciObiettivi(player);
        assertEquals(2, solution.size());
    }
}
