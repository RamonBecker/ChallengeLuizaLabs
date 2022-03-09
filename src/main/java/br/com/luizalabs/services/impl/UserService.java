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
        getDBUser().insert(user, order, product);
    }
}
