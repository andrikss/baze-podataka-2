package com.bp2.dto;

public class KriptovalutaDTO {
    private int id;
    private String naziv;
    private double cena;

    public KriptovalutaDTO(int id, String naziv, double cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getId() { return id; }
    public String getNaziv() { return naziv; }
    public double getCena() { return cena; }
}
