package main;

import br.com.luizalabs.bd.impl.DBUserImpl;
import br.com.luizalabs.services.impl.JsonService;
import br.com.luizalabs.utils.IFile;
import br.com.luizalabs.utils.UtilString;
import br.com.luizalabs.utils.impl.UtilFile;

public class Main {

    public static void main(String[] args) {
        IFile file = new UtilFile();
        if (file.isFile(UtilString.PATH + UtilString.FILE_DATA_1) && file.isFile(UtilString.PATH + UtilString.FILE_DATA_2)) {
            file.read(UtilString.PATH + UtilString.FILE_DATA_1);
            file.read(UtilString.PATH + UtilString.FILE_DATA_2);
            file.write(new JsonService().createJson(DBUserImpl.getInstance().getUsers()), UtilString.PATH);
        }
    }
}
