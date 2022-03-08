package br.com.luizalabs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Map<Integer, Order> orders;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Map<Integer, Order> getOrders() {
        if (orders == null) {
            orders = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer orderId1, Integer orderId2) {
                    return orderId1 - orderId2;
                }
            });
        }
        return orders;
    }
}
