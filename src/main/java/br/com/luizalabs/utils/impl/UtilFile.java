package br.com.luizalabs.utils.impl;

import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.IFile;
import br.com.luizalabs.utils.UtilString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UtilFile implements IFile {

    private IDelimiter delimiter;

    public UtilFile() {
        delimiter = new UtilDelimiter();
    }

    public IDelimiter getDelimiter(){
        if(delimiter == null){
            delimiter = new UtilDelimiter();
        }
        return delimiter;
    }

    @Override
    public boolean isFile(String path) {
        return Paths.get(path).toFile().isFile();
    }
    @Override
    public void read(String pathFile) {
        Path path = Paths.get(pathFile);
        List<String> linesFiles = null;
        try {
            linesFiles = Files.readAllLines(path);
            for (String line : linesFiles) {
                getDelimiter().lineReading(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
