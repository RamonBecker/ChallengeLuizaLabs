package br.com.luizalabs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private Order order;

    public User(String id, String name){
        this.id = id;
        this.name = name;
    }
}
