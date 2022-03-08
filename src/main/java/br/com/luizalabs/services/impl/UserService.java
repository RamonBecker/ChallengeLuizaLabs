package br.com.luizalabs.services.impl;

import br.com.luizalabs.bd.impl.DBUserImpl;
import br.com.luizalabs.bd.IDBUser;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import br.com.luizalabs.services.IUserService;

public class UserService implements IUserService {

    private IDBUser dbUser;

    public UserService() {
        this.dbUser = DBUserImpl.getInstance();
    }

    public IDBUser getDBUser() {
        if (dbUser == null) {
            dbUser = DBUserImpl.getInstance();
        }
        return dbUser;
    }

    @Override
    public void getResults(User user, Order order, Product product) {
/*
        System.out.println("UserId: " + user.getId());
        System.out.println("Username: " + user.getName());
        System.out.println("OrderId: " + order.getId());
        System.out.println("ProdId: " + product.getId());
        System.out.println("Value: " + product.getValue());
        System.out.println("Date: " + order.getDate());


 */
        getDBUser().insert(user, order, product);
    }
}
