package br.com.luizalabs.bd.impl;

import br.com.luizalabs.bd.IDBOrder;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.User;

public class DBOrderImpl implements IDBOrder {

    private static DBOrderImpl dbOrder;

    private DBOrderImpl() {
    }

    public static synchronized DBOrderImpl getInstance() {
        if (dbOrder == null) {
            dbOrder = new DBOrderImpl();
        }
        return dbOrder;
    }

    @Override
    public void insert(User user, Order order) {
        if (!isOnList(user, order)) {
            user.getOrders().put(order.getId(), order);
        }
    }

    @Override
    public boolean isOnList(User user, Order order) {
        return user.getOrders().containsKey(order.getId());
    }
}
