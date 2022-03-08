package br.com.luizalabs.bd.impl;

import br.com.luizalabs.bd.IDBProduct;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import java.util.List;

public class DBProductImpl implements IDBProduct {

    private static DBProductImpl dbProduct;

    private DBProductImpl() {
    }

    public static synchronized DBProductImpl getInstance() {
        if (dbProduct == null) {
            dbProduct = new DBProductImpl();
        }
        return dbProduct;
    }

    @Override
    public void insert(User user, Order order, Product product) {
        user.getOrders().get(order.getId()).
                setProducts(
                        add(user.getOrders().get(order.getId()).getProducts(), product)
                );
        order.setTotal(total(order,
                            user.getOrders().get(order.getId()).getProducts()
                      )
        );
    }

    @Override
    public Double total(Order order, List<Product> products) {
        Double valueTotal = 0.0;
        for (Product product : products) {
            valueTotal += product.getValue();
        }
        return (double) Math.round(valueTotal * 100) / 100;
    }

    private List<Product> add(List<Product> products, Product product) {
        products.add(product);
        products.sort((p1, p2) -> p1.compare(p1, p2));
        return products;
    }
}
