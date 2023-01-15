//package
package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.gameState.NewGameState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;

class TestNewGameState {
    // test gameStateManager

    private IState state;

    @BeforeEach
    void setUp() {
        this.state = new NewGameState(null);
    }

    @Test
    void testAttack_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> this.state.attack(null, null, 0));
    }

    @Test
    void testMove_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> this.state.move(null, null, 0));
    }

    @Test
    void testPassTurn_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> this.state.passTurn());
    }

    @Test
    void testRedeemReinforcementsCard_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> this.state.redeemReinforcementsCard());
    }

    @Test
    void testPlaceReinforcements_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> this.state.placeReinforcements(null, 0));
    }

}