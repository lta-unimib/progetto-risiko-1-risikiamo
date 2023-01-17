package com.project.progettorisikorisikiamobackend.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.player.Player;

class ContinentTests {

    // test: Constructor
    @Test
    void testConstructor() {
        Continent continent = new Continent("Continent", 1);
        assertEquals("Continent", continent.getName());
        assertEquals(1, continent.getBonus());
        assertEquals(0, continent.getTerritories().values().size());
    }

    // test: getOwner()
    @Test
    void testGetOwner() {
        Player player1 = new Player("Player1", null, null, "123");
        Continent continent = new Continent("Continent", 1);
        assertEquals(null, continent.getOwner());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals("Player1", continent.getOwner().getName());
        territory2.setOwner(new Player("Player2", null, null, "321"));
        assertEquals(null, continent.getOwner());
    }

    // test: isOwnedBy()
    @Test
    void testIsOwnedBy() {
        Player player1 = new Player("Player1", null, null, "123");
        Continent continent = new Continent("Continent", 1);
        assertEquals(false, continent.isOwnedBy(player1));
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(true, continent.isOwnedBy(player1));
        territory2.setOwner(new Player("Player2", null, null, "321"));
        assertEquals(false, continent.isOwnedBy(player1));
    }

    // test: isOwned()
    @Test
    void testIsOwned() {
        Player player1 = new Player("Player1", null, null, "123");
        Continent continent = new Continent("Continent", 1);
        assertEquals(false, continent.isOwned());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(true, continent.isOwned());
        assertThrows(IllegalArgumentException.class, () -> territory2.setOwner(null));
        territory2.setOwner(new Player("Player2", null, null, "123"));
        assertEquals(false, continent.isOwned());
    }

    // test: isTerritoryInContinent(Territory territory)
    @Test
    void testIsTerritoryInContinent() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(true, continent.isTerritoryInContinent(territory1));
        assertEquals(true, continent.isTerritoryInContinent(territory2));
        Territory territory3 = new Territory("Territory3");
        assertEquals(false, continent.isTerritoryInContinent(territory3));
    }

    // test: isTerritoryInContinent(String territoryId)
    @Test
    void testIsTerritoryInContinent2() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(true, continent.isTerritoryInContinent("Territory1"));
        assertEquals(true, continent.isTerritoryInContinent("Territory2"));
        assertEquals(false, continent.isTerritoryInContinent("id3"));
    }

    // test: getTerritory(String territoryId)
    @Test
    void testGetTerritory() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(territory1, continent.getTerritory("Territory1"));
        assertEquals(territory2, continent.getTerritory("Territory2"));
        assertEquals(null, continent.getTerritory("id3"));
    }

    // test: addTerritory(Territory territory, String id)
    @Test
    void testAddTerritory() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(2, continent.getTerritories().values().size());
        assertEquals(territory1, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));

        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory1));
        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory2));

        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory1));
        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory2));

        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory1));
        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory2));

    }

    // test: addTerritory(Territory territory)
    @Test
    void testAddTerritory2() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(2, continent.getTerritories().values().size());
        assertEquals(territory1, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));

        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory1));
        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(territory2));

        assertThrows(IllegalArgumentException.class, () -> continent.addTerritory(null));

    }

    // test: removeTerritory(String territoryId)
    @Test
    void testRemoveTerritory() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(2, continent.getTerritories().values().size());
        assertEquals(territory1, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));
        continent.removeTerritory("Territory1");
        assertEquals(1, continent.getTerritories().values().size());
        assertEquals(null, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));
    }

    // test: removeTerritory(Territory territory)
    @Test
    void testRemoveTerritory2() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(2, continent.getTerritories().values().size());
        assertEquals(territory1, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));
        continent.removeTerritory(territory1);
        assertEquals(1, continent.getTerritories().values().size());
        assertEquals(null, continent.getTerritories().get("Territory1"));
        assertEquals(territory2, continent.getTerritories().get("Territory2"));
    }

    // test: getNumberOfTerritories()
    @Test
    void testGetNumberOfTerritories() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        assertEquals(2, continent.getNumberOfTerritories());
    }

    // test: getNumberOfTerritories(Player player)
    @Test
    void testGetNumberOfTerritories2() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, "123");

        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        continent.addTerritory(territory3);

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(2, continent.getNumberOfTerritories(player1));
        assertEquals(0, continent.getNumberOfTerritories(null));

    }

    // test: getTerritories(Player player)
    @Test
    void testGetTerritories() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, "123");

        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        continent.addTerritory(territory3);

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        Iterator<Territory> iterator = continent.getTerritories(player1).iterator();
        assertEquals(2, continent.getTerritories(player1).size());
        assertEquals(territory2, iterator.next());
        assertEquals(territory1, iterator.next());

        assertEquals(0, continent.getTerritories(null).size());
    }

    // test: Equals
    @Test
    void testEquals() {
        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent", 1);

        assertEquals(Continent, continent2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        Continent.addTerritory(territory2);

        continent2.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(Continent, continent2);

        Player player1 = new Player("Player1", null, null, "123");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(Continent, continent2);

        territory1.setOwner(new Player("Player2", null, null, "123"));

        assertEquals(Continent, continent2);

    }

    // test: hashCode
    @Test
    void testHashCode() {
        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent", 1);

        assertEquals(Continent.hashCode(), continent2.hashCode());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        Continent.addTerritory(territory2);

        continent2.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(Continent.hashCode(), continent2.hashCode());

        Player player1 = new Player("Player1", null, null, "123");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(Continent.hashCode(), continent2.hashCode());

        territory1.setOwner(new Player("Player2", null, null, "123"));

        assertEquals(Continent.hashCode(), continent2.hashCode());

    }

    // test: setOwner(Player player)
    @Test
    void testSetOwner() {
        Continent continent = new Continent("Continent", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        Player player1 = new Player("Player1", null, null, "123");

        continent.addTerritory(territory1);
        continent.addTerritory(territory2);
        continent.addTerritory(territory3);

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(2, continent.getNumberOfTerritories(player1));
        assertEquals(0, continent.getNumberOfTerritories(null));

        continent.setOwner(player1);

        assertEquals(3, continent.getNumberOfTerritories(player1));
        assertEquals(0, continent.getNumberOfTerritories(null));

        territory1.addArmy(2);

        assertThrows(IllegalArgumentException.class, () -> continent.setOwner(player1));

    }
}
