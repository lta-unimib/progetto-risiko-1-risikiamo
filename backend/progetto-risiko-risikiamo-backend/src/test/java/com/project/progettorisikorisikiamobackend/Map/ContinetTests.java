package com.project.progettorisikorisikiamobackend.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Iterator;

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

    // test: isOwnedBy()
    @Test
    public void testIsOwnedBy() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        Continet continet = new Continet("Continet1", 1);
        assertThrows(IllegalArgumentException.class, () -> continet.isOwnedBy(player1));
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isOwnedBy(player1));
        territory2.setOwner(new PlayerPlaceholder("Player2"));
        assertEquals(false, continet.isOwnedBy(player1));
    }

    // test: isOwned()
    @Test
    public void testIsOwned() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        Continet continet = new Continet("Continet1", 1);
        assertThrows(IllegalArgumentException.class, () -> continet.isOwned());
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        territory1.setOwner(player1);
        territory2.setOwner(player1);
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(true, continet.isOwned());
        assertThrows(IllegalArgumentException.class, () -> territory2.setOwner(null));
        territory2.setOwner(new PlayerPlaceholder("Player2"));
        assertEquals(false, continet.isOwned());
    }

    // test: isTerritoryInContinet(Territory territory)
    @Test
    public void testIsTerritoryInContinet() {
        Continet continet = new Continet("Continet1", 1);
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
    public void testIsTerritoryInContinet2() {
        Continet continet = new Continet("Continet1", 1);
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
    public void testGetTerritory() {
        Continet continet = new Continet("Continet1", 1);
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
    public void testAddTerritory() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("id1"));
        assertEquals(territory2, continet.getTerritories().get("id2"));
    }

    // test: addTerritory(Territory territory)
    @Test
    public void testAddTerritory2() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1);
        continet.addTerritory(territory2);
        assertEquals(2, continet.getTerritories().values().size());
        assertEquals(territory1, continet.getTerritories().get("Territory1"));
        assertEquals(territory2, continet.getTerritories().get("Territory2"));
    }

    // test: removeTerritory(String territoryId)
    @Test
    public void testRemoveTerritory() {
        Continet continet = new Continet("Continet1", 1);
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
    public void testRemoveTerritory2() {
        Continet continet = new Continet("Continet1", 1);
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
    public void testGetNumberOfTerritories() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        assertEquals(2, continet.getNumberOfTerritories());
    }

    // test: getNumberOfTerritories(PlayerPlaceholder player)
    @Test
    public void testGetNumberOfTerritories2() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

        continet.addTerritory(territory1, "id1");
        continet.addTerritory(territory2, "id2");
        continet.addTerritory(territory3, "id3");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(2, continet.getNumberOfTerritories(player1));
        assertEquals(0, continet.getNumberOfTerritories(null));

    }

    // test: getTerritories(PlayerPlaceholder player)
    @Test
    public void testGetTerritories() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

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
    public void testEquals() {
        Continet continet1 = new Continet("Continet1", 1);
        Continet continet2 = new Continet("Continet1", 1);

        assertEquals(continet1, continet2);

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        continet1.addTerritory(territory1, "id1");
        continet1.addTerritory(territory2, "id2");

        continet2.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(continet1, continet2);

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(continet1, continet2);

        territory1.setOwner(new PlayerPlaceholder("Player2"));

        assertEquals(continet1, continet2);

    }

    // test: hashCode
    @Test
    public void testHashCode() {
        Continet continet1 = new Continet("Continet1", 1);
        Continet continet2 = new Continet("Continet1", 1);

        assertEquals(continet1.hashCode(), continet2.hashCode());

        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");

        continet1.addTerritory(territory1, "id1");
        continet1.addTerritory(territory2, "id2");

        continet2.addTerritory(territory1, "id1");
        continet2.addTerritory(territory2, "id2");

        assertEquals(continet1.hashCode(), continet2.hashCode());

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

        territory1.setOwner(player1);
        territory2.setOwner(player1);

        assertEquals(continet1.hashCode(), continet2.hashCode());

        territory1.setOwner(new PlayerPlaceholder("Player2"));

        assertEquals(continet1.hashCode(), continet2.hashCode());

    }

    // test: setOwner(PlayerPlaceholder player)
    @Test
    public void testSetOwner() {
        Continet continet = new Continet("Continet1", 1);
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

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
