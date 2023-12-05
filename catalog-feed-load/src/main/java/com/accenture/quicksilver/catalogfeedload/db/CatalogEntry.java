package com.accenture.quicksilver.catalogfeedload.db;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CatalogEntry {
    public CatalogEntry() {
    }

    public CatalogEntry(String string, double d) {
        this.SKU = string;
        this.price = d;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String SKU;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof CatalogEntry))
            return false;
        CatalogEntry entry = (CatalogEntry) o;
        return Objects.equals(this.id, entry.id) && Objects.equals(this.SKU, entry.SKU)
                && Objects.equals(this.price, entry.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.SKU, this.price);
    }

    @Override
    public String toString() {
        return "CatalogEntry{" + "id=" + this.id + ", SKU='" + this.SKU + '\'' + ", price='" + this.price + '\'' + '}';
    }

}
