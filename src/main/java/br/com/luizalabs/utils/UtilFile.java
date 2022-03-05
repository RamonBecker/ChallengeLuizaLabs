package br.com.luizalabs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UtilFile {

    private static int k;
    private static int j;

    public static boolean isFile(String path) {
        return Paths.get(path).toFile().isFile();
    }

    public static void read() {
        Path path = Paths.get("datasource\\data_1.txt");
        List<String> linesFiles = null;
        try {
            linesFiles = Files.readAllLines(path);
            for (String line : linesFiles) {
                test(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test(String line) {
        findSpace(line);
        String idUser = find(line, 0, j);

        String userName = find(line, k, line.length());
        line = userName;
        find(userName);

        userName = find(userName, 0, j);

        line = find(line, userName.length(), line.length());

        findSpace(line);
        String orderId = find(line, 0, j - 1);

        findOrderId(orderId);

        orderId = find(orderId, 0, j + 1);

        String prodI = find(line, orderId.length(), line.length());
        line = prodI;
        findSpace(prodI);

        prodI = find(prodI, 0, j);


        String value = find(line, k, line.length());
        line = value;
        findValue(value);
        value = find(value, 0, j + 1);

        String date = find(line, value.length(), line.length());

        System.out.println("-----------");
        System.out.println("UserId: " + idUser);
        System.out.println("Username: " + userName);
        System.out.println("OrderId: " + orderId);
        System.out.println("ProdId: " + prodI);
        System.out.println("Value: " + value);
        System.out.println("Date: " + date);
        System.out.println("-----------");

    }

    private static void find(String line) {
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '0') {
                j = i;
                break;
            }
        }
    }

    private static String find(String line, int positionInitial, int positionFinal) {
        return line.substring(positionInitial, positionFinal);
    }

    private static void findOrderId(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            char character = line.charAt(i);
            if (character != '0') {
                j = i;
                break;
            }
        }
    }

    private static void findSpace(String line) {

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

    private static void findValue(String line) {
        int cont = 0;
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
                break;
            }
        }
    }

    public static void main(String args[]) {

        boolean result = UtilFile.isFile(UtilString.PATH + UtilString.FILE_DATA_1);

        UtilFile.read();


    }
}
