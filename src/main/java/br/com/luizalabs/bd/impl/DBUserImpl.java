package br.com.luizalabs.bd.impl;

import br.com.luizalabs.bd.IDBOrder;
import br.com.luizalabs.bd.IDBProduct;
import br.com.luizalabs.bd.IDBUser;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;

import java.util.*;

public class DBUserImpl implements IDBUser {
    private static DBUserImpl dbUserImpl;
    private Map<String, User> users;
    private IDBOrder dbOrder;
    private IDBProduct dbProduct;

    private DBUserImpl() {
        this.dbOrder = DBOrderImpl.getInstance();
        this.dbProduct = DBProductImpl.getInstance();
    }

    public static synchronized DBUserImpl getInstance() {
        if (dbUserImpl == null) {
            dbUserImpl = new DBUserImpl();
        }
        return dbUserImpl;
    }

    public Map<String, User> getUsers() {
        if (users == null) {
            users = new TreeMap<>();
        }
        return users;
    }

    public void print() {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().getName());
            for (Map.Entry<String, Order> order : entry.getValue().getOrders().entrySet()) {
                System.out.println("Order");
                System.out.println(order.getKey());
            }
        }
    }

    @Override
    public boolean isOnList(User user) {
        return getUsers().containsKey(user.getId());
    }

    @Override
    public void insert(User user, Order order, Product product) {
        if (!isOnList(user)) {
            dbOrder.insert(user, order);
            getUsers().put(user.getId(), user);
        }

        if (isOnList(user)) {
            if (dbOrder.isOnList(user, order)) {
                dbProduct.insert(user, order, product);
            }
        }
    }

    @Override
    public User get(User user) {
        return null;
    }
}
