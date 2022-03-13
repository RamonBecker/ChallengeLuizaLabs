package br.com.luizalabs.entities;

import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class Order {
    private Integer id;
    private LocalDate date;
    private Double valueTotal;
    private List<Product> products;

    public Order(Integer id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.valueTotal = 0.0;
        this.products = new ArrayList<>();
    }

    public void setValueTotal(Product product) {
        products.add(product);
        products.sort((p1, p2) -> p1.compare(p1, p2));
        this.valueTotal += product.getValue();
        this.valueTotal = (double) Math.round(this.valueTotal * 100) / 100;
    }
}
