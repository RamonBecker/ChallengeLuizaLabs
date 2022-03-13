package br.com.luizalabs.entities;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class User {
    private Integer id;
    private String name;
    private Map<Integer, Order> orders;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new TreeMap<>();
    }
}
