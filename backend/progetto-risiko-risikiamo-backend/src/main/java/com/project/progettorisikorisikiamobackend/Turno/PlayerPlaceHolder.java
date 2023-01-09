package com.project.progettorisikorisikiamobackend.Turno;
import lombok.Getter;
import lombok.Setter;

public class PlayerPlaceHolder implements iPlayerPlaceHolder {
   @Getter @Setter private String playerName;
    @Getter private String color;
    @Getter @Setter private int playerId;
    @Getter  private int armies;
    @Getter private int Obiettivo;
    
    @Getter private int statoObiettivo;
//costruttore
    public PlayerPlaceHolder(String playerName, String color, int Obiettivo, int statoObiettivo, int playerId) {
        this.playerName = playerName;
        this.color = color;
        this.Obiettivo = Obiettivo;
        this.statoObiettivo = statoObiettivo;
        this.armies = armies;
        this.playerId = playerId;

        

    }
    //costruttore vuoto
    public PlayerPlaceHolder() {
        this.playerName = "";
        this.color = "";
        this.Obiettivo = 0;
        this.statoObiettivo = 0;
        this.armies = 25;
        this.playerId = 0;
    
    }
public boolean defeated(){
    if(this.armies == 1){
        return true;
    }
    else{
        return false;
    }

    
}
}
