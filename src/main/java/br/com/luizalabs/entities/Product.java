package br.com.luizalabs.entities;

import lombok.Getter;

import java.util.Comparator;

@Getter
public class Product implements Comparator<Product> {
    private Integer id;
    private Double value;

    public Product(Integer id, Double value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getId() - p2.getId();
    }
}
