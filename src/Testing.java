import org.w3c.dom.ls.LSOutput;

import java.util.Map;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        GameMap map = new GameMap();
        MapPrefixHandler mapPrefixHandler = new MapPrefixHandler(map);
        map.setupMap();
        map.displayMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a command");
        mapPrefixHandler.handlePrefix(scanner.nextLine());
        map.displayMap();
    }


}
