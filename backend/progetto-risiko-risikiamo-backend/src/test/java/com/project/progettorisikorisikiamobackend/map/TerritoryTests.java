package com.project.progettorisikorisikiamobackend.map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

class TerritoryTests {

    // test: Constructor
    @Test
    void testConstructor() {
        Territory territory = new Territory("Territory1");
        assertEquals("Territory1", territory.getName());
        assertEquals(0, territory.getArmy());
        assertEquals(null, territory.getOwner());

    }

    // test setOwner
    @Test
    void testSetOwner() {
        Territory territory = new Territory("Territory1");
        territory.setOwner(new PlayerPlaceholder("Player1"));
        assertEquals("Player1", territory.getOwner().getName());
        assertThrows(IllegalArgumentException.class, () -> territory.setOwner(null));
        territory.setOwner(new PlayerPlaceholder("Player2"));
        assertEquals("Player2", territory.getOwner().getName());
        territory.addArmy(+1);
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.setOwner(new PlayerPlaceholder("Player3")));
    }

    // test addArmy
    @Test
    void testAddArmy() {
        Territory territory = new Territory("Territory1");
        territory.getArmy();
        assertEquals(0, territory.getArmy());

        assertEquals(null, territory.getOwner());
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(0));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(-1));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(+1));

        territory.setOwner(new PlayerPlaceholder("Player1"));
        assertDoesNotThrow(() -> territory.addArmy(+1));
        assertEquals(1, territory.getArmy());
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(-3));

        Territory territory2 = new Territory("Territory2");
        assertThrowsExactly(IllegalArgumentException.class, () -> territory2.addArmy(+1));

        territory2.setOwner(new PlayerPlaceholder("Player2"));
        assertDoesNotThrow(() -> territory2.addArmy(+1));
        assertEquals(1, territory2.getArmy());

        assertThrowsExactly(IllegalArgumentException.class, () -> territory2.addArmy(-2));

        territory2.addArmy(-1);
        assertEquals(null, territory2.getOwner());

    }

    // test isNeighbor
    @Test
    void testIsNeighbor() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        assertEquals(true, territory1.isNeighbor(territory2));
        assertEquals(true, territory1.isNeighbor(territory3));
        assertEquals(false, territory1.isNeighbor(territory1));
        assertEquals(false, territory1.isNeighbor(new Territory("Territory4")));

        // by id

        assertEquals(true, territory1.isNeighbor("Territory2"));
        assertEquals(true, territory1.isNeighbor("Territory3"));
        assertEquals(false, territory1.isNeighbor("Territory4"));
        assertEquals(false, territory1.isNeighbor("Territory1"));

    }

    // test addNeighbor
    @Test
    void testAddNeighbor() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbor(null));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbor(territory1));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbor(territory2));
    }

    // test removeNeighbor
    @Test
    void testRemoveNeighbor() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        territory1.removeNeighbor(territory2);
        assertEquals(false, territory1.isNeighbor(territory2));
        assertEquals(true, territory1.isNeighbor(territory3));

    }

    // test getNeighbors
    @Test
    void testGetNeighbors() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        assertEquals(2, territory1.getNeighbors().size());
        assertEquals(true, territory1.getNeighbors().contains(territory2));
        assertEquals(true, territory1.getNeighbors().contains(territory3));
        assertEquals(false, territory1.getNeighbors().contains(new Territory("Territory4")));
    }

    // test getNotOwnedNeighbor
    @Test
    void testGetNotOwnedNeighbor() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("Player2");
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        territory2.setOwner(player1);
        territory3.setOwner(player2);

        assertEquals(2, territory1.getNotOwnedNeighbor().length);
        assertEquals(territory2, territory1.getNotOwnedNeighbor()[0]);
        assertEquals(territory3, territory1.getNotOwnedNeighbor()[1]);

        territory1.setOwner(player1);

        assertEquals(1, territory1.getNotOwnedNeighbor().length);
        assertEquals(territory3, territory1.getNotOwnedNeighbor()[0]);

        assertEquals(0, territory2.getNotOwnedNeighbor().length);
        assertEquals(0, territory3.getNotOwnedNeighbor().length);

    }

    // test getOwnedNeighbor
    @Test
    void testGetOwnedNeighbor() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("Player2");
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);

        territory2.setOwner(player1);
        territory3.setOwner(player2);

        assertEquals(0, territory1.getOwnedNeighbor().length);

        territory1.setOwner(player1);

        assertEquals(1, territory1.getOwnedNeighbor().length);
        assertEquals(territory2, territory1.getOwnedNeighbor()[0]);

        territory2.setOwner(player2);

        assertEquals(0, territory1.getOwnedNeighbor().length);

        assertEquals(0, territory2.getOwnedNeighbor().length);

        assertEquals(0, territory3.getOwnedNeighbor().length);
    }

    // test equals
    @Test
    void testEquals() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        assertEquals(true, territory1.equals(territory1));
        assertEquals(false, territory1.equals(territory2));
        assertEquals(false, territory1.equals(null));
        assertEquals(false, territory1.equals(new Territory("Territory1")));

        Territory territory4 = new Territory("Territory1");
        territory4.addNeighbor(territory2);
        territory4.addNeighbor(territory3);
        assertEquals(true, territory1.equals(territory4));

        Object object = new Object();
        assertEquals(false, territory1.equals(object));

        Object object2 = null;
        assertEquals(false, territory1.equals(object2));

    }

    // test hashCode @EqualsAndHashCode lombok
    @Test
    void testHashCode() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbor(territory2);
        territory1.addNeighbor(territory3);
        assertEquals(territory1.hashCode(), territory1.hashCode());
        assertNotEquals(territory1.hashCode(), new Territory("Territory1").hashCode());
        assertNotEquals(territory1.hashCode(), territory2.hashCode());
        assertNotEquals(territory1.hashCode(), territory3.hashCode());

        Territory territory4 = new Territory("Territory1");
        territory4.addNeighbor(territory2);
        territory4.addNeighbor(territory3);

        assertEquals(territory1.hashCode(), territory4.hashCode());

        Object object = new Object();
        assertNotEquals(territory1.hashCode(), object.hashCode());

        assertNotEquals(territory1.hashCode(), null);

    }

}
