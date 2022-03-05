package br.com.luizalabs.utils.impl;


import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.User;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.TypeDelimiter;
import br.com.luizalabs.utils.factory.FactoryUser;
import lombok.Getter;

@Getter
public class UtilDelimiter implements IDelimiter {

    private int j;
    private int k;
    private String line;

    private String[] user(String line) {
        String idUser = find(line, 0, j);
        String userName = find(line, k, line.length());
        this.line = userName;
        findZero(userName);
        userName = find(userName, 0, j);
        return new String[]{idUser, userName};
    }

    private String order(String line){
        String orderId = find(line, 0, j - 1);
        findReverse(orderId);
        orderId = find(orderId, 0, j + 1);
        return orderId;
    }

    private String[] prod(String line, String lineOrderId){
        String prodID = find(line, lineOrderId.length(), line.length());
        line = prodID;
        findSpace(prodID);
        prodID = find(prodID, 0, j);
        String value = find(line, k, line.length());
        line = value;
        findDigits(value);
        value = find(value, 0, j + 1);
        return new String[]{prodID, value};
    }


    @Override
    public void lineReading(String line) {
        findSpace(line);
        /*
        String idUser = find(line, 0, j);

        String userName = find(line, k, line.length());
        line = userName;
        findZero(userName);

        userName = find(userName, 0, j);


         */

        String[] attributesUser = user(line);
        User user = new User(Integer.parseInt(attributesUser[0]), attributesUser[1]);
        line = this.line;
        line = find(line, attributesUser[1].length(), line.length());

        findSpace(line);
        String orderId = find(line, 0, j - 1);

        findReverse(orderId);

        orderId = find(orderId, 0, j + 1);

        String prodI = find(line, orderId.length(), line.length());
        line = prodI;
        findSpace(prodI);

        prodI = find(prodI, 0, j);


        String value = find(line, k, line.length());
        line = value;
        findDigits(value);
        value = find(value, 0, j + 1);

        String date = find(line, value.length(), line.length());

        System.out.println("-----------");
        System.out.println("UserId: " + user.getId());
        System.out.println("Username: " + user.getName());
        System.out.println("OrderId: " + orderId);
        System.out.println("ProdId: " + prodI);
        System.out.println("Value: " + value);
        System.out.println("Date: " + date);
        System.out.println("-----------");

    }

    @Override
    public String find(String line, int positionInitial, int positionFinal) {
        return line.substring(positionInitial, positionFinal);
    }

    @Override
    public void findSpace(String line) {

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
    public void findDigits(String line) {
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

    private void findPositionNotZero(String line) {
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character != '0') {
                j = i;
                break;
            }
        }
    }

    @Override
    public void findZero(String line) {
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '0') {
                j = i;
                break;
            }
        }
    }
}
