package com.project.progettorisikorisikiamobackend.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;

public class MapTests {

    // Test: testMap
    @Test
    public void testMap() {

        PlayerPlaceholder player1 = new PlayerPlaceholder("Player1");
        Map map = new Map("Map1");
        assertEquals("Map1", map.getMapId());
        assertEquals(0, map.getContinents(player1).size());

        Continet continet1 = new Continet("Continet1", 1);
        Continet continet2 = new Continet("Continet2", 2);

        map.addContinet(continet1, "id1");
        map.addContinet(continet2, "id2");

        assertEquals(2, map.getContinents().size());
        Collection<Continet> continets = map.getContinents();
        assertEquals(continet1, continets.toArray()[1]);
        assertEquals(continet2, continets.toArray()[0]);

        assertThrows(IllegalArgumentException.class, () -> map.addContinet(continet1, "id1"));
        assertThrows(IllegalArgumentException.class, () -> map.addContinet(continet2, "id3"));

        Continet continet3 = new Continet("Continet3", 3);

        assertThrows(IllegalArgumentException.class, () -> map.addContinet(continet3, "id1"));

        map.removeContinet("id1");

        assertEquals(1, map.getContinents().size());

    }

    // test : testEqualMap
    @Test
    public void testEqualMap() {
        Map map1 = new Map("Map1");
        Map map2 = new Map("Map1");
        Map map3 = new Map("Map2");

        assertEquals(map1, map2);
        assertEquals(map1.hashCode(), map2.hashCode());

        assertNotEquals(map1, map3);
    }
}
