package br.com.luizalabs;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
public class FileUserJson {

    private Map<Integer, User> users;

    public FileUserJson() {
        this.users = new TreeMap<>();
    }

    public void read(String line) {

        String id_user = line.substring(0, 11).trim();
        String name_user = line.substring(11, line.length() - 40).trim();
        String id_order = line.substring(line.length() - 37, ((line.length() - 40) + 10)).trim();
        String date_order = line.substring(line.length() - 8, line.length());
        String id_prod = line.substring(((line.length() - 40) + 10), line.length());
        id_prod = id_prod.substring(1, 12).trim();
        String value = line.substring((line.length() - 19), (line.length()) - 8).trim();

        Integer id_user_convert_int = Integer.parseInt(id_user);
        Integer id_order_convert_int = Integer.parseInt(id_order);
        Integer id_prod_convert_int = Integer.parseInt(id_prod);
        Double value_prod_convert_double = Double.parseDouble(value);
        LocalDate date_prod_format = formatDate(date_order);

        User user = new User(id_user_convert_int, name_user);
        Order order = new Order(id_order_convert_int, date_prod_format);
        Product product = new Product(id_prod_convert_int, value_prod_convert_double);

        User user_list = isUserList(user);
        Order order_list = isOrderList(user_list, order);
        order_list.setValueTotal(product);

        users.put(user_list.getId(), user_list);
    }

    public LocalDate formatDate(String date) {
        StringBuilder dateFormat = new StringBuilder(date);
        dateFormat.insert(4, "-");
        dateFormat.insert(date.length() - 1, "-");
        return LocalDate.parse(dateFormat.toString());
    }

    public Order isOrderList(User user, Order order) {
        if (user.getOrders().containsKey(order.getId())) {
            return user.getOrders().get(order.getId());
        }
        return addOrder(user, order);
    }

    public User isUserList(User user) {
        if (users.containsKey(user.getId())) {
            return users.get(user.getId());
        }
        return addUser(user);
    }

    public Order addOrder(User user, Order order) {
        user.getOrders().put(order.getId(), order);
        return user.getOrders().get(order.getId());
    }

    public User addUser(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public JSONArray create() {
        List<User> users_list = users.values().stream().toList();
        JSONArray jsonUsers = new JSONArray();
        for (User user : users_list) {
            List<Order> order_list = user.getOrders().values().stream().toList();
            JSONArray jsonOrders = new JSONArray();
            for (Order order : order_list) {
                List<Product> products_list = order.getProducts();
                JSONArray jsonProducts = new JSONArray();
                for (Product product : products_list) {
                    JSONObject jsonItemProduct = createItemProductJson(product);
                    jsonProducts.put(jsonItemProduct);
                }
                JSONObject jsonItemOrder = createItemOrderJson(order, jsonProducts);
                jsonOrders.put(jsonItemOrder);
            }
            JSONObject jsonItemUser = createItemUserJson(user, jsonOrders);
            jsonUsers.put(jsonItemUser);
        }
        return jsonUsers;
    }

    public JSONObject createItemProductJson(Product product) {
        JSONObject jsonItemObject = new JSONObject();
        jsonItemObject.put("product_id", product.getId());
        jsonItemObject.put("value", product.getValue());
        return jsonItemObject;
    }

    public JSONObject createItemOrderJson(Order order, JSONArray jsonProducts) {
        JSONObject jsonItemObject = new JSONObject();
        jsonItemObject.put("order_id", order.getId());
        jsonItemObject.put("total", order.getValueTotal());
        jsonItemObject.put("date", order.getDate());
        jsonItemObject.put("products", jsonProducts);
        return jsonItemObject;
    }

    public JSONObject createItemUserJson(User user, JSONArray jsonOrders) {
        JSONObject jsonItemObject = new JSONObject();
        jsonItemObject.put("user_id", user.getId());
        jsonItemObject.put("name", user.getName());
        jsonItemObject.put("orders", jsonOrders);
        return jsonItemObject;
    }

    public boolean export(JSONArray jsonUsers, String file_output) {
        try (FileWriter file = new FileWriter(file_output)) {
            file.write(jsonUsers.toString());
        } catch (IOException e) {
            System.out.println("Não foi possível exportar o JSON");
        }
        return true;
    }
}
