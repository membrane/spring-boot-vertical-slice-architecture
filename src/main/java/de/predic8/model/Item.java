package de.predic8.model;

import jakarta.persistence.*;

import java.math.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    int id;

    int quantity;
    String article;
    BigDecimal price;

    public Item() {
    }

    public Item(String article, int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.article = article;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
