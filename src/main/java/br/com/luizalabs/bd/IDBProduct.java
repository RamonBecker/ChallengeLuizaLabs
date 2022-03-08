package br.com.luizalabs.bd;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;

import java.util.List;


public interface IDBProduct {

    void insert(User user, Order order, Product product);

    Double total(Order order, List<Product> products);
}
