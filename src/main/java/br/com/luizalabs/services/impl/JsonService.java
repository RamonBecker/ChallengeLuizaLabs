package br.com.luizalabs.services.impl;

import br.com.luizalabs.bd.IDBUser;
import br.com.luizalabs.bd.impl.DBUserImpl;
import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import br.com.luizalabs.services.IJsonService;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class JsonService implements IJsonService {

    @Override
    public JSONArray createJson(Map<Integer, User> users) {
        JSONArray json = new JSONArray();
        for (Map.Entry<Integer, User> user : users.entrySet()) {
            User aux_user = user.getValue();

            JSONObject jsonUser = new JSONObject();
            JSONArray jsonOrders = new JSONArray();

            Map<Integer, Order> orders = aux_user.getOrders();

            for (Map.Entry<Integer, Order> order : orders.entrySet()) {
                JSONObject jsonItemOrder = new JSONObject();
                Order aux_order = order.getValue();
                JSONArray jsonArrayProducys = new JSONArray();

                for (Product product : aux_order.getProducts()) {
                    JSONObject jsonItemProduct = new JSONObject();
                    jsonItemProduct.put("product_id", product.getId());
                    jsonItemProduct.put("value", product.getValue());
                    jsonArrayProducys.put(jsonItemProduct);
                }

                jsonItemOrder.put("order_id", aux_order.getId());
                jsonItemOrder.put("total", aux_order.getTotal());
                jsonItemOrder.put("date", aux_order.getDate());
                jsonItemOrder.put("products", jsonArrayProducys);
                jsonOrders.put(jsonItemOrder);
            }

            jsonUser.put("user_id", aux_user.getId());
            jsonUser.put("name", aux_user.getName());
            jsonUser.put("orders", jsonOrders);
            json.put(jsonUser);
        }
        return json;
    }
}
