package main;

import br.com.luizalabs.FileUserJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private FileUserJson fileUserJson;
    private List<Path> files_exists;
    private List<Path> files_not_exists;
    private static final int INDEX_FILE_EXIST = 0;
    private static final int INDEX_FILE_NOT_EXIST = 0;

    @BeforeEach
    void setUp() {
        files_exists = Arrays.asList(Path.of("datasource\\data_1.txt"), Path.of("datasource\\data_2.txt"));
        files_not_exists = Arrays.asList(Path.of("datasource\\data_3.txt"), Path.of("datasource\\data_4.txt"));
        fileUserJson = new FileUserJson();
    }

    @Test
    void createFile() {
        List<Path> files = new ArrayList<>();
        String path_directory = "datasource\\";
        File directory = new File(path_directory);
        assertTrue(directory.exists());
        String file = "data_1.txt";
        Path path_file = Path.of(path_directory+file);
        files.add(path_file);
        assertTrue(Main.verifyIsFiles(files));
    }

    @Test
    void read() {
        assertNotNull(files_exists);
        assertNotNull(fileUserJson);
        assertEquals(FileUserJson.class, fileUserJson.getClass());
        Path file = files_exists.get(INDEX_FILE_EXIST);
        assertEquals("data_1.txt", file.getFileName().toString());
        boolean result_files_read = Main.read(files_exists, fileUserJson);
        assertTrue(result_files_read);
    }

    @Test
    void readException() {
        assertNotNull(files_not_exists);
        assertNotNull(fileUserJson);
        assertEquals(FileUserJson.class, fileUserJson.getClass());
        try {
            boolean result_files_read = Main.read(files_not_exists, fileUserJson);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
    }


    @Test
    void isFile() {
        assertNotNull(files_exists);
        Path file = files_exists.get(INDEX_FILE_EXIST);
        assertNotNull(file);
        assertInstanceOf(Path.class, file);
        assertTrue(Main.isFile(file));
    }

    @Test
    void isNotFile() {
        assertNotNull(files_not_exists);
        Path file = files_not_exists.get(INDEX_FILE_NOT_EXIST);
        assertNotNull(file);
        assertInstanceOf(Path.class, file);
        assertFalse(Main.isFile(file));
    }

    @Test
    void verifyIsExistsFiles() {
        assertNotNull(files_exists);
        boolean result = Main.verifyIsFiles(files_exists);
        assertTrue(result);
    }

    @Test
    void verifyINotsExistsFiles() {
        assertNotNull(files_not_exists);
        boolean result = Main.verifyIsFiles(files_not_exists);
        assertFalse(result);
    }
}