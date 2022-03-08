package br.com.luizalabs.utils.factory;

import br.com.luizalabs.entities.User;
import br.com.luizalabs.exceptions.InstanceException;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.impl.UtilDelimiter;
import lombok.NoArgsConstructor;

import static br.com.luizalabs.utils.UtilString.messageCreateInstanceException;

@NoArgsConstructor
public class FactoryUser {

    private IDelimiter delimiter;

    public FactoryUser(IDelimiter delimiter) {
        this.delimiter = delimiter;
    }


    public User user(String line) throws InstanceException {
        if (delimiter instanceof UtilDelimiter) {
            delimiter.findBySpace(line);
            String idUser = delimiter.find(line, 0, ((UtilDelimiter) delimiter).getJ());
            String username = delimiter.find(line, ((UtilDelimiter) delimiter).getK(), line.length());
            ((UtilDelimiter) delimiter).setLine(username);
            delimiter.findByZero(username);
            username = delimiter.find(username, 0, ((UtilDelimiter) delimiter).getJ());
            return new User(String.valueOf(Integer.parseInt(idUser)), username);
        }
        throw new InstanceException(messageCreateInstanceException + User.class);
    }
}
