package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.User;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDelimiter;

public class FactoryUser {


    public static User  user(Integer id, String name){
        return new User();
    }
}
