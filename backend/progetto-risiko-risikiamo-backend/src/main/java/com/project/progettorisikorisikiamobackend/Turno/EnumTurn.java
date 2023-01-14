package com.project.progettorisikorisikiamobackend.Turno;

public enum EnumTurn {

    INGAME("In game"),
    WIN("Win"),
    SURRENDERED("Surrendered"),
    DEFETED("Defeated"),
    LOST("Lost");

    private String value;

    EnumTurn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
