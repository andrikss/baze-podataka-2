package com.bp2.dto;

public class BlokchainMrezaDTO {
    private int id;
    private String ime;
    private String tip;
    private Integer parentId;

    public BlokchainMrezaDTO(int id, String ime, String tip, Integer parentId) {
        this.id = id;
        this.ime = ime;
        this.tip = tip;
        this.parentId = parentId;
    }

    public int getId() { return id; }
    public String getIme() { return ime; }
    public String getTip() { return tip; }
    public Integer getParentId() { return parentId; }
}
