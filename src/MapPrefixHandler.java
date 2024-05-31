import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MapPrefixHandler implements PrefixHandler{
    private final Map<String, Method> mapPrefixes;
    private final GameMap gameMap;
    public MapPrefixHandler(GameMap gameMap) {
        mapPrefixes = new HashMap<>();
        this.gameMap = gameMap;
        initializePrefixes();

    }
    @Override
    public void initializePrefixes() {
        try {
            mapPrefixes.put("look", this.getClass().getMethod("handleLook", String.class));
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
    public void handleItem(String argument) {
        System.out.println("Iteming " + argument);
    }
    public void handleBag(String argument) {
        Scanner scanner = new Scanner(System.in);
        switch (argument) {
            case "contents":
                Character.bag.listBagContents();
                System.out.println("Input anything to continue...");
                scanner.nextLine();
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
                        if (discardedItemIndex >= 1) {
                            // Valid item index, remove the item
                            Items discardedItem = Character.bag.getItem(discardedItemIndex - 1);
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
            default:
                System.out.println("Illegal command detected. Please try again or get help with the \"Help\" command.");
                break;
        }
    }
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
