import java.io.Console;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MapPrefixHandler implements PrefixHandler{
    private final Map<String, Method> mapPrefixes; //Very important, because this is a hashMap that calls methods when you use the ".get()" method.
    private final GameMap gameMap; //The gameMap object that all the commands will be referring to.
    public MapPrefixHandler(GameMap gameMap) {
        mapPrefixes = new HashMap<>();
        this.gameMap = gameMap;
        initializePrefixes();

    }
    /**
     * Initializes all the prefixes and stores them into the hashMap
     * The latter part utilizes Java's Method Reflection, or Reflection, which allows the calling of a method
     * even without knowing whether it exists.
     */
    @Override
    public void initializePrefixes() {
        try {
            mapPrefixes.put("look", this.getClass().getMethod("handleLook", String.class)); //String.class at the back is a parameter that it accepts
            mapPrefixes.put("move", this.getClass().getMethod("handleMove", String.class));
            mapPrefixes.put("item", this.getClass().getMethod("handleItem", String.class));
            mapPrefixes.put("bag", this.getClass().getMethod("handleBag", String.class));
            mapPrefixes.put("help", this.getClass().getMethod("handleHelp", String.class));
            mapPrefixes.put("admin", this.getClass().getMethod("handleAdmin", String.class));
            // Add more combat prefixes and their corresponding logic here
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }
    /**
     * Gets an input and splits it at the first whitespace. Takes the first part and calls it through the mapPrefixes
     * hashMap, using the second part as the argument that is inputted into the method that is called
     * @param input The input that the user will give.
     */
    @Override
    public void handlePrefix(String input) {
        String[] parts = input.split(" ", 2);
        String prefix = parts[0].toLowerCase();
        String argument = ((parts.length > 1) ? parts[1] : "").toLowerCase();

        Method prefixMethod = mapPrefixes.get(prefix);
        if (prefixMethod != null) {
            try {
                prefixMethod.invoke(this, argument);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unknown prefix: " + prefix);
        }
    }

    /**
     * Handles the logic behind looking at tiles in the map and revealing them
     * @param argument The direction that the user has chosen to look in.
     */
    public void handleLook(String argument) {
        switch (argument) {
            case "left":
                gameMap.playerLook("left");
                break;
            case "right":
                gameMap.playerLook("right");
                break;
            case "up":
                gameMap.playerLook("up");
                break;
            case "down":
                gameMap.playerLook("down");
                break;
            default:
                System.out.println("Illegal command detected. Please try again or get help with the \"Help\" command.");
                break;
        }
    }

    /**
     * Handles the logic behind movement between tiles in the map.
     * @param argument The direction that the user has chosen to move in.
     */
    public void handleMove(String argument) {
        switch (argument) {
            case "left":
                gameMap.playerMove(-1,0);
                break;
            case "right":
                gameMap.playerMove(1, 0);
                break;
            case "up":
                gameMap.playerMove(0,-1);
                break;
            case "down":
                gameMap.playerMove(0,1);
                break;
            default:
                System.out.println("Illegal command detected. Please try again or get help with the \"Help\" command.");
                break;
        }
    }

    /**
     * Work in progress, hasn't been implemented to examine items yet.
     * @param argument The specific commands related to items in the game.
     */
    public void handleItem(String argument) {
        System.out.println("Iteming " + argument);
    }

    /**
     * Handles the logic behind accessing contents in the bag
     * @param argument The specific command the user wants.
     */
    public void handleBag(String argument) {
        Scanner scanner = new Scanner(System.in);
        switch (argument) {
            case "contents":
                Character.bag.listBagContents();
                System.out.println("To inspect an item, input the corresponding number.");
                System.out.println("Input anything else to exit...");
                while (true) {
                    try {
                        String input = scanner.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Exiting...");
                            break;
                        }
                        int itemNumber = Integer.parseInt(input);
                        int lastIndex = Character.bag.getItems().size() - 1;
                        if (itemNumber < 1 || itemNumber > lastIndex + 1) {
                            System.out.println("Invalid item number. Exiting...");
                            break;
                        }
                        Item item = Character.bag.getItem(itemNumber - 1);
                        item.displayFormattedStats();
                        System.out.println(ConsoleColors.CYAN + "--------------------" + ConsoleColors.RESET);

                        // Prompt for another inspection or exit
                        System.out.println("To inspect another item, input the corresponding number.");
                        System.out.println("Input nothing to exit...");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Exiting...");
                        break;
                    }
                }
                break;
            case "equip weapon":
                Character.bag.switchWeapon();
                break;
            case "remove item":
                int discardedItemIndex;
                do {
                    System.out.println("What would you like to discard?");
                    Character.bag.listBagContents();
                    System.out.println("Input anything else to exit.");
                    String input = scanner.nextLine();
                    try {
                        discardedItemIndex = Integer.parseInt(input);
                        if (discardedItemIndex >= 1 && discardedItemIndex < Character.bag.getItems().size()) {
                            // Valid item index, remove the item
                            Item discardedItem = Character.bag.getItem(discardedItemIndex - 1);
                            Character.bag.removeItem(discardedItemIndex - 1);
                            System.out.println("You have discarded: " + discardedItem.getName());
                        } else {
                            System.out.println("Invalid item index. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        // User input is not a number, exit the loop
                        break;
                    }
                } while (true);
                break;
            case "use item":
                int usedItemIndex;
                do {
                    System.out.println("Which item would you like to use?");
                    Character.bag.listAllItemsExceptWeapons();
                    ArrayList<Item> onlyItems = Character.bag.bagItemsArrayList();
                    System.out.println("Input anything else to exit.");
                    String input = scanner.nextLine();
                    try {
                        usedItemIndex = Integer.parseInt(input);
                        if (usedItemIndex >= 1 && usedItemIndex < Character.bag.getItems().size()+1) {
                            Item usedItem = onlyItems.get(usedItemIndex-1);
                            Character.bag.removeItem(usedItem);
                            usedItem.useItem();
                            break;
                        }
                        else {
                            System.out.println("Invalid item index. Please try again.");
                            break;
                        }
                    }
                    catch (NumberFormatException e) {
                        break;
                    }
                } while (true);


            default:
                System.out.println("Illegal command detected. Please try again or get help with the \"Help\" command.");
                break;
        }
    }

    /**
     * Calls respective tutorial texts based on the user choice
     * @param argument What the user wants help with
     */
    public void handleHelp(String argument) {
        switch (argument) {
            case "look":
                Text.stallReadFile("./src/text/lookTutorial.txt");
                break;
            case "move":
                Text.stallReadFile("./src/text/moveTutorial.txt");
                break;
            case "item":
                Text.stallReadFile("./src/text/itemTutorial.txt");
                break;
            case "bag":
                Text.stallReadFile("./src/text/bagTutorial.txt");
                break;
            default:
                Text.stallReadFile("./src/text/movementHelpTutorial.txt");
                break;
        }
    }

    /**
     * Hidden commands that give cheats or are for bug-fixing
     * @param argument The specific command within "Admin" that the user wants to access
     */
    public void handleAdmin(String argument) {
        System.out.println("ADMIN COMMAND");
        switch (argument) {
            case ("show map"):
                gameMap.displayMap();
                break;
            case ("show vmap"):
                gameMap.displayVisualMap();
                break;
            case ("get loot"):
                Scanner scanner = new Scanner(System.in);
                System.out.println("How much loot do you want?");
                int choice = scanner.nextInt();
                LootManager.plainLoot(choice);
                break;
            case ("get weapon"):
                System.out.println(Character.weapon);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameMap gameMap = new GameMap();
        gameMap.setupMap();
        MapPrefixHandler mapPrefixHandler = new MapPrefixHandler(gameMap);
        System.out.println("Enter a command");
        mapPrefixHandler.handlePrefix(scanner.nextLine());
    }
}
