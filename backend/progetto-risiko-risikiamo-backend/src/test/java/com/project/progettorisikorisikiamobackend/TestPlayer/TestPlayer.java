package com.project.progettorisikorisikiamobackend.TestPlayer;

import com.project.progettorisikorisikiamobackend.player.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.progettorisikorisikiamobackend.map.*;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    // Test Player metodo attacca un altro player
    @Test
    public void testAttack() {
        Player player = new Player("Player1", "Red", null, "1");
        Player player2 = new Player("Player1", "Red", null, "2");
        Territory owner = new Territory("Territory1", player);
        Territory neighbour = new Territory("Territory2", player2);
        owner.addArmy(5);
        neighbour.addArmy(3);
        assertThrows(IllegalArgumentException.class, () -> player.attack(owner, neighbour, 3));
        owner.addNeighbour(neighbour);
        player.attack(owner, neighbour, 3);

    }

    @Test
    public void testAttackSameOwner() {
        Player player = new Player("Player1", "Red", null, "1");
        Territory owner = new Territory("Territory1", player);
        assertThrows(IllegalArgumentException.class, () -> player.attack(owner, owner, 3));
    }

    @Test
    public void testMove() {
        Player player = new Player("Player1", "Red", null, "1");
        Territory owner = new Territory("Territory1", player);
        Territory neighbour = new Territory("Territory2", player);
        owner.addArmy(5);
        assertThrows(IllegalArgumentException.class, () -> player.move(owner, neighbour, 3));
        owner.addNeighbour(neighbour);
        player.move(owner, neighbour, 3);
        assertEquals(2, owner.getArmy());
        assertEquals(3, neighbour.getArmy());
    }

    @Test
    public void testMoveNonNeighbour() {
        Player player = new Player("Player1", "Red", null, "1");
        Territory owner = new Territory("Territory1", player);
        Territory neighbour = new Territory("Territory2", player);
        assertThrows(IllegalArgumentException.class, () -> player.move(owner, neighbour, 3));
    }

    @Test
    public void testReinforce() {
        Player player = new Player("Player1", "Red", null, "1");
        Territory owner = new Territory("Territory1", player);
        owner.addArmy(6);
        assertThrows(IllegalArgumentException.class, () -> player.placeReinforcements(owner, 3));

        player.setReinforce(6);
        player.placeReinforcements(owner, 2);

        assertEquals(8, owner.getArmy());
    }

    @Test
    public void testReinforceNotOwnedTerritory() {
        Player player = new Player("Player1", "Red", null, "1");
        Player otherPlayer = new Player("Player2", "Blue", null, "2");
        Territory owner = new Territory("Territory1", otherPlayer);
        assertThrows(IllegalArgumentException.class, () -> player.placeReinforcements(owner, 3));
    }

    @Test
    public void testSetReinforce() {
        Player player = new Player("Player1", "Red", null, "1");
        player.setReinforce(5);
        assertEquals(5, player.getReinforce());
    }

    @Test
    public void testSetReinforceBelowZero() {
        Player player = new Player("Player1", "Red", null, "1");
        assertThrows(IllegalArgumentException.class, () -> player.setReinforce(-1));
    }

}
