package com.backend.projekti.tiimityo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tuote {

    @Id
    private Long id;

    private String title;
    private double price;
    private String isbn;

    public Tuote () {}

    public Tuote(Long id, String title, double price, String isbn) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Tuote [id=" + id + ", title=" + title + ", price=" + price + ", isbn=" + isbn + "]";
    }

    
}
