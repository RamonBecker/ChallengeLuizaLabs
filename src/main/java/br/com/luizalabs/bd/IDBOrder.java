package br.com.luizalabs.bd;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.User;

public interface IDBOrder {

    void insert(User user, Order order);
    boolean isOnList(User user, Order order);
}
