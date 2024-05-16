import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
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
