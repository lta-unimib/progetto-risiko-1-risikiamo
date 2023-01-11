package com.project.progettorisikorisikiamobackend.obiettivi;

import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;

public interface Objective {
    public String getObjDescription();
    public boolean isCompleted(PlayerPlaceholder player);

    
}
