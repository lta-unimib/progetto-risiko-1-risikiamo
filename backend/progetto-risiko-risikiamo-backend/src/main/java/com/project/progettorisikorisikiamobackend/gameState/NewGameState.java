package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewGameState implements IState {

    // * Noto pozzo aspetta che la partita cominci
    private IContext context;

}
