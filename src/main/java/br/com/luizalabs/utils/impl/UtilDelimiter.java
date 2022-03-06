package br.com.luizalabs.utils.impl;


import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.UtilDate;
import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
public class UtilDelimiter implements IDelimiter {

    private int j;
    private int k;
    private String line;

    private String[] user(String line) {
        String idUser = find(line, 0, j);
        String userName = find(line, k, line.length());
        this.line = userName;
        findByZero(userName);
        userName = find(userName, 0, j);
        return new String[]{idUser, userName};
    }

    private String order(String line) {
        String orderId = find(line, 0, j - 1);
        findReverse(orderId);
        orderId = find(orderId, 0, j + 1);
        return orderId;
    }

    private String[] prod(String line, String lineOrderId) {
        String prodID = find(line, lineOrderId.length(), line.length());
        line = prodID;

        findBySpace(prodID);
        prodID = find(prodID, 0, j);
        findReverse(prodID);
        prodID = find(prodID, j, prodID.length());


        String value = find(line, k, line.length());
        this.line = value;
        findByDigits(value);
        value = find(value, 0, j + 1);

        return new String[]{prodID, value};
    }


    @Override
    public void lineReading(String line) {
        findBySpace(line);

        String[] attributesUser = user(line);
        User user = new User(Integer.parseInt(attributesUser[0]), attributesUser[1]);

        line = this.line;
        line = find(line, user.getName().length(), line.length());

        findBySpace(line);

        Order order = new Order(order(line));

        String[] attributesProduct = prod(line, String.valueOf(order.getId()));
        Product product = new Product(attributesProduct[0], Double.parseDouble(attributesProduct[1]));

        line = this.line;

        String date = find(line, String.valueOf(product.getValue()).length(), line.length());

        findByPositionNotZero(order.getId());
        order.setId(find(order.getId(), j, order.getId().length()));
        order.setDate(UtilDate.formatDate(date));

        System.out.println("-----------");
        System.out.println("UserId: " + user.getId());
        System.out.println("Username: " + user.getName());
        System.out.println("OrderId: " + order.getId());
        System.out.println("ProdId: " + product.getId());
        System.out.println("Value: " + product.getValue());
        System.out.println("Date: " + order.getDate());
        System.out.println("-----------");

    }

    @Override
    public String find(String line, int positionInitial, int positionFinal) {
        return line.substring(positionInitial, positionFinal);
    }

    @Override
    public void findBySpace(String line) {

        boolean first_space = false;

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == ' ') {
                for (int l = i; l < line.length(); l++) {
                    char character_next = line.charAt(l);
                    if (character_next != ' ') {
                        j = i;
                        k = l;
                        first_space = true;
                        break;
                    }
                }
            }
            if (first_space) {
                break;
            }
        }
    }

    @Override
    public void findByDigits(String line) {
        int cont = 0;
        int aux_digit = 0;

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '.') {
                for (int l = i + 1; l < line.length(); l++) {
                    cont++;
                    if (cont == 2) {
                        j = l;
                        break;
                    }
                }
            }
            if (cont == 2) {
                for (int l = i + 1; l < line.length(); l++) {
                    aux_digit++;
                }
                if (aux_digit < 10) {
                    j--;
                }
                break;
            }
        }
    }

    @Override
    public void findReverse(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            char character = line.charAt(i);
            if (character != '0') {
                j = i;
                break;
            }
        }
    }

    @Override
    public void findByPositionNotZero(String line) {
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character != '0') {
                j = i;
                break;
            }
        }
    }

    @Override
    public void findByZero(String line) {
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '0') {
                j = i;
                break;
            }
        }
    }
}
