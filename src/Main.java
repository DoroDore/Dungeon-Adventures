import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static GameMap gameMap;
    static MapPrefixHandler mapPrefixHandler;
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        setup(); //Very important, used to set up the weapon map and load in the events.
        Saves.pickSave(); //Used to select a save, still a WIP though, I might make it so that the player can get different weapons in the future.
        boolean lock = true; //Note to self: Maybe this is bad practice
        while (lock) {
            char response = menuOptions(scanner);
            if (response == 'P') {
                Character.loadData();
                Character.displayPlayerStats();
                System.out.println("This is the character you will play as. Are you sure?");
                System.out.println("[Y] Yes, [N] No");
                response = yesNo(scanner);
                if (response == 'Y') {
                    lock = false;
                    Text.stallReadFile("./src/text/enterDungeonMessage.txt");
                    while (Character.getHP() > 0) {
                        movementInterface(scanner);
                    }

                }
            }
            else if (response == 'S') {
                handleSave(scanner);
            }
            else if (response == 'I') {
                handleInstructions(scanner);
            }
            else if (response == 'E') {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }
    /**The interface that pops up at the start of each turn.*/
    private static void movementInterface(Scanner scanner) {
        gameMap.displayVisualMap();
        System.out.println(Arrays.toString(gameMap.getPlayerCoordinate()));
        Character.displayPlayerStats();
        System.out.println(ConsoleColors.BRIGHT_YELLOW + "Please input a command" + ConsoleColors.RESET);
        mapPrefixHandler.handlePrefix(scanner.nextLine());
    }
    /**Sets up all the crucial hashmaps and variables that are necessary for the game to function*/
    private static void setup() throws IOException, ParseException {
        gameMap = new GameMap();
        mapPrefixHandler = new MapPrefixHandler(gameMap);
        gameMap.setupMap();
        EventManager.loadEvents();
        WeaponManager.createWeapons(readFile("./src/data/Weapon.json"));
        ItemManager.createItems(readFile("./src/data/Items.json"));
    }
    /**Handles all the code for viewing and accessing saves.*/
    private static void handleSave(Scanner scanner) throws IOException, ParseException {
        char response;
        System.out.println("Here are your current stats!\n");
        Saves.checkSave();
        System.out.println("\n[W] Wipe Save");
        System.out.println("Press Anything Else to Continue...");
        scanner.nextLine();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("W")) {
            System.out.println("Do you want to wipe this save?");
            System.out.println("[Y] Yes, [N] No");
            response = yesNo(scanner);
            if (response == 'Y') {
                System.out.println("To delete your save, type in full caps \"DELETE\"");
                System.out.println("Typing anything else will cancel this process");
                String responseString = scanner.nextLine();
                if (responseString.equals("DELETE")) {
                    Saves.wipeSave();
                    System.out.println("Save Successfully Wiped!");
                    System.out.println("Restart the program to play again.");
                    System.out.println("Press Enter to Continue...");
                    stall();
                    System.exit(0);
                }
                else {
                    System.out.println("Delete Process Terminated.");
                    System.out.println("Press Enter to Continue...");
                    stall();
                }
            }
            else {
                System.out.println("Delete Process Terminated.");
                System.out.println("Press Enter to Continue...");
                stall();
            }
        }
    }
    /**Handles all the code for displaying various instructions.*/
    private static void handleInstructions(Scanner scanner) {
        boolean subLock = true;
        while (subLock) {
            System.out.println("Welcome to the tutorial page! Input any of the following words to view tutorials");
            System.out.println("Tutorial\nPrefixes\nTiles\nItems\nEXIT");
            scanner.nextLine();
            String responseString = scanner.nextLine().toLowerCase();
            switch (responseString) {
                case "tutorial":
                    Text.stallReadFile("./src/text/tutorial.txt");
                    break;
                case "prefixes":
                    Text.stallReadFile("./src/text/prefixesTutorial.txt");
                    break;
                case "tiles":
                    Text.stallReadFile("./src/text/tilesTutorial.txt");
                    break;
                case "items":
                    Text.stallReadFile("./src/text/itemsTutorial.txt");
                    break;
                case "exit":
                    subLock = false;
                    break;
                default:
                    System.out.println("Invalid Input Detected.");
                    break;
            }
        }
    }
    /**A neat little method used to stall the progression of text by requiring the player to press enter/return*/
    private static void stall() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    /**Sa method that only returns a y/n after an acceptable answer is given.*/
    private static char yesNo(Scanner scanner) {
        boolean validInput = false;
        char choice = 'A'; // Default value
        while (!validInput) {
            String input = scanner.nextLine();
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
    /**Displays the options in the menu at the start of the game.*/
    private static char menuOptions(Scanner scanner) {
        Text.readFile("./src/text/mainMenu.txt");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("P")) {
            return 'P';
        }
        else if (input.equals("S")) {
            return 'S';
        }
        else if (input.equals("I")) {
            return 'I';
        }
        else if (input.equals("E")) {
            return 'E';
        }
        else {
            System.out.println("Please input a valid character.");
            return 'A';
        }
    }
    /**General Purpose for Reading Files*/
    public static JSONArray readFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader(fileName));
        return data;
    }
}