package main;

import br.com.luizalabs.utils.IFile;
import br.com.luizalabs.utils.UtilDate;
import br.com.luizalabs.utils.UtilString;
import br.com.luizalabs.utils.impl.UtilFile;

public class Main {

    public static void main(String[] args) {
        IFile file = new UtilFile();
        if(file.isFile(UtilString.PATH + UtilString.FILE_DATA_1)){
            file.read(UtilString.PATH + UtilString.FILE_DATA_1);
        }
    }
}
