import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Text {
    public static void readFile(String fileName) {
        //System.out.println("Reading file: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print each line with line breaks
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + fileName);
            e.printStackTrace();
        }
    }
    public static void stallReadFile(String fileName) {
        //System.out.println("Reading file: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);// Process each line (modify as per your needs)
                stall();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + fileName);
            e.printStackTrace();
        }
    }
    private static void stall() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
