package com.project.progettorisikorisikiamobackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.obiettivi.ConquestContinent;
import com.project.progettorisikorisikiamobackend.obiettivi.ContinentPlaceholder;
import com.project.progettorisikorisikiamobackend.obiettivi.NumTerritories;

public class ObjectiveTests {
    @Test // Test obiettivo conquista territori
    void testNumTerritories() {
        // Test per conferma coverege
        NumTerritories n = new NumTerritories(6);
        ContinentPlaceholder c = new ContinentPlaceholder("Africa", 6);

        // test
        assertEquals(6, n.getNumTerritories());
        n.setNumTerritories(10);
        assertEquals(10, n.getNumTerritories());
        assertEquals("Ottieni 10 territori", n.getObjDescription());
        assertTrue(!n.isCompleted(null));
        n.setCompleted();
        assertTrue(n.isCompleted(null));
    }

    @Test // Test obiettivo conquista continente
    void testConquestContinent() {
        // Test per conferma coverege
        ContinentPlaceholder c = new ContinentPlaceholder("Europa", 22);
        ConquestContinent result = new ConquestContinent(c);

        // test
        assertEquals(null, result.getContinent());
        result.setContinent(null);
        assertEquals(null, result.getContinent());
        assertEquals("Ottieni il continente null", result.getObjDescription());
        assertTrue(!result.isCompleted(null));
        result.setCompleted();
        assertTrue(result.isCompleted(null));
    }
    //Test obiettivo sconfitta nemico

}
