package com.project.progettorisikorisikiamobackend.obiettivi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.SecureRandom;


public class Obiettivi {
    HashMap<Integer, Enum> objectives=null;
    public enum Lista {
        CONQUER_18_TERRITORIES,
        CONQUER_24_TERRITORIES,
        CONQUER_NORTH_AMERICA_AND_AFRICA,
        CONQUER_NORTH_AMERICA_AND_OCEANIA,
        CONQUER_ASIA_AND_AFRICA,
        CONQUER_ASIA_AND_OCEANIA,
        CONQUER_SOUTH_AMERICA_EUROPE_AND_A_THIRD_CONTINENT,
        CONQUER_EUROPE_OCEANIA_AND_A_THIRD_CONTINENT,
        DEFEAT_THE_ENEMY
        }
    public Obiettivi() {
        objectives = new HashMap<>();
        objectives.put(1, Lista.CONQUER_18_TERRITORIES);
        objectives.put(2, Lista.CONQUER_24_TERRITORIES);
        objectives.put(3, Lista.CONQUER_NORTH_AMERICA_AND_AFRICA);
        objectives.put(4, Lista.CONQUER_NORTH_AMERICA_AND_OCEANIA);
        objectives.put(5, Lista.CONQUER_ASIA_AND_AFRICA);
        objectives.put(6, Lista.CONQUER_ASIA_AND_OCEANIA);
        objectives.put(7, Lista.CONQUER_SOUTH_AMERICA_EUROPE_AND_A_THIRD_CONTINENT);
        objectives.put(8, Lista.CONQUER_EUROPE_OCEANIA_AND_A_THIRD_CONTINENT);
        objectives.put(9, Lista.DEFEAT_THE_ENEMY);
            
        
    }
    public List<Enum> listaObiettivi(int j) {
        List<Enum> l=new ArrayList<>();
        HashMap<Integer, Enum> obj = getObiettivi();
        for (int i = 1; i <= j; i++) {
            SecureRandom random = new SecureRandom();
            int n = random.nextInt(obj.size()) + 1;
            l.add(obj.get(n));
            obj.remove(n);
        }
        
        return l;
    }
    public HashMap<Integer, Enum> distribuisciObiettivi(List<Integer> p) {
        List<Enum> obj=listaObiettivi(p.size());
        HashMap<Integer, Enum> ass = new HashMap<>();
        
        for (int i = 0; i < p.size(); i++) {
            ass.put(p.get(i), obj.get(i));
        }
        return ass;
    }
    public HashMap<Integer, Enum> getObiettivi() {
        return this.objectives;
    }
}
