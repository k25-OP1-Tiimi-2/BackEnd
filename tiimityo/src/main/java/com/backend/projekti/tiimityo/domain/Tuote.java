package com.backend.projekti.tiimityo.domain;

public class Tuote {

    private Long id;
    private String nimi;
    private double hinta;
    private String isbn;

    public Tuote () {}

    public Tuote(Long id, String nimi, double hinta, String isbn) {
        this.id = id;
        this.nimi = nimi;
        this.hinta = hinta;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Tuote [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + ", isbn=" + isbn + "]";
    }

    
}
