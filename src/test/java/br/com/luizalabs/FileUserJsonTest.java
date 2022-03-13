package br.com.luizalabs;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileUserJsonTest {

    private FileUserJson fileUserJson;
    private String line;
    private String date;
    private LocalDate dateFormat;
    private User user;
    private Order order;
    private Product product;
    private String file_output;
    private static final int INDEX = 0;

    @BeforeEach
    void setUp() {
        fileUserJson = new FileUserJson();
        line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        date = "20210308";
        dateFormat = LocalDate.parse("2021-03-08");
        file_output = "datasource\\output.json";
        user = new User(70, "Palmer Prossacco");
        order = new Order(753, dateFormat);
        product = new Product(3, 1836.74);
        order.setValueTotal(product);
        user.getOrders().put(order.getId(), order);
        fileUserJson.getUsers().put(user.getId(), user);
    }

    @Test
    void read() {
        assertNotNull(line);
        assertFalse(line.isEmpty());
        assertEquals(95, line.length());
        fileUserJson.read(line);
    }

    @Test
    void formatDate() {
        assertNotNull(date);
        assertFalse(date.isEmpty());
        assertEquals(8, date.length());
        LocalDate dateFormatResult = fileUserJson.formatDate(date);
        assertNotNull(dateFormatResult);
        assertEquals(dateFormat.getDayOfMonth(), dateFormatResult.getDayOfMonth());
        assertEquals(dateFormat.getMonthValue(), dateFormatResult.getMonthValue());
        assertEquals(dateFormat.getYear(), dateFormatResult.getYear());
    }


    @Test
    void isOrderList() {
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getOrders());
        assertNotNull(order);
        assertNotNull(order.getId());

        Order order_result = fileUserJson.isOrderList(user, order);

        assertEquals(order.getId(), order_result.getId());
        assertEquals(order.getDate(), order_result.getDate());

    }

    @Test
    void isUserList() {
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(fileUserJson.getUsers());
        User user_result = fileUserJson.isUserList(user);
        assertEquals(user.getId(), user_result.getId());
        assertEquals(user.getName(), user_result.getName());
    }

    @Test
    void createItemProductJson() {
        assertNotNull(product);

        JSONObject jsonItemProduct = fileUserJson.createItemProductJson(product);
        assertNotNull(jsonItemProduct);
        assertTrue(jsonItemProduct.has("product_id"));
        assertTrue(jsonItemProduct.has("value"));

        Object prod_id = jsonItemProduct.get("product_id");
        Object value = jsonItemProduct.get("value");

        assertNotNull(prod_id);
        assertNotNull(value);

        assertInstanceOf(Integer.class, prod_id);
        Integer prod_id_convert_int = (Integer) prod_id;
        assertInstanceOf(Double.class, value);
        Double value_convert_double = (Double) value;

        assertEquals(product.getId(), prod_id_convert_int);
        assertEquals(product.getValue(), value_convert_double);
    }

    @Test
    void createItemOrderJson() {
        JSONArray jsonProducts = new JSONArray();
        assertNotNull(product);
        JSONObject jsonItemProduct = fileUserJson.createItemProductJson(product);
        assertNotNull(jsonItemProduct);
        jsonProducts.put(jsonItemProduct);

        assertNotNull(jsonProducts);
        assertNotNull(order);

        JSONObject jsonItemOrder = fileUserJson.createItemOrderJson(order, jsonProducts);
        assertNotNull(jsonItemOrder);
        assertTrue(jsonItemOrder.has("order_id"));
        assertTrue(jsonItemOrder.has("total"));
        assertTrue(jsonItemOrder.has("date"));

        Object order_id = jsonItemOrder.get("order_id");
        Object total = jsonItemOrder.get("total");
        Object date = jsonItemOrder.get("date");

        assertNotNull(order_id);
        assertNotNull(total);
        assertNotNull(date);

        assertInstanceOf(Integer.class, order_id);
        Integer order_id_covert_int = (Integer) order_id;
        assertInstanceOf(Double.class, total);
        Double total_convert_double = (Double) total;
        assertInstanceOf(LocalDate.class, date);
        LocalDate date_convert_local_date = (LocalDate) date;

        assertEquals(order.getId(), order_id_covert_int);
        assertEquals(order.getValueTotal(), total_convert_double);
        assertEquals(order.getDate(), date_convert_local_date);
    }

    @Test
    void createItemUserJson() {
        JSONArray jsonProducts = new JSONArray();
        assertNotNull(product);
        JSONObject jsonItemProduct = fileUserJson.createItemProductJson(product);
        assertNotNull(jsonItemProduct);
        jsonProducts.put(jsonItemProduct);

        assertNotNull(jsonProducts);
        assertNotNull(order);
        JSONObject jsonItemOrder = fileUserJson.createItemOrderJson(order, jsonProducts);
        assertNotNull(jsonItemOrder);

        JSONArray jsonOrders = new JSONArray();
        jsonOrders.put(jsonItemOrder);
        assertNotNull(user);
        assertNotNull(jsonOrders);

        JSONObject jsonUser = fileUserJson.createItemUserJson(user, jsonOrders);

        assertNotNull(jsonUser);
        assertTrue(jsonUser.has("user_id"));
        assertTrue(jsonUser.has("name"));

        Object user_id = jsonUser.get("user_id");
        Object name_user = jsonUser.get("name");


        assertNotNull(user_id);
        assertNotNull(name_user);

        assertInstanceOf(Integer.class, user_id);
        Integer user_id_convert_int = (Integer) user_id;
        assertInstanceOf(String.class, name_user);
        String name_user_convert_string = (String) name_user;

        assertEquals(user.getId(), user_id_convert_int);
        assertEquals(user.getName(), name_user_convert_string);
    }

    @Test
    void create() {
        Map<Integer, User> users = fileUserJson.getUsers();
        assertNotNull(users);

        List<User> users_list = fileUserJson.getUsers().values().stream().toList();
        assertNotNull(users_list);
        assertFalse(users_list.isEmpty());

        User user_list = users_list.get(INDEX);
        assertNotNull(user_list);

        List<Order> order_list = user_list.getOrders().values().stream().toList();
        assertNotNull(order_list);

        List<Product> products_list = order_list.get(INDEX).getProducts();
        assertNotNull(products_list);

        JSONArray result_json_create = fileUserJson.create();

        assertNotNull(result_json_create);
        assertFalse(result_json_create.isEmpty());
        assertNotEquals(0, result_json_create.length());
    }

    @Test
    void export() {
        JSONArray result_json_create = fileUserJson.create();
        assertNotNull(result_json_create);
        assertFalse(result_json_create.isEmpty());
        assertNotEquals(0, result_json_create.length());

        assertNotNull(file_output);
        assertFalse(file_output.isEmpty());
        assertNotEquals(0, file_output.length());
        boolean result_json_export = fileUserJson.export(result_json_create, file_output);
        assertTrue(result_json_export);
    }

    @Test
    void exportException() {
        JSONArray result_json_create = fileUserJson.create();
        assertNotNull(result_json_create);
        assertFalse(result_json_create.isEmpty());
        assertNotEquals(0, result_json_create.length());
        try {
            boolean result_json_export = fileUserJson.export(result_json_create, "");
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
    }


}