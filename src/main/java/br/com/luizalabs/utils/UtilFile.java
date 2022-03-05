package br.com.luizalabs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static br.com.luizalabs.utils.UtilString.PATH;
import static br.com.luizalabs.utils.UtilString.FILE_DATA_1;

public class UtilFile {

    static int k;
    static int j;

    public static boolean isFile(String path) {
        return Paths.get(path).toFile().isFile();
    }

    public static void read() {
        Path path = Paths.get("datasource\\data_1.txt");

        List<String> linesFiles = null;
        try {
            linesFiles = Files.readAllLines(path);
            for (String line : linesFiles) {
                //  delimiter(linha);
                test(line);
                //       System.out.println(linha);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static void delimiter(String linha) {
        int k = -1;
        int j = -1;
        String codigo = "";

        for (int i = 0; i < linha.length(); i++) {
            char character = linha.charAt(i);

            if (character == ' ') {
                j = i;
                break;
            }
            codigo += character;
        }

        for (int i = 0; i < codigo.length(); i++) {
            char character = codigo.charAt(i);
            if (character != '0') {
                k = i;
                break;
            }
        }

        if (k > 0) {
            codigo = codigo.substring(k, codigo.length());
            linha = linha.substring(j).trim();
        }


        String nome = "";

        for (int i = 0; i < linha.length(); i++) {
            char character = linha.charAt(i);
            if (character == '0') {
                k = i;
                break;
            }
            nome += character;
        }


        for (int i = k; i < linha.length(); i++) {
            char character = linha.charAt(i);
            if (character != '0') {
                j = i;
                break;
            }
        }

        String order = "";
        linha = linha.substring(j, linha.length());

        for (int i = 0; i < linha.length(); i++) {
            char character = linha.charAt(i);
            if (character == ' ') {
                for (int l = i; l < linha.length(); l++) {
                    char aux_c = linha.charAt(l);
                    if (aux_c != ' ') {
                        j = i;
                        k = l;
                        break;
                    }
                }
            }
        }
        linha = linha.substring(0, j - 1);
        // System.out.println(linha);

    }

    public static void test(String line) {

        findSpace(line);
        String idUser = line.substring(0, j);
        System.out.println(idUser);

        String userName = line.substring(k, line.length());

        System.out.println(userName);

    }

    private static void findSpace(String linha) {

        boolean first_space = false;

        for (int i = 0; i < linha.length(); i++) {
            char character = linha.charAt(i);
            if (character == ' ') {
                for (int l = i; l < linha.length(); l++) {
                    char character_next = linha.charAt(l);
                    if (character_next != ' ') {
                        j = i;
                        k = l;
                        first_space = true;
                        break;
                    }
                }
            }
            if(first_space){
                break;
            }
        }
    }

    public static void main(String args[]) {

        boolean result = UtilFile.isFile(PATH + FILE_DATA_1);

        UtilFile.read();


    }
}
