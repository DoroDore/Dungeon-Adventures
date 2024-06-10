import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private static final Map<Integer, Item> itemMap = new HashMap<>();
    /**Creates all items based on data provided in the parameters.*/
    public static void createItems(JSONArray data) {
        for (Object o : data) {
            JSONObject obj = (JSONObject) o;
            int id = ((Long) obj.get("ID")).intValue();
            String name = (String) obj.get("Name");
            String rarity = (String) obj.get("Rarity");
            String color = ConsoleColors.colorMapping.getOrDefault(rarity, ConsoleColors.CYAN);
            String formattedName = color + name + ConsoleColors.RESET;
            int size = ((Long) obj.get("Size")).intValue();
            int value = ((Long) obj.get("Value")).intValue();
            String description = (String) obj.get("Description");

            JSONArray effectsArray = (JSONArray) obj.get("Effect");
            JSONArray intensitiesArray = (JSONArray) obj.get("EffectIntensity");

            String[] effects = null;
            int[] effectIntensities = null;

            if (effectsArray != null && intensitiesArray != null) {
                effects = new String[effectsArray.size()];
                effectIntensities = new int[intensitiesArray.size()];

                for (int i = 0; i < effectsArray.size(); i++) {
                    effects[i] = (String) effectsArray.get(i);
                    effectIntensities[i] = ((Long) intensitiesArray.get(i)).intValue();
                }
            }

            Item item = new Item(id, formattedName, size, rarity, value, description, effects, effectIntensities);
            itemMap.put(id, item);
        }
    }
    private static JSONArray readFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader(fileName));
        return data;
    }
    public static Map<Integer, Item> getItemMap() {
        return itemMap;
    }
}
