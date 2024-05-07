import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Saves.pickSave();
        Scanner scanner = new Scanner(System.in);
        boolean lock = true;
        while (lock) {
            char response = menuOptions();
            if (response == 'P') {
                Character.loadData();
                Saves.checkSave();
                System.out.println("This is the character you will play as. Are you sure?");
                System.out.println("[Y] Yes, [N] No");
                response = yesNo();
                if (response == 'Y') {
                    lock = false;
                    System.out.println("Temporary Start Game");
                }
            }
            else if (response == 'S') {
                System.out.println("Here are your current stats!\n");
                Saves.checkSave();
                System.out.println("\n[W] Wipe Save");
                System.out.println("Press Anything Else to Continue...");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("W")) {
                    System.out.println("Do you want to wipe this save?");
                    System.out.println("[Y] Yes, [N] No");
                    response = yesNo();
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
            else if (response == 'I') {
                boolean subLock = true;
                while (subLock) {
                    System.out.println("Welcome to the tutorial page! Input any of the following words to view tutorials");
                    System.out.println("Tutorial\nPrefixes\nTiles\nItems\nEXIT");
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
            else if (response == 'E') {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }
    private static void stall() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
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
    private static char menuOptions() {
        Scanner scanner = new Scanner(System.in);
        Text.readFile("./src/text/mainMenu.txt");
        String input = scanner.next();
        if (input.equalsIgnoreCase("P")) {
            return 'P';
        }
        else if (input.equalsIgnoreCase("S")) {
            return 'S';
        }
        else if (input.equalsIgnoreCase("I")) {
            return 'I';
        }
        else if (input.equalsIgnoreCase("E")) {
            return 'E';
        }
        else {
            System.out.println("Please input a valid character.");
            return 'A';
        }
    }
}