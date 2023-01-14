package com.project.progettorisikorisikiamobackend.cards;

public enum EnumCard {
    
    FANTE("fante"),
    CAVALLO("cavallo"),
    CANNONE("cannone"),
    JOLLY("jolly"),
    ;
    private String type;
    private EnumCard(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
