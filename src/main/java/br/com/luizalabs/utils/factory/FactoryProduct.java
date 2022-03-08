package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.Product;
import br.com.luizalabs.exceptions.InstanceException;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDelimiter;
import lombok.NoArgsConstructor;

import static br.com.luizalabs.utils.UtilString.messageCreateInstanceException;

@NoArgsConstructor

public class FactoryProduct {
    private IDelimiter delimiter;

    public FactoryProduct(IDelimiter delimiter) {
        this.delimiter = delimiter;
    }

    public Product prod(String line, String lineOrderId) throws InstanceException {
        if (delimiter instanceof UtilDelimiter) {
            String prodID = delimiter.find(line, lineOrderId.length(), line.length());
            line = prodID;

            delimiter.findBySpace(prodID);
            prodID = delimiter.find(prodID, 0, ((UtilDelimiter) delimiter).getJ());
            delimiter.findReverse(prodID);
            prodID = delimiter.find(prodID, ((UtilDelimiter) delimiter).getJ(), prodID.length());


            String value = delimiter.find(line, ((UtilDelimiter) delimiter).getK(), line.length());
            ((UtilDelimiter) delimiter).setLine(value);
            delimiter.findByDigits(value);
            value = delimiter.find(value, 0, ((UtilDelimiter) delimiter).getJ() + 1);

            try {
                Integer.parseInt(prodID);
            } catch (Exception e) {
                prodID = "0";
            }

            return new Product(prodID, Double.parseDouble(value));
        }
        throw new InstanceException(messageCreateInstanceException + Product.class);
    }

}
