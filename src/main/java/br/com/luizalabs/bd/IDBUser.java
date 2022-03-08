package br.com.luizalabs.bd;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;

public interface IDBUser {

    boolean isOnList(User user);

    void insert(User user, Order order, Product product);

    User get(User user);

    void print();
}
