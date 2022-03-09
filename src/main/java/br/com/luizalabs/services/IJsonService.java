package br.com.luizalabs.services;

import br.com.luizalabs.entities.User;
import org.json.JSONArray;

import java.util.Map;

public interface IJsonService {
    JSONArray createJson(Map<Integer, User> users);
}
