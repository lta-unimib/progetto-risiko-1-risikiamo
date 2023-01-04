package com.project.progettorisikorisikiamobackend.map;
import com.project.progettorisikorisikiamobackend.obiettivi.ConquestContinent;
import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
import com.project.progettorisikorisikiamobackend.player.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class ContinentTests {

    // test: Constructor
    @Test
    void testConstructor() {
        Continent continet = new Continent("Continent", 1);
        assertEquals("Continent", continet.getName());
        assertEquals(1, continet.getBonus());
        assertEquals(0, continet.getTerritories().values().size());
    }

    // test: getOwner()
    @Test
    void testGetOwner() {
        Continent continet = new Continent("Continent", 1);
        Objective objective = new ConquestContinent(continet);
        Player player1 = new Player("Player1", "red",objective, 1);
        assertEquals(null, continet.getOwner());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals("Player1", continet.getOwner().getName());
        territory2.setOwner(new Player("Player2", null, objective, 0));
        assertEquals(null, continet.getOwner());
    }

    // test: isOwnedBy()
    @Test
    void testIsOwnedBy() {
        Player player1 = new Player("Player1", null, null, 0);
        Continent continet = new Continent("Continent", 1);
        assertEquals(false, continet.isOwnedBy(player1));
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isOwnedBy(player1));
        territory2.setOwner(new Player("Player2", null, null, 0));
        assertEquals(false, continet.isOwnedBy(player1));
    }

    // test: isOwned()
    @Test
    void testIsOwned() {
        Player player1 = new Player("Player1", null, null, 0);
        Continent continet = new Continent("Continent", 1);
        assertEquals(false, continet.isOwned());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isOwned());
        assertThrows(IllegalArgumentException.class, () -> territory2.setOwner(null));
        territory2.setOwner(new Player("Player2", null, null, 0));
        assertEquals(false, continet.isOwned());
    }

    // test: isTerritoryInContinet(Territory territory)
    @Test
    void testIsTerritoryInContinet() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isTerritoryInContinet(territory1));
        assertEquals(true, continet.isTerritoryInContinet(territory2));
        Territory territory3 = new Territory("Territory3");
        assertEquals(false, continet.isTerritoryInContinet(territory3));
    }

    // test: isTerritoryInContinet(String territoryId)
    @Test
    void testIsTerritoryInContinet2() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isTerritoryInContinet("id1"));
        assertEquals(true, continet.isTerritoryInContinet("id2"));
        assertEquals(false, continet.isTerritoryInContinet("id3"));
    }

    // test: getTerritory(String territoryId)
    @Test
    void testGetTerritory() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(territory1, continet.getTerritory("id1"));
        assertEquals(territory2, continet.getTerritory("id2"));
        assertEquals(null, continet.getTerritory("id3"));
    }

    // test: addTerritory(Territory territory, String id)
    @Test
    void testAddTerritory() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, "id1"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory2, "id2"));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, "id2"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory2, "id1"));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, "id3"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory2, "id3"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, "id4"));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, null));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1, ""));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(null, "id1"));

    }

    // test: addTerritory(Territory territory)
    @Test
    void testAddTerritory2() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1);
        continet.addTerritory(territory2);
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("Territory1"));
        assertEquals(territory2, continet.getTerritories().get("Territory2"));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory1));
        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(territory2));

        assertThrows(IllegalArgumentException.class, () -> continet.addTerritory(null));

    }

    // test: removeTerritory(String territoryId)
    @Test
    void testRemoveTerritory() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));
        continet.removeTerritory("id1");
        assertEquals(1, continet.getTerritories().values().size());
        assertEquals(null, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));
    }

    // test: removeTerritory(Territory territory)
    @Test
    void testRemoveTerritory2() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));
        continet.removeTerritory(territory1);
        assertEquals(1, continet.getTerritories().values().size());
        assertEquals(null, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));
    }

    // test: getNumberOfTerritories()
    @Test
    void testGetNumberOfTerritories() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getNumberOfTerritories());
    }

    // test: getNumberOfTerritories(Player player)
    @Test
    void testGetNumberOfTerritories2() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, 0);

        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        continet.addTerritory(territory3, "id3");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(2, continet.getNumberOfTerritories(player1));
        assertEquals(0, continet.getNumberOfTerritories(null));

    }

    // test: getTerritories(Player player)
    @Test
    void testGetTerritories() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, 0);

        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        continet.addTerritory(territory3, "id3");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        Iterator<Territory> iterator = continet.getTerritories(player1).iterator();
        assertEquals(2, continet.getTerritories(player1).size());
        assertEquals(territory2, iterator.next());
        assertEquals(territory1, iterator.next());

        assertEquals(0, continet.getTerritories(null).size());
    }

    // test: Equals
    @Test
    void testEquals() {
        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continent", 1);

        assertEquals(Continent, continet2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        Continent.addTerritory(territory2, "id2");

        continet2.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(Continent, continet2);

        Player player1 = new Player("Player1", null, null, 0);

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(Continent, continet2);

        territory1.setOwner(new Player("Player2", null, null, 0));

        assertEquals(Continent, continet2);

    }

    // test: hashCode
    @Test
    void testHashCode() {
        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continent", 1);

        assertEquals(Continent.hashCode(), continet2.hashCode());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        Continent.addTerritory(territory2, "id2");

        continet2.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(Continent.hashCode(), continet2.hashCode());

        Player player1 = new Player("Player1", null, null, 0);

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(Continent.hashCode(), continet2.hashCode());

        territory1.setOwner(new Player("Player2", null, null, 0));

        assertEquals(Continent.hashCode(), continet2.hashCode());

    }

    // test: setOwner(Player player)
    @Test
    void testSetOwner() {
        Continent continet = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, 0);

        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        continet.addTerritory(territory3, "id3");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(2, continet.getNumberOfTerritories(player1));
        assertEquals(0, continet.getNumberOfTerritories(null));

        continet.setOwner(player1);

        assertEquals(3, continet.getNumberOfTerritories(player1));
        assertEquals(0, continet.getNumberOfTerritories(null));

        territory1.addArmy(2);

        assertThrows(IllegalArgumentException.class, () -> continet.setOwner(player1));

    }
}
