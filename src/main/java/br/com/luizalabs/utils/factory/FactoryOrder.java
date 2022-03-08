package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.exceptions.InstanceException;
import br.com.luizalabs.exceptions.UpdateObjectException;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDate;
import br.com.luizalabs.utils.impl.UtilDelimiter;
import lombok.NoArgsConstructor;

import static br.com.luizalabs.utils.UtilString.messageCreateInstanceException;
import static br.com.luizalabs.utils.UtilString.messageUpdate;

@NoArgsConstructor
public class FactoryOrder {

    private IDelimiter delimiter;

    public FactoryOrder(IDelimiter delimiter) {
        this.delimiter = delimiter;
    }

    public Order order(String line) throws InstanceException {
        if (delimiter instanceof UtilDelimiter) {
            delimiter.findBySpace(line);
            String orderId = delimiter.find(line, 0, ((UtilDelimiter) delimiter).getJ() - 1);
            delimiter.findReverse(orderId);
            orderId = delimiter.find(orderId, 0, ((UtilDelimiter) delimiter).getJ() + 1);
            return new Order(String.valueOf(Integer.parseInt(orderId)));
        }
        throw new InstanceException(messageCreateInstanceException + Order.class);
    }

    public void updateDateOrder(Order order, Product product, String line) throws UpdateObjectException {
        if (delimiter instanceof UtilDelimiter) {
            line =  ((UtilDelimiter) delimiter).getLine();
            String date = delimiter.find(line, String.valueOf(product.getValue()).length(), line.length());
            delimiter.findByPositionNotZero(order.getId());
            order.setDate(UtilDate.formatDate(date));
            return;
        }
        throw new UpdateObjectException(messageUpdate + Order.class);
    }
}
