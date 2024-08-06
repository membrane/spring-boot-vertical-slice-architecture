package de.predic8.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.*;

import static de.predic8.model.Order.State.CREATED;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    UUID id;

    @OneToMany(cascade = ALL)
    List<Item> items;

    State state = CREATED;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        CREATED, ORDERED, CANCELED
    }

}

