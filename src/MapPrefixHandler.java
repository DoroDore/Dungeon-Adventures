import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MapPrefixHandler implements PrefixHandler{
    private Map<String, Method> mapPrefixes;
    private GameMap gameMap;
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
        }
    }
    public void handleItem(String argument) {
        System.out.println("Iteming " + argument);
    }
    public void handleBag(String argument) {
        System.out.println("Bagging " + argument);
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
