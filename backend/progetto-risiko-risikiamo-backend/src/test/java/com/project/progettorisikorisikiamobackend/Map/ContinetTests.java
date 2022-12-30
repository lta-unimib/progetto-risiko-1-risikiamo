package com.project.progettorisikorisikiamobackend.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ContinetTests {

    // test: Constructor
    @Test
    public void testConstructor() {
        Continet continet = new Continet("Continet1", 1);
        assertEquals("Continet1", continet.getName());
        assertEquals(1, continet.getBonus());
        assertEquals(0, continet.getTerritories().values().size());
    }

    // test: getOwner()
    @Test
    public void testGetOwner() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        Continet continet = new Continet("Continet1", 1);
        assertThrows(IllegalArgumentException.class, () -> continet.getOwner());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals("Player1", continet.getOwner().getName());
        territory2.setOwner(new PlayerPlaceholder("Player2"));
        assertEquals(null, continet.getOwner());
    }

    // Test: testContinet
    @Test
    public void testContinet() {

        Continet continet = new Continet("Continet1", 1);
        assertEquals("Continet1", continet.getName());
        assertEquals(1, continet.getBonus());
        assertEquals(0, continet.getTerritories().values().size());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");

        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, "id1"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory2, "id3"));

        Territory territory3 = new Territory("Territory3");

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory3, "id1"));

        continet.removeTerritory("id1");

        assertEquals(1, continet.getTerritories().values().size());
        continet.addTerritory(territory3, "id1");
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory3, continet.getTerritories().get("id1"));

    }

    // test : testEqualContinent
    @Test
    public void testEqualContinent() {
        Continet continet1 = new Continet("Continet1", 1);
        Continet continet2 = new Continet("Continet1", 1);
        Continet continet3 = new Continet("Continet2", 1);
        Continet continet4 = new Continet("Continet1", 2);

        assertEquals(continet1, continet2);
        assertEquals(continet1.hashCode(), continet2.hashCode());

        assertNotEquals(continet1, continet3);
        assertNotEquals(continet1, continet4);
        assertNotEquals(continet3, continet4);

        assertEquals(continet2, continet1);
    }

    // test : TestOwedTerritory
    @Test
    public void TestOwedTerritory() {
        Continet continet1 = new Continet("Continet1", 1);

        PlayerPlaceholder player1 = new PlayerPlaceholder("player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("player2");

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        Territory territory4 = new Territory("Territory4");

        continet1.addTerritory(territory1, "id1");
        continet1.addTerritory(territory2, "id2");
        continet1.addTerritory(territory3, "id3");
        continet1.addTerritory(territory4, "id4");

        territory1.setOwner(player1);
        territory2.setOwner(player1);
        territory3.setOwner(player1);
        territory4.setOwner(player2);

        assertEquals(3, continet1.getTerritories(player1).size());
        assertEquals(1, continet1.getTerritories(player2).size());

    }
}
