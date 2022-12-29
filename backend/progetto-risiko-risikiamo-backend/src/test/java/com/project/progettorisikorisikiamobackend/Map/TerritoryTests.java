package com.project.progettorisikorisikiamobackend.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.Test;

public class TerritoryTests {

    // Test: testTerritory
    @Test
    public void testTerritory() {
        Territory territory = new Territory("Territory1", null);
        assertEquals("Territory1", territory.getName());
        assertEquals(0, territory.getArmy());
        assertEquals(null, territory.getOwner());
    }

    // Test: testTerritoryWithArmy
    @Test
    public void testTerritoryWithArmy() {
        Territory territory = new Territory("Territory1", null);
        territory.getArmy();
        assertEquals(0, territory.getArmy());
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(-1));
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.addArmy(+1));
        territory.setOwner(new PlayerPlaceholder("Player1"));
        assertDoesNotThrow(() -> territory.addArmy(+1));
        assertEquals(1, territory.getArmy());

    }

    // Test: testTerritoryWithOwner
    @Test
    public void testTerritoryWithOwner() {
        Territory territory = new Territory("Territory1", null);
        territory.setOwner(new PlayerPlaceholder("Player1"));
        assertEquals("Player1", territory.getOwner().getName());
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.setOwner(null));
        territory.setOwner(new PlayerPlaceholder("Player2"));
        assertEquals("Player2", territory.getOwner().getName());
        territory.addArmy(+1);
        assertThrowsExactly(IllegalArgumentException.class, () -> territory.setOwner(new PlayerPlaceholder("Player3")));
    }

    // Test: testTerritoryOwnedNeighborAndNotOwnedNeighborWithNullOwner
    @Test
    public void testTerritoryOwnedNeighborAndNotOwnedNeighborWithNullOwner() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

        Territory territory1 = new Territory("Territory1",
                new Territory[] { new Territory("Territory2", null),
                        new Territory("Territory3", null) });

        assertEquals(2, territory1.ownedNeighbor().length);
        assertEquals(0, territory1.notOwnedNeighbor().length);
        territory1.setOwner(player1);
        assertEquals(0, territory1.ownedNeighbor().length);
        assertEquals(2, territory1.notOwnedNeighbor().length);

    }

    // Test : testTerritoryOwnedNeighbor
    @Test
    public void testTerritoryNeighborOwned() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");

        Territory territory1 = new Territory("Territory1",
                new Territory[] { new Territory("Territory2", null),
                        new Territory("Territory3", null) });

        territory1.setOwner(player1);
        territory1.getNeighbors()[0].setOwner(player1);
        assertEquals(1, territory1.ownedNeighbor().length);
        assertEquals(1, territory1.notOwnedNeighbor().length);

    }

    // Test: testTerritoryIsNeighbor
    @Test
    public void testTerritoryIsNeighbor() {
        Territory territory1 = new Territory("Territory1",
                new Territory[] { new Territory("Territory2", null),
                        new Territory("Territory3", null) });
        Territory territory2 = new Territory("Territory2", null);
        Territory territory3 = new Territory("Territory3", null);
        Territory territory4 = new Territory("Territory4", null);

        assertEquals(true, territory1.isNeighbor(territory2));
        assertEquals(true, territory1.isNeighbor(territory3));
        assertEquals(false, territory1.isNeighbor(territory4));

        assertEquals(true, territory1.isNeighbor("Territory2"));
        assertEquals(true, territory1.isNeighbor("Territory3"));
        assertEquals(false, territory1.isNeighbor("Territory4"));
    }
}
