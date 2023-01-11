package com.project.progettorisikorisikiamobackend.obiettivi;

import com.project.progettorisikorisikiamobackend.player.Player;

public interface Objective {
    public String getObjDescription();
    public boolean isCompleted(Player player);

    
}
