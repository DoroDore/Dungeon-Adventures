import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Text {
    /**
     * Reads the data of a file and prints it all out
     * @param fileName The path of the file
     */
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

    /**
     * Reads the data of a file, but prints it out one line by one line as the user presses return/enter
     * @param fileName The path of the file
     */
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
    /**Used for making the user press return/enter before the next line shows up*/
    private static void stall() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
