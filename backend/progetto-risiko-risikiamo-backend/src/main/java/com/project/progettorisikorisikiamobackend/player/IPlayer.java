package com.project.progettorisikorisikiamobackend.player;

import com.project.progettorisikorisikiamobackend.map.Territory;

public interface IPlayer {

    public void attack(Territory owner, Territory neighbor, int army);

    public void move(Territory owner, Territory neighbor, int army);

    public void placeReinforcements(Territory ownTerritory, int armies);

}
