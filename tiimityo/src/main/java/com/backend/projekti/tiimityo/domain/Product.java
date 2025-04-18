package com.backend.projekti.tiimityo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotNull(message = "Hinta on pakollinen")
    @Min(value = 1, message = "Hinnan tulee olla vähintään 1")
    private Double price;
    private String color;
    private String size;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manuid")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "productTypeid")
    private ProductType productType;

    public Product() {
    }

    public Product(String title, Double price, ProductType type, String color, String size,
            Manufacturer manufacturer, int quantity) {
        super();
        this.title = title;
        this.price = price;
        this.productType = type;
        this.color = color;
        this.size = size;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType producttype) {
        this.productType = producttype;
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
        return "Product [id=" + id + ", title=" + title + ", price=" + price + ", type=" + productType
                + ", color=" + color + ", size=" + size + ", manufacturer=" + manufacturer + ", quantity=" + quantity
                + "]";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
