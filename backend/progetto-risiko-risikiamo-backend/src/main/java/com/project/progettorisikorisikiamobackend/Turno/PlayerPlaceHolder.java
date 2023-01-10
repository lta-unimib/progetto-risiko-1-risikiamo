package com.project.progettorisikorisikiamobackend.Turno;

public class PlayerPlaceHolder implements iPlayerPlaceHolder {
    private String playerName;
    private String color;
    private int playerId;
    private boolean isIngame;
    private int Obiettivo;
    private int statoObiettivo;
//costruttore
    public PlayerPlaceHolder(String playerName, String color,  boolean isIngame, int Obiettivo, int statoObiettivo, int playerId) {
        setColor(color);
        setPlayerName(playerName);
       setPlayerId(playerId);
        setIsIngame(isIngame);
        setObiettivo(Obiettivo);
        setStatoObiettivo(statoObiettivo);
        

    }
    //costruttore vuoto
    public PlayerPlaceHolder() {
        setColor("white");
        setPlayerName("player");
        setPlayerId(0);
        setIsIngame(false);
        setObiettivo(0);
        setStatoObiettivo(0);
    }

//getters e setters
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public int getPlayerId() {
        return this.playerId;
    }
    public void setIsIngame(boolean isIngame) {
        this.isIngame = isIngame;
    }
    public boolean getIsIngame() {
        return this.isIngame;
    }
    public void setObiettivo(int Obiettivo) {
        this.Obiettivo = Obiettivo;
    }
    public int getObiettivo() {
        return this.Obiettivo;
    }
    public void setStatoObiettivo(int statoObiettivo) {
        this.statoObiettivo = statoObiettivo;
    }
    public int getStatoObiettivo() {
        return this.statoObiettivo;
    }

    public void attack(){}
    public void moveArmies(){}
    public void placeReinforcements(int armies){}
}
