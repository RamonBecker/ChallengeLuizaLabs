package br.com.luizalabs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private Map<String, Order> orders;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Map<String, Order> getOrders() {
        if (orders == null) {
            orders = new TreeMap<>();
        }
        return orders;
    }

}
