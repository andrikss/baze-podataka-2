package com.bp2.dto;

public class SentimentDTO {
    private int id;
    private int vrednost;
    private double skor;

    public SentimentDTO(int id, int vrednost, double skor) {
        this.id = id;
        this.vrednost = vrednost;
        this.skor = skor;
    }

    public int getId() { return id; }
    public int getVrednost() { return vrednost; }
    public double getSkor() { return skor; }
}
