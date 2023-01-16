package com.project.progettorisikorisikiamobackend.TestObiettivi;

import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.project.progettorisikorisikiamobackend.map.*;

import org.junit.jupiter.api.Test;

public class TestTotTerritories {
    // Test obiettivo totTerritories
    @Test
    public void testTotTerritories() {
        Map map = new Map("map1");
        Continent continent = new Continent("continent1", 1);
        for (int i = 0; i < 30; i++) {
            Territory territory = new Territory("territory" + i);
            continent.addTerritory(territory);
        }
        map.addContinent(continent);
        Player player = new Player("player1", null, null, "0");
        for (Territory territory : continent.getTerritories().values()) {
            territory.setOwner(player);
        }
        Objective obiettivo = new TotTerritories(30, map);
        assertTrue(obiettivo.isCompleted(player));
        assertEquals("Conquista 30 territori", obiettivo.getObjDescription());
        Player player2 = new Player("player2", null, obiettivo, "0");
        assertTrue(!obiettivo.isCompleted(player2));
    }
}