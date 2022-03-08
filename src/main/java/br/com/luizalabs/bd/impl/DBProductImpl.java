package br.com.luizalabs.bd.impl;

import br.com.luizalabs.bd.IDBProduct;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        List<Product> products = user.getOrders().get(order.getId()).getProducts();
        products.add(product);
        user.getOrders().get(order.getId()).setProducts(products);
        order.setTotal(total(order, products));
    }

    @Override
    public Double total(Order order, List<Product> products) {
        Double valueTotal = order.getTotal();
        for (Product product : products) {
            valueTotal += product.getValue();
        }
        return valueTotal;
    }
}
