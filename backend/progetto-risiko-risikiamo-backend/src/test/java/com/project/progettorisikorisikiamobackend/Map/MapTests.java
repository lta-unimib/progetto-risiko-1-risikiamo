package com.project.progettorisikorisikiamobackend.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;

public class MapTests {

    // Test: constructor
    @Test
    void constructor() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());
    }

    // Test: addContinet(Continet continet, String continetId)
    @Test
    void addContinet() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(2, map.getContinents().size());
        Collection<Continent> continets = map.getContinents();
        assertEquals(Continent, continets.toArray()[1]);
        assertEquals(continet2, continets.toArray()[0]);

        assertThrows(IllegalArgumentException.class, () -> map.addContinet(Continent, "id1"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(continet2, "id2"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(Continent, "id2"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(continet2, "id1"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(null, "id1"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(Continent, null));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(null, null));

    }

    // Test: isContinetInMap(Continet continet)
    @Test
    void isContinetInMap() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(true, map.isContinetInMap(Continent));
        assertEquals(true, map.isContinetInMap(continet2));
        assertEquals(false, map.isContinetInMap(new Continent("Continet3", 3)));
    }

    // Test: isContinetInMap(String continetId)
    @Test
    void isContinetInMap2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(true, map.isContinetInMap("id1"));
        assertEquals(true, map.isContinetInMap("id2"));
        assertEquals(false, map.isContinetInMap("id3"));
    }

    // Test : isTerritoryInMap(Territory territory)
    @Test
    void isTerritoryInMap() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

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
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(true, map.isTerritoryInMap("id1"));
        assertEquals(true, map.isTerritoryInMap("id2"));
        assertEquals(false, map.isTerritoryInMap("id3"));
    }

    // Test: Continet getContinet(String continetId)
    @Test
    void getContinet() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(Continent, map.getContinet("id1"));
        assertEquals(continet2, map.getContinet("id2"));
        assertEquals(null, map.getContinet("id3"));

    }

    // Test: Territory getTerritory(String territoryId)
    @Test
    void getTerritory() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(territory1, map.getTerritory("id1"));
        assertEquals(territory2, map.getTerritory("id2"));
        assertEquals(null, map.getTerritory("id3"));
    }

    // Test : void removeContinet(String continetId)
    @Test
    void removeContinet() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(2, map.getContinents().size());
        map.removeContinet("id1");
        assertEquals(1, map.getContinents().size());
        map.removeContinet("id2");
        assertEquals(0, map.getContinents().size());
    }

    // Test : void removeContinet(Continet continet)
    @Test
    void removeContinet2() {
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents().size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(2, map.getContinents().size());
        map.removeContinet(Continent);
        assertEquals(1, map.getContinents().size());
        map.removeContinet(continet2);
        assertEquals(0, map.getContinents().size());
    }

    // Test : int getNumberOfContinent(PlayerPlaceholder player)
    @Test
    void getNumberOfContinent() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("Player2");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        Continent.setOwner(player1);

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        Territory territory1 = new Territory("Territory1");

        Continent.addTerritory(territory1, "id1");

        Continent.setOwner(player1);

        assertEquals(1, map.getNumberOfContinent(player1));
        assertEquals(0, map.getNumberOfContinent(player2));

        territory1.setOwner(player2);

        continet2.setOwner(player2);

        assertEquals(0, map.getNumberOfContinent(player1));
        assertEquals(1, map.getNumberOfContinent(player2));

    }

    // test : int getNumberOfTerritories(PlayerPlaceholder player)
    @Test
    void getNumberOfTerritories() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("Player2");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(0, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(0, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        territory1.setOwner(player1);

        assertEquals(1, map.getNumberOfTerritories(player1));
        assertEquals(0, map.getNumberOfTerritories(player2));

        territory2.setOwner(player2);

        assertEquals(1, map.getNumberOfTerritories(player1));
        assertEquals(1, map.getNumberOfTerritories(player2));

    }

    // Test : getContinents(PlayerPlaceholder player) throws
    // IllegalArgumentException
    @Test
    void getContinents() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("Player2");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continent Continent = new Continent("Continent", 1);
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(0, map.getContinents(player1).size());
        assertEquals(0, map.getContinents(player2).size());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        Continent.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

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
        Continent continet2 = new Continent("Continet2", 2);

        map.addContinet(Continent, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(2, map.getContinents().size());
    }

    // Test : equals(Object obj)
    @Test
    void equals() {
        Map map1 = new Map("Map1");
        Map map2 = new Map("Map2");
        Map map3 = new Map("Map1");

        assertEquals(map1, map3);
        assertNotEquals(map1, map2);
    }

    // Test : hashCode()
    @Test
    void hashCodeTest() {
        Map map1 = new Map("Map1");
        Map map2 = new Map("Map2");
        Map map3 = new Map("Map1");

        assertEquals(map1.hashCode(), map3.hashCode());
        assertNotEquals(map1.hashCode(), map2.hashCode());
    }

}