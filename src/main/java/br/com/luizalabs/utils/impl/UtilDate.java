package br.com.luizalabs.utils.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilDate {

    public static LocalDate formatDate(String date) {
        String date_format = "";
        int cont = 0;
        boolean year = false;

        for (int i = 0; i < date.length(); i++) {
            char character = date.charAt(i);
            if (!year) {
                if (cont == 4) {
                    date_format += "-";
                    cont = 0;
                    year = true;
                }
            } else {
                if (cont == 2) {
                    date_format += "-";
                    cont = 0;
                }
            }
            date_format += character;
            cont++;
        }
        return LocalDate.parse(date_format);
    }
}
