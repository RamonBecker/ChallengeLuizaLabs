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
    private Map<Integer, User> users;
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

    public Map<Integer, User> getUsers() {
        if (users == null) {
            users = new TreeMap<>();
        }
        return users;
    }

    public void print() {
        System.out.println("-------------");
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().getName());
            for (Map.Entry<Integer, Order> order : entry.getValue().getOrders().entrySet()) {
                System.out.println("-------------");
                System.out.println("Order :" + order.getValue().getId());
                System.out.println("Total: " + order.getValue().getTotal());
                System.out.println("Date: " + order.getValue().getDate());

                for (Product product : order.getValue().getProducts()) {
                    System.out.println("Prod id: " + product.getId());
                    System.out.println("Value: " + product.getValue());
                }
            }
        }
        System.out.println("-------------");
    }

    @Override
    public boolean isOnList(User user) {
        return getUsers().containsKey(user.getId());
    }

    @Override
    public void insert(User user, Order order, Product product) {
        if (!isOnList(user)) {
            getUsers().put(user.getId(), user);
        }
        if (isOnList(user)) {
            if (!dbOrder.isOnList(getUsers().get(user.getId()), order)) {
                dbOrder.insert(getUsers().get(user.getId()), order);
            }
            if (dbOrder.isOnList(getUsers().get(user.getId()), getUsers().get(user.getId()).getOrders().get(order.getId()))) {
                dbProduct.insert(getUsers().get(user.getId()), getUsers().get(user.getId()).getOrders().get(order.getId()), product);
            }
        }
    }
}
