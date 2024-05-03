import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class Saves {
    static String fileName;
    static long runs;
    static long bestRun;
    static long ATK;
    static long DEF;
    static long HP;
    static long Mana;
    private static int fileNo;
    public static void pickSave() throws IOException, ParseException {
        System.out.println("Which save would you like to enter?");
        System.out.println("[1] File 1\t[2] File 2\t[3] File 3");
        Scanner scanner = new Scanner(System.in);
        boolean yesNo = false;
        fileNo = 0;
        while (!(fileNo == 1 || fileNo == 2 || fileNo == 3)) {
            try {
                fileNo = scanner.nextInt();
                if (fileNo == 1 || fileNo == 2 || fileNo == 3) {
                    System.out.println("File Loading Success!");
                }
                else {
                    System.out.println("Invalid input. Please enter a valid integer");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer");
            }
            scanner.nextLine();
        }
        loadData(readFile(), fileNo);
        int result = newSaveChecker(fileNo);
        if (result == 0) {
            System.out.println("File Loading Success!");
        }
        else if (result == 1) {
            System.out.println("File Loading Success!");
        }
        else {
            System.out.println("You have chosen not to write in this file.");
            System.out.println("Restart the Application to Pick a Different Save.");
            System.exit(0);
        }
    }
    private static JSONArray readFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/Save.json"));
        return data;
    }
    private static void loadData(JSONArray data, int fileNo) { //Note to self, I need to find a way for the player to start new saves in unused save slots
        JSONObject obj = (JSONObject) data.get(fileNo-1);
        fileName = (String) obj.get("Name");
        runs = (long) obj.get("Runs");
        bestRun = (long) obj.get("Best Run");
        ATK = (long) obj.get("ATK");
        DEF = (long) obj.get("DEF");
        HP = (long) obj.get("HP");
        Mana = (long) obj.get("Mana");
    }
    /**Used to check if the save is a new one*/
    private static int newSaveChecker(int choice) throws IOException, ParseException {
        if (fileName.equals("[BLANK FILE]")) {
            System.out.println("The file you have selected is a blank file.");
            System.out.println("Would you like to create a new save in this file?");
            System.out.println("[Y] Yes, [N] No");
            String name;
            if (yesNo() == 'Y') {
                while (fileName.equalsIgnoreCase("[BLANK FILE]")) {
                    System.out.println("What would you like to name this file?");
                    System.out.println("Note that the file name will also be your character name in game.");
                    Scanner scanner = new Scanner(System.in);
                    name = scanner.nextLine();
                    if (name.equalsIgnoreCase("[BLANK FILE]")) {
                        System.out.println("That is an illegal name.");
                    } else {
                        // Update the JSON object with the new filename
                        JSONObject obj = new JSONObject();
                        obj.put("Name", name);
                        obj.put("Runs", 0);
                        obj.put("Best Run", 0);
                        obj.put("ATK", 3);
                        obj.put("DEF", 0);
                        obj.put("HP", 20);
                        obj.put("Mana", 10);

                        // Update the JSON array with the modified object
                        JSONArray data = readFile();
                        int fileNo = choice-1;
                        data.set(fileNo, obj);

                        // Write the modified JSON array back to the file
                        File file = new File("./src/Save.json");
                        FileWriter writer = new FileWriter(file);
                        writer.write(data.toJSONString());
                        writer.close();

                        System.out.println("Save file created successfully!");
                        loadData(readFile(), choice);
                        return 1;
                    }
                }
            }
            return 2;
        }
        return 0;
    }
    public static void updateRuns() throws IOException, ParseException {
        runs++;
        writeSave();
        System.out.println("Runs successfully updated!");
    }
    private static void writeSave() throws IOException, ParseException {
        // Update the JSON object with the new run count
        JSONObject obj = new JSONObject();
        obj.put("Name", fileName);
        obj.put("Runs", runs);
        obj.put("Best Run", bestRun);
        obj.put("ATK", ATK);
        obj.put("DEF", DEF);
        obj.put("HP", HP);
        obj.put("Mana", Mana);

        // Update the JSON array with the modified object
        JSONArray data = readFile();
        int fileNo = Saves.fileNo - 1;
        data.set(fileNo, obj);

        // Write the modified JSON array back to the file
        File file = new File("./src/Save.json");
        FileWriter writer = new FileWriter(file);
        writer.write(data.toJSONString());
        writer.close();
    }
    /**Very important method that wipes a save!*/
    public static void wipeSave() throws IOException, ParseException {
        JSONArray data = readFile();
        int index = fileNo - 1;
        JSONObject obj = (JSONObject) data.get(index);
        obj.put("Name", "[BLANK FILE]");
        obj.put("Runs", 0);
        obj.put("Best Run", 0);
        obj.put("ATK", 3);
        obj.put("DEF", 0);
        obj.put("HP", 20);
        obj.put("Mana", 10);
        data.set(index, obj);

        // Write the modified JSON array back to the file
        File file = new File("./src/Save.json");
        FileWriter writer = new FileWriter(file);
        writer.write(data.toJSONString());
        writer.close();

        System.out.println("Save file wiped successfully!");
    }
    /** Method that displays stats of a save*/
    public static void checkSave() {
        System.out.println("File Name: " + fileName);
        System.out.println("Runs: " + runs);
        System.out.println("Best Run: " + bestRun);
        System.out.println("Base ATK: " + ATK);
        System.out.println("Base DEF: " + DEF);
        System.out.println("Base HP: " + HP);
        System.out.println("Base Mana: " + Mana);
    }
    /**Simple method that returns either yes or no*/
    private static char yesNo() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        char choice = 'A'; // Default value

        while (!validInput) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("Y")) {
                choice = 'Y';
                validInput = true;
            } else if (input.equalsIgnoreCase("N")) {
                choice = 'N';
                validInput = true;
            } else {
                System.out.println("Please input either 'Y' or 'N'.");
            }
        }

        return choice;
    }
    public static String getFileName() {
        return fileName;
    }
    public static int getRuns() {
        return (int) runs;
    }
    public static int getBestRun() {
        return (int) bestRun;
    }
    public static int getATK() {
        return (int) ATK;
    }
    public static int getDEF() {
        return (int) DEF;
    }
    public static int getHP() {
        return (int) HP;
    }
    public static int getMana() {
        return (int) Mana;
    }
}