package com.project.progettorisikorisikiamobackend.TestObiettivi;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.project.progettorisikorisikiamobackend.map.*;
import org.junit.jupiter.api.Test;

public class TestConquestContinent {
    //Test obiettivo conquestContinent
    @Test
    public void testConquestContinent() {
        Map map = new Map("map1");
        Continent continent = new Continent("continent1", 1);
        for (int i = 0; i < 25; i++) {
            Territory territory = new Territory("territory" + i);
            continent.addTerritory(territory);
        }
        map.addContinet(continent, "America");
        PlayerPlaceholder player = new PlayerPlaceholder("player1");
        for (Territory territory : continent.getTerritories().values()) {   
            territory.setOwner(player);
        }
        Objective obiettivo = new ConquestContinent(continent);
        assertTrue(obiettivo.isCompleted(player));
        assertEquals("Conquista il continente continent1", obiettivo.getObjDescription());
        PlayerPlaceholder player2 = new PlayerPlaceholder("player2");
        continent.getTerritories().get("territory1").setOwner(player2);
        assertTrue (!obiettivo.isCompleted(player));
    }
}
