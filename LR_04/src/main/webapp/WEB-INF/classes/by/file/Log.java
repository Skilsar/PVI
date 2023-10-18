package by.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static final String LOG_FILE_PATH = "E:\\BGTU\\PVI\\LR_04\\src\\log.txt";//

    public static void info(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            System.out.println("LOGGER: " + message);
            writer.write(message);
            writer.newLine(); // Добавляем перевод строки
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
