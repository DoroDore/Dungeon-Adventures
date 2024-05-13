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
            mapPrefixes.put("Look", this.getClass().getMethod("handleLook", String.class));
            mapPrefixes.put("Move", this.getClass().getMethod("handleMove", String.class));
            mapPrefixes.put("Item", this.getClass().getMethod("handleItem", String.class));
            mapPrefixes.put("Bag", this.getClass().getMethod("handleBag", String.class));
            // Add more combat prefixes and their corresponding logic here
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }
    @Override
    public void handlePrefix(String input) {
        String[] parts = input.split(" ", 2);
        String prefix = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";

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
        System.out.println("Looking " + argument);
    }
    public void handleMove(String argument) {
        System.out.println("Move command received");
        switch (argument) {
            case "Left":
                gameMap.playerMove(-1,0);
                break;
            case "Right":
                gameMap.playerMove(1, 0);
                break;
            case "Up":
                gameMap.playerMove(0,-1);
                break;
            case "Down":
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameMap gameMap = new GameMap();
        MapPrefixHandler mapPrefixHandler = new MapPrefixHandler(gameMap);
        System.out.println("Enter a command");
        mapPrefixHandler.handlePrefix(scanner.nextLine());
    }
}
