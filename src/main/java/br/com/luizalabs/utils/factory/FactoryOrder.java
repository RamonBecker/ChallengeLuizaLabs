package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.Order;
import br.com.luizalabs.entities.Product;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDate;
import br.com.luizalabs.utils.impl.UtilDelimiter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FactoryOrder {

    private IDelimiter delimiter;

    public FactoryOrder(IDelimiter delimiter) {
        this.delimiter = delimiter;
    }

    public Order order(String line) {
        String orderId = delimiter.find(line, 0, ((UtilDelimiter) delimiter).getJ() - 1);
        delimiter.findReverse(orderId);
        orderId = delimiter.find(orderId, 0, ((UtilDelimiter) delimiter).getJ() + 1);
        return new Order(String.valueOf(Integer.parseInt(orderId)));
    }

    public void updateDateOrder(Order order, Product product, String line) {
        String date = delimiter.find(line, String.valueOf(product.getValue()).length(), line.length());
        delimiter.findByPositionNotZero(order.getId());
        order.setId(delimiter.find(order.getId(), ((UtilDelimiter) delimiter).getJ(), order.getId().length()));
        order.setDate(UtilDate.formatDate(date));
    }
}
