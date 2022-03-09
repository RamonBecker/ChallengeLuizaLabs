package br.com.luizalabs.utils.impl;

import br.com.luizalabs.exceptions.ExportFileException;
import br.com.luizalabs.utils.IDelimiter;
import br.com.luizalabs.utils.IFile;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static br.com.luizalabs.utils.UtilString.*;

public class UtilFile implements IFile {

    private IDelimiter delimiter;

    public UtilFile() {
        delimiter = new UtilDelimiter();
    }

    public IDelimiter getDelimiter() {
        if (delimiter == null) {
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

    @Override
    public void write(Object obj, String path) {

        String file_output = FILE_NAME_EXPORT;

        if (obj instanceof JSONArray) {
            file_output += TYPE_FILE_JSON;
        }

        try (FileWriter file = new FileWriter(path + file_output)) {
            file.write(obj.toString());
        } catch (IOException e) {
            throw new ExportFileException(MESSAGE_EXPORT_FILE_EXCEPTION);
        }
    }
}
