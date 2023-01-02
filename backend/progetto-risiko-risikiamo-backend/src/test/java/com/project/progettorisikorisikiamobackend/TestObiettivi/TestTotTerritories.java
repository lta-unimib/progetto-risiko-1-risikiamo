package com.project.progettorisikorisikiamobackend.TestObiettivi;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.project.progettorisikorisikiamobackend.map.*;

import org.junit.jupiter.api.Test;

public class TestTotTerritories {
    //Test obiettivo totTerritories
    @Test
    public void testTotTerritories() {
        Map map = new Map("map1");
        Continent continent = new Continent("continent1", 1);
        for (int i = 0; i < 30; i++) {
            Territory territory = new Territory("territory" + i);
            continent.addTerritory(territory);
        }
        map.addContinet(continent, "America");
        PlayerPlaceholder player = new PlayerPlaceholder("player1");
        for (Territory territory : continent.getTerritories().values()) {   
            territory.setOwner(player);
        }
        Objective obiettivo = new TotTerritories(30, map);
        assertTrue(obiettivo.isCompleted(player));
        assertEquals("Conquista 30 territori", obiettivo.getObjDescription());
        PlayerPlaceholder player2 = new PlayerPlaceholder("player2");
        assertTrue (!obiettivo.isCompleted(player2));
    }
}