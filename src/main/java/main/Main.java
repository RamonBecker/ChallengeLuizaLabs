package main;

import br.com.luizalabs.FileUserJson;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Path> files = new ArrayList<>();
        Scanner read_entry = new Scanner(System.in);
        createFile(files, read_entry);

        if (verifyIsFiles(files)) {
            FileUserJson fileUserJson = new FileUserJson();
            boolean result_files_read = read(files, fileUserJson);
            if (result_files_read) {
                System.out.println("Todos os arquivos foram lidos com sucesso!");
                JSONArray result_json_create = fileUserJson.create();
                if (!result_json_create.isEmpty()) {
                    System.out.println("JSON criado com sucesso!");
                    boolean result_json_export = fileUserJson.export(result_json_create, "datasource\\output.json");
                    if (result_json_export) {
                        System.out.println("JSON exportado com sucesso!");
                    }
                }
            }
        } else {
            System.out.println("Os arquivos informados não foram encontrados!");
        }
    }

    public static void createFile(List<Path> files, Scanner read_entry) {
        int qtd_files = -1;
        String directory = "datasource\\";

        while (true) {
            System.out.print("Informe a quantidade de arquivos a serem lidos:");
            qtd_files = read_entry.nextInt();
            if (qtd_files > 0) {
                break;
            } else {
                System.out.println("Entrada inválida");
            }
        }

        for (int i = 0; i < qtd_files; i++) {
            System.out.println("Informe o nome do arquivo " + (i + 1) + " e a sua extensão. Exemplo: file.txt: ");
            String file = read_entry.next().trim();
            Path path = Path.of(directory + file);
            files.add(path);
        }
    }

    public static boolean read(List<Path> files, FileUserJson fileUserJson) {
        int file_read = 0;
        try {
            for (Path file : files) {
                List<String> linesFiles = null;
                linesFiles = Files.readAllLines(file);
                for (String line : linesFiles) {
                    fileUserJson.read(line);
                }
                System.out.println("Arquivo: " + file.getFileName() + " lido com sucesso!");
                file_read++;
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler os arquivos!");
        }
        return file_read == files.size();
    }

    public static boolean isFile(Path path) {
        return path.toFile().isFile();
    }

    public static boolean verifyIsFiles(List<Path> files) {
        int file_found = 0;
        for (Path file : files) {
            boolean result_file_found = isFile(file);
            if (result_file_found) {
                file_found++;
            }
        }
        return file_found == files.size();
    }

}
