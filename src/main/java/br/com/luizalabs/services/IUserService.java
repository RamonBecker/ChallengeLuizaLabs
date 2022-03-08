package br.com.luizalabs.services;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;

public interface IUserService {

    void getResults(User user, Order order, Product product);
}
