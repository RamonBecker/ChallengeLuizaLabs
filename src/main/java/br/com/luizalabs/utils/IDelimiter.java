package br.com.luizalabs.utils;

public interface IDelimiter {

    void lineReading(String line);

    String find(String line, int positionInitial, int positionFinal);

    void findByDigits(String line);

    void findByZero(String line);

    void findBySpace(String line);

    void findByPositionNotZero(String line);

    void findReverse(String line);


}
