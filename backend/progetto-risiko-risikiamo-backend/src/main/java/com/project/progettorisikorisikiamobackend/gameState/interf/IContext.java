package com.project.progettorisikorisikiamobackend.gameState.interf;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;

public interface IContext {

    public Turn getTurn();

    public Map getMap();

    public void setState(IState state);

}
