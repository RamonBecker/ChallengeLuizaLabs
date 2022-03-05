package br.com.luizalabs.bd;

import br.com.luizalabs.entities.User;

import java.util.Map;
import java.util.TreeMap;

public class DBUser {
    private static DBUser dbUser;
    private Map<Integer, User> users;

    private DBUser(){
    }

    public static DBUser getInstance(){
        if (dbUser == null){
            dbUser = new DBUser();
        }
        return dbUser;
    }

    public Map<Integer, User> getUsers(){
        if(users == null){
            users = new TreeMap<>();
        }
        return users;
    }
}
