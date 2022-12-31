package com.project.progettorisikorisikiamobackend.Turno;
import java.util.*;

public class PlayerPlaceHolder implements iPlayerPlaceHolder {
    private String playerName;
    private String color;
    private int playerId;
    private boolean isIngame;
    private int Obiettivo;
    private int statoObiettivo;
    private List <Territorio> territori;
    private Dice d;
//costruttore
    public PlayerPlaceHolder(String playerName, String color,  boolean isIngame, int Obiettivo, int statoObiettivo, int playerId, List <Territorio> territori, Dice d) {
        setColor(color);
        setPlayerName(playerName);
       setPlayerId(playerId);
        setIsIngame(isIngame);
        setObiettivo(Obiettivo);
        setStatoObiettivo(statoObiettivo);
        this.territori = territori;
        setD(d);
        

    }
    //costruttore vuoto
    public PlayerPlaceHolder() {
        setColor("white");
        setPlayerName("player");
        setPlayerId(0);
        setIsIngame(false);
        setObiettivo(0);
        setStatoObiettivo(0);
        this.territori = new ArrayList<Territorio>();
        this.d = new Dice(6);
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
    public Dice getD() {
        return this.d;
    }
    public void setD(Dice d) {
        this.d = d;
    }
    /**
     * gestisce il posizionamento delle truppe iniziali
     * @param armies numero di truppe da posizionare
     */
    public void placeReinforcements(int armies) {
       
        int armiesAlreadyPlaced = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("inserisci nome territorio");
        String nomeTerritorio = input.nextLine();
        System.out.println("inserisci numero di truppe da posizionare");
        int truppeDaPosizionare = input.nextInt();
        
        if(armies > 0) {
            territori.stream().filter(territorio -> territorio.getNomeTerritorio().
            equals(nomeTerritorio)).
            forEach(territorio -> territorio.setArmies(territorio.getArmies() + truppeDaPosizionare));
        }
        armiesAlreadyPlaced += truppeDaPosizionare;
        armies -= armiesAlreadyPlaced;
        input.close();
        placeReinforcements(armies);
    }
    /**
     * gestisce l'attacco tra due territori
     * non so ancora come gestire l'altro territorio
     */
    public void attack() {
        Scanner input = new Scanner(System.in);
        System.out.println("inserisci nome territorio da cui attaccare");
        String nomeTerritorio = input.nextLine();
        Territorio partenza = territori.stream().filter(territorio -> territorio.getNomeTerritorio().equals(nomeTerritorio)).findFirst().get();
        System.out.println("inserisci nome territorio da attaccare");
        String nomeTerritorioDaAttaccare = input.nextLine();
        System.out.println("inserisci numero di truppe  con cui attaccare");
        int attaccanti = input.nextInt();
        
        if( partenza.getArmies()> 1) {
            
            for (int lanci = 0; lanci < attaccanti; lanci++) {
                d.roll();
                
                System.out.print("Hai ottenuto " + " " + d.getValue());
            }
        }
        input.close();
        
    }
    
    public void moveArmies() {
        Scanner input = new Scanner(System.in);
        System.out.println("inserisci nome territorio da cui spostare le truppe");
        String nomeTerritorio = input.nextLine();
        Territorio partenza = territori.stream().filter(territorio -> territorio.getNomeTerritorio().equals(nomeTerritorio)).findFirst().get();
        System.out.println("inserisci nome territorio in cui spostare le truppe");
        String nomeTerritorioDaAttaccare = input.nextLine();
        Territorio arrivo = territori.stream().filter(territorio -> territorio.getNomeTerritorio().equals(nomeTerritorioDaAttaccare)).findFirst().get();
        System.out.println("inserisci numero di armate da spostare");
        int daSpostare = input.nextInt();
        
        if( partenza.getArmies() > daSpostare) {
            
            partenza.setArmies(partenza.getArmies() - daSpostare) ;
            arrivo.setArmies(daSpostare + arrivo.getArmies());

        }
        else {
            System.out.println("non puoi spostare da quel territorio");
        }
        input.close();
    }
    //getters e setters per la lista di territori
    public void setTerritori(List <Territorio> territori) {
        this.territori = territori;
    }
    public List <Territorio> getTerritori() {
        return this.territori;
    }
    
}
