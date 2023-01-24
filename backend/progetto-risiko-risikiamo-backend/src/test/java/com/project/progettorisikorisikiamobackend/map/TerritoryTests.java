package com.project.progettorisikorisikiamobackend.map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.player.Player;

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
        territory.setOwner(new Player("Player1", null, null, "123"));
        assertEquals("Player1", territory.getOwner().getName());
        assertThrows(IllegalArgumentException.class, () -> territory.setOwner(null));
        territory.setOwner(new Player("Player2", null, null, "123"));
        assertEquals("Player2", territory.getOwner().getName());
        territory.addArmy(+1);
        assertThrowsExactly(IllegalArgumentException.class,
                () -> territory.setOwner(new Player("Player3", null, null, "123")));
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

        territory.setOwner(new Player("Player1", null, null, "123"));
        assertDoesNotThrow(() -> territory.addArmy(+1));
        assertEquals(1, territory.getArmy());
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(-3));

        Territory territory2 = new Territory("Territory2");
        assertThrowsExactly(IllegalArgumentException.class, () -> territory2.addArmy(+1));

        territory2.setOwner(new Player("Player2", null, null, "123"));
        assertDoesNotThrow(() -> territory2.addArmy(+1));
        assertEquals(1, territory2.getArmy());

        assertThrowsExactly(IllegalArgumentException.class, () -> territory2.addArmy(-2));

        territory2.addArmy(-1);
        assertEquals(null, territory2.getOwner());

    }

    // test isNeighbour
    @Test
    void testIsNeighbour() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        assertEquals(true, territory1.isNeighbour(territory2));
        assertEquals(true, territory1.isNeighbour(territory3));
        assertEquals(false, territory1.isNeighbour(territory1));
        assertEquals(false, territory1.isNeighbour(new Territory("Territory4")));

        // by id

        assertEquals(true, territory1.isNeighbour("Territory2"));
        assertEquals(true, territory1.isNeighbour("Territory3"));
        assertEquals(false, territory1.isNeighbour("Territory4"));
        assertEquals(false, territory1.isNeighbour("Territory1"));

    }

    // test addNeighbour
    @Test
    void testAddNeighbour() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbour(null));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbour(territory1));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory1.addNeighbour(territory2));
    }

    // test removeNeighbour
    @Test
    void testRemoveNeighbour() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        territory1.removeNeighbour(territory2);
        assertEquals(false, territory1.isNeighbour(territory2));
        assertEquals(true, territory1.isNeighbour(territory3));

    }

    // test getNeighbours
    @Test
    void testGetNeighbours() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        assertEquals(2, territory1.getNeighbours().size());
        assertEquals(true, territory1.getNeighbours().contains(territory2));
        assertEquals(true, territory1.getNeighbours().contains(territory3));
        assertEquals(false, territory1.getNeighbours().contains(new Territory("Territory4")));
    }

    // test getNotOwnedNeighbour
    @Test
    void testGetNotOwnedNeighbour() {
        Player player1 = new Player("Player1", null, null, "123");
        Player player2 = new Player("Player2", null, null, "123");
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        territory2.setOwner(player1);
        territory3.setOwner(player2);

        assertEquals(2, territory1.getNotOwnedNeighbour().length);
        assertEquals(territory2, territory1.getNotOwnedNeighbour()[0]);
        assertEquals(territory3, territory1.getNotOwnedNeighbour()[1]);

        territory1.setOwner(player1);

        assertEquals(1, territory1.getNotOwnedNeighbour().length);
        assertEquals(territory3, territory1.getNotOwnedNeighbour()[0]);

        assertEquals(0, territory2.getNotOwnedNeighbour().length);
        assertEquals(0, territory3.getNotOwnedNeighbour().length);

    }

    // test getOwnedNeighbour
    @Test
    void testGetOwnedNeighbour() {
        Player player1 = new Player("Player1", null, null, "123");
        Player player2 = new Player("Player2", null, null, "123");
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");

        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);

        territory2.setOwner(player1);
        territory3.setOwner(player2);

        assertEquals(0, territory1.getOwnedNeighbour().length);

        territory1.setOwner(player1);

        assertEquals(1, territory1.getOwnedNeighbour().length);
        assertEquals(territory2, territory1.getOwnedNeighbour()[0]);

        territory2.setOwner(player2);

        assertEquals(0, territory1.getOwnedNeighbour().length);

        assertEquals(0, territory2.getOwnedNeighbour().length);

        assertEquals(0, territory3.getOwnedNeighbour().length);
    }

    // test equals
    @Test
    void testEquals() {
        Territory territory1 = new Territory("Territory1");
        Territory territory2 = new Territory("Territory2");
        Territory territory3 = new Territory("Territory3");
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        assertEquals(true, territory1.equals(territory1));
        assertEquals(false, territory1.equals(territory2));
        assertEquals(false, territory1.equals(null));
        assertEquals(false, territory1.equals(new Territory("Territory1")));

        Territory territory4 = new Territory("Territory1");
        territory4.addNeighbour(territory2);
        territory4.addNeighbour(territory3);
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
        territory1.addNeighbour(territory2);
        territory1.addNeighbour(territory3);
        assertEquals(territory1.hashCode(), territory1.hashCode());
        assertNotEquals(territory1.hashCode(), new Territory("Territory1").hashCode());
        assertNotEquals(territory1.hashCode(), territory2.hashCode());
        assertNotEquals(territory1.hashCode(), territory3.hashCode());

        Territory territory4 = new Territory("Territory1");
        territory4.addNeighbour(territory2);
        territory4.addNeighbour(territory3);

        assertEquals(territory1.hashCode(), territory4.hashCode());

        Object object = new Object();
        assertNotEquals(territory1.hashCode(), object.hashCode());

        assertNotEquals(territory1.hashCode(), null);

    }

}
