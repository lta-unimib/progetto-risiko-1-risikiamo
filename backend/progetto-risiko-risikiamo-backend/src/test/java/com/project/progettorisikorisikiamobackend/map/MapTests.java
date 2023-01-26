package com.project.progettorisikorisikiamobackend.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.player.Player;

class MapTests {

    // Test: constructor
    @Test
    void constructor() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());
    }

    // Test: addContinent(Continent continent, String continentId)
    @Test
    void addContinent() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(2, map.getContinents().size());
        Collection<Continent> continents = map.getContinents().values();
        assertEquals(Continent, continents.toArray()[0]);
        assertEquals(continent2, continents.toArray()[1]);

        assertThrows(IllegalArgumentException.class, () -> map.addContinent(Continent));
        assertThrows(IllegalArgumentException.class, () -> map.addContinent(continent2));
        assertThrows(IllegalArgumentException.class, () -> map.addContinent(Continent));
        assertThrows(IllegalArgumentException.class, () -> map.addContinent(continent2));
        assertThrows(IllegalArgumentException.class, () -> map.addContinent(null));

    }

    // Test: isContinentInMap(Continent continent)
    @Test
    void isContinentInMap() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(true, map.isContinentInMap(Continent));
        assertEquals(true, map.isContinentInMap(continent2));
        assertEquals(false, map.isContinentInMap(new Continent("Continent3", 3)));
    }

    // Test: isContinentInMap(String continentId)
    @Test
    void isContinentInMap2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(true, map.isContinentInMap("Continent"));
        assertEquals(true, map.isContinentInMap("Continent2"));
        assertEquals(false, map.isContinentInMap("id3"));
    }

    // Test : isTerritoryInMap(Territory territory)
    @Test
    void isTerritoryInMap() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(true, map.isTerritoryInMap(territory1));
        assertEquals(true, map.isTerritoryInMap(territory2));
        assertEquals(false, map.isTerritoryInMap(new Territory("Territory3")));
    }

    // Test : isTerritoryInMap(String territoryId)
    @Test
    void isTerritoryInMap2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(true, map.isTerritoryInMap("Territory1"));
        assertEquals(true, map.isTerritoryInMap("Territory2"));
        assertEquals(false, map.isTerritoryInMap("id3"));
    }

    // Test: Continent getContinent(String continentId)
    @Test
    void getContinent() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(Continent, map.getContinent("Continent"));
        assertEquals(continent2, map.getContinent("Continent2"));
        assertEquals(null, map.getContinent("id3"));

    }

    // Test: Territory getTerritory(String territoryId)
    @Test
    void getTerritory() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(territory1, map.getTerritory("Territory1"));
        assertEquals(territory2, map.getTerritory("Territory2"));
        assertEquals(null, map.getTerritory("id3"));
    }

    // Test : void removeContinent(String continentId)
    @Test
    void removeContinent() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(2, map.getContinents().size());
        map.removeContinent("Continent");
        assertEquals(1, map.getContinents().size());
        map.removeContinent("Continent2");
        assertEquals(0, map.getContinents().size());
    }

    // Test : void removeContinent(Continent continent)
    @Test
    void removeContinent2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(2, map.getContinents().size());
        map.removeContinent(Continent);
        assertEquals(1, map.getContinents().size());
        map.removeContinent(continent2);
        assertEquals(0, map.getContinents().size());
    }

    // Test : int getNumberOfContinent(Player player)
    @Test
    void getNumberOfContinent() {
        Player player1 = new Player("Player1", null, null, "123");
        Player player2 = new Player("Player2", null, null, "123");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        Continent.setOwner(player1);

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        Territory territory1 = new Territory("Territory1");

        Continent.addTerritory(territory1);

        Continent.setOwner(player1);

        assertEquals(1, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        territory1.setOwner(player2);

        continent2.setOwner(player2);

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(1, map.getNumberOfContinent(player2));

    }

    // test : int getNumberOfTerritories(Player player)
    @Test
    void getNumberOfTerritories() {
        Player player1 = new Player("Player1", null, null, "123");
        Player player2 = new Player("Player2", null, null, "123");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(0, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(0, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        territory1.setOwner(player1);

        assertEquals(1, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        territory2.setOwner(player2);

        assertEquals(1, map.getNumberOfTerritories(player1));
        assertEquals(1, map.getNumberOfTerritories(player2));

    }

    // Test : getContinents(Player player) throws
    // IllegalArgumentException
    @Test
    void getContinents() {
        Player player1 = new Player("Player1", null, null, "123");
        Player player2 = new Player("Player2", null, null, "123");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(0, map.getContinents(player1).size());
        assertEquals(0, map.getContinents(player2).size());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1);
        continent2.addTerritory(territory2);

        assertEquals(0, map.getContinents(player1).size());
        assertEquals(0, map.getContinents(player2).size());

        territory1.setOwner(player1);

        assertEquals(1, map.getContinents(player1).size());
        assertEquals(0, map.getContinents(player2).size());

        territory2.setOwner(player2);

        assertEquals(1, map.getContinents(player1).size());
        assertEquals(1, map.getContinents(player2).size());

    }

    // Test : getContinents()
    @Test
    void getContinents2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continent2 = new Continent("Continent2", 2);

        map.addContinent(Continent);
        map.addContinent(continent2);

        assertEquals(2, map.getContinents().size());
    }

}