package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.Product;
import br.com.luizalabs.exceptions.InstanceException;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDelimiter;
import lombok.NoArgsConstructor;

import static br.com.luizalabs.utils.UtilString.MESSAGE_CREATE_INSTANCE_EXCEPTION;

@NoArgsConstructor

public class FactoryProduct {
    private IDelimiter delimiter;

    public FactoryProduct(IDelimiter delimiter) {
        this.delimiter = delimiter;
    }

    public Product prod(String line, String lineOrderId) throws InstanceException {
        if (delimiter == null) {
            throw new InstanceException(MESSAGE_CREATE_INSTANCE_EXCEPTION + Product.class);
        }

        String prodID = "";
        String value = "";

        if (delimiter instanceof UtilDelimiter) {
            prodID = delimiter.find(line, lineOrderId.length(), line.length());
            line = prodID;

            delimiter.findBySpace(prodID);
            prodID = delimiter.find(prodID, 0, ((UtilDelimiter) delimiter).getJ());
            delimiter.findReverse(prodID);
            prodID = delimiter.find(prodID, ((UtilDelimiter) delimiter).getJ(), prodID.length());

            value = delimiter.find(line, ((UtilDelimiter) delimiter).getK(), line.length());
            ((UtilDelimiter) delimiter).setLine(value);
            delimiter.findByDigits(value);
            value = delimiter.find(value, 0, ((UtilDelimiter) delimiter).getJ() + 1);

            try {
                Integer.parseInt(prodID);
            } catch (Exception e) {
                prodID = "0";
            }
        }

        return new Product(Integer.parseInt(prodID), Double.parseDouble(value));
    }

}
