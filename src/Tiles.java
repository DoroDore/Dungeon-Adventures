import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Tiles {
    private int id;
    private String name;
    private char symbol;
    static HashMap<Integer, Tiles> tileMap = new HashMap<Integer, Tiles>();
    public Tiles(int id, String name, char symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public char getSymbol() {
        return symbol;
    }
    private static JSONArray readFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/data/Tiles.json"));
        return data;
    }
    public static void createTiles(JSONArray data) {
        for (Object o : data) {
            JSONObject obj = (JSONObject) o;
            int id = ((Long) obj.get("ID")).intValue();
            String name = (String) obj.get("Name");
            String symbolString = (String) obj.get("Symbol");
            char symbol = symbolString.charAt(0);
            Tiles tile = new Tiles(id, name, symbol);
            tileMap.put(id, tile);
        }
    }
}
