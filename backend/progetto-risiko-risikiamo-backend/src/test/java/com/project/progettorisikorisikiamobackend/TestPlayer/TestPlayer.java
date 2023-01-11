package com.project.progettorisikorisikiamobackend.TestPlayer;
import com.project.progettorisikorisikiamobackend.player.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.map.*;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    //Test Player metodo attacca un altro player
    @Test
    public void testPlayer() {
        List<Territory> t = new ArrayList<Territory>();
        for (int i=0; i<5; i++){
            Territory territory = new Territory("t1");
            t.add(territory);
        }
        Player p1= new Player("p1", null, null, 0);
        Player p2= new Player("p2", null, null, 1);
        t.get(0).setOwner(p1);
        t.get(1).setOwner(p2);
        t.get(2).setOwner(p1);
        t.get(3).setOwner(p2);
        t.get(4).setOwner(p1);
        t.get(0).addArmy(5);
        t.get(1).addArmy(4);
        t.get(2).addArmy(2);
        t.get(3).addArmy(3);
        t.get(4).addArmy(2);
        t.get(0).addNeighbor(t.get(1));
        t.get(0).addNeighbor(t.get(2));
        t.get(1).addNeighbor(t.get(0));
        t.get(1).addNeighbor(t.get(2));
        t.get(2).addNeighbor(t.get(0));
        t.get(2).addNeighbor(t.get(1));
        t.get(3).addNeighbor(t.get(4));
        t.get(4).addNeighbor(t.get(3));
        p1.attack(t.get(0), t.get(1), 2);
        assertTrue(t.get(0).getArmy()<=5 || t.get(1).getArmy()<=3);
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.attack(t.get(0), t.get(1), 6));
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.attack(t.get(0), t.get(3), 2));
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.attack(t.get(0), t.get(2), 2));
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.attack(t.get(0), t.get(1), 0));
    }
    //Test: move() metodo sposta eserciti da un territorio ad un altro
    @Test
    public void testMove() {
        List<Territory> t = new ArrayList<Territory>();
        for (int i=0; i<5; i++){
            Territory territory = new Territory("t1");
            t.add(territory);
        }
        Player p1= new Player("p1", null, null, 0);
        Player p2= new Player("p2", null, null, 1);
        t.get(0).setOwner(p1);
        t.get(1).setOwner(p2);
        t.get(2).setOwner(p1);
        t.get(3).setOwner(p2);
        t.get(4).setOwner(p1);
        t.get(0).addArmy(5);
        t.get(1).addArmy(4);
        t.get(2).addArmy(2);
        t.get(3).addArmy(3);
        t.get(4).addArmy(2);
        t.get(0).addNeighbor(t.get(1));
        t.get(0).addNeighbor(t.get(2));
        t.get(1).addNeighbor(t.get(0));
        t.get(1).addNeighbor(t.get(2));
        t.get(2).addNeighbor(t.get(0));
        t.get(2).addNeighbor(t.get(1));
        t.get(3).addNeighbor(t.get(4));
        t.get(4).addNeighbor(t.get(3));
        p1.move(t.get(0), t.get(2), 2);
        assertTrue(t.get(0).getArmy()==3 || t.get(2).getArmy()==4);
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.move(t.get(0), t.get(3), 2));
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.move(t.get(0), t.get(1), 0));
    }
    //Test: reinforce() metodo rafforza un territorio
    @Test
    public void testReinforce() {
        List<Territory> t = new ArrayList<Territory>();
        for (int i=0; i<5; i++){
            Territory territory = new Territory("t1");
            t.add(territory);
        }
        Player p1= new Player("p1", null, null, 0);
        Player p2= new Player("p2", null, null, 1);
        t.get(0).setOwner(p1);
        t.get(1).setOwner(p2);
        t.get(2).setOwner(p1);
        t.get(3).setOwner(p2);
        t.get(4).setOwner(p1);
        t.get(0).addArmy(5);
        t.get(1).addArmy(4);
        t.get(2).addArmy(2);
        t.get(3).addArmy(3);
        t.get(4).addArmy(2);
        t.get(0).addNeighbor(t.get(1));
        t.get(0).addNeighbor(t.get(2));
        t.get(1).addNeighbor(t.get(0));
        t.get(1).addNeighbor(t.get(2));
        t.get(2).addNeighbor(t.get(0));
        t.get(2).addNeighbor(t.get(1));
        t.get(3).addNeighbor(t.get(4));
        t.get(4).addNeighbor(t.get(3));
        p1.reinforce(t.get(0), 2);
        assertEquals(7, t.get(0).getArmy());
        p2.reinforce(t.get(1), 2);
        assertEquals(6, t.get(1).getArmy());
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.reinforce(t.get(1), 5));
        assertThrowsExactly(IllegalArgumentException.class, () -> p1.reinforce(t.get(0), 0));
    }
    
        
    
}
