package com.project.progettorisikorisikiamobackend.Turno;

public class Territorio {
    private int id;
    private int armies;
    private String nome;
    //costruttore
    public Territorio(int id, int armies, String nome) {
        setId(id);
        setArmies(armies);
        setNome(nome);
    }
    //costruttore vuoto
    public Territorio() {
        setId(0);
        setArmies(0);
        setNome("territorio");
    }
    //get e set
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setArmies(int armies) {
        this.armies = armies;
    }
    public int getArmies() {
        return this.armies;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomeTerritorio() {
        return this.nome;
    }

    
    
}
