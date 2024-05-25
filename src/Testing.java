import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        testRandomEvent();
    }
    public static void testRandomEvent() {
        Random rand = new Random();
        EventManager.loadEvents();
        EventTree eventTree = EventManager.getEventTree(rand.nextInt(EventManager.getEventMapSize())+1);
        eventTree.runInteraction();
    }
    public static void testDisplayTree() {
        String jsonFilePath = "./src/data/events/wanderingTrader.json";
        File jsonFile = new File(jsonFilePath);
        EventTree eventTree = new EventTree();
        eventTree.buildTreeFromJSON(jsonFile);
        Node rootNode = eventTree.getRootNode();
        eventTree.printEventTree(rootNode);
    }
    public static void testMovement() {
        GameMap map = new GameMap();
        MapPrefixHandler mapPrefixHandler = new MapPrefixHandler(map);
        map.setupMap();
        map.displayVisualMap();
        while (true) {
            System.out.println(Arrays.toString(map.getPlayerCoordinate()));
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input a command");
            mapPrefixHandler.handlePrefix(scanner.nextLine());
        }
    }
}


