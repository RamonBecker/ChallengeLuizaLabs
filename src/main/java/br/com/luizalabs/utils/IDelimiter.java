package br.com.luizalabs.utils;

public interface IDelimiter {

    void lineReading(String line);

    String find(String line, int positionInitial, int positionFinal);

    void findDigits(String line);

    void findZero(String line);

    void findReverse(String line);

    void findSpace(String line);
}
