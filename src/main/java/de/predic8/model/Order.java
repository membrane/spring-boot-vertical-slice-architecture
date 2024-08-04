package de.predic8.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = UUID)
    UUID id;

    @OneToMany(cascade = ALL)
    List<Item> items;

    public Order() {}

    public Order(List<Item> items) {
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

