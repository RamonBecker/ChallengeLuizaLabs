package br.com.luizalabs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparator<Product> {
    private Integer id;
    private Double value;

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getId() - o2.getId();
    }
}
