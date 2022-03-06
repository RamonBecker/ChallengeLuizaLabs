package br.com.luizalabs.utils.impl;


import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.entities.User;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.factory.FactoryOrder;
import br.com.luizalabs.utils.factory.FactoryProduct;
import br.com.luizalabs.utils.factory.FactoryUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilDelimiter implements IDelimiter {

    private int j;
    private int k;
    private String line;

    @Override
    public void lineReading(String line) {
        findBySpace(line);

        User user = new FactoryUser(this).user(line);
        
        line = getLine();
        line = find(line, user.getName().length(), line.length());

        findBySpace(line);

        Order order = new FactoryOrder(this).order(line);

        Product product = new FactoryProduct(this).prod(line, String.valueOf(order.getId()));
        line = getLine();

        new FactoryOrder(this).updateDateOrder(order, product, line);

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
