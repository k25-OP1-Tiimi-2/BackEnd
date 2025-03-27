package com.backend.projekti.tiimityo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    private Long id;

    private String title;
    private double price;
    private String isbn;
    private String type;
    private String color;
    private String size;

    @ManyToOne
    @JoinColumn(name = "manuid")
    private Manufacturer manufacturer;


    public Product () {}


    public Product(Long id, String title, double price, String isbn, String type, String color, String size,
            Manufacturer manufacturer) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.type = type;
        this.color = color;
        this.size = size;
        this.manufacturer = manufacturer;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public Manufacturer getManufacturer() {
        return manufacturer;
    }


    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", price=" + price + ", isbn=" + isbn + ", type=" + type
                + ", color=" + color + ", size=" + size + ", manufacturer=" + manufacturer + "]";
    }

 
    
}
