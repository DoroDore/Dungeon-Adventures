import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeaponManager {
    private static final Map<Integer, Weapon> weaponMap = new HashMap<>();

    /**
     * Reads a JSONArray of data and creates all the weapons within it. Text is also colored during this process
     * based on the rarity of the weapon
     * @param data The data that is inputted in order to create the weapon
     */
    public static void createWeapons(JSONArray data) {
        for (Object o : data) {
            JSONObject obj = (JSONObject) o;
            int id = ((Long) obj.get("ID")).intValue();
            String name = (String) obj.get("Name");
            String rarity = (String) obj.get("Rarity");
            String color = ConsoleColors.colorMapping.getOrDefault(rarity, ConsoleColors.CYAN); // Cyan for any other rarity
            String formattedName = color + name + ConsoleColors.RESET; // Reset color after the name
            int size = ((Long) obj.get("Size")).intValue();
            int value = ((Long) obj.get("Value")).intValue();
            int attack = ((Long) obj.get("Attack")).intValue();
            int defense = ((Long) obj.get("Defense")).intValue();
            String skill = (String) obj.get("Skill");
            int skillCost = ((Long) obj.get("SkillCost")).intValue();
            String skillDescription = (String) obj.get("SkillDescription");
            String weaponDescription = (String) obj.get("WeaponDescription");
            Weapon weapon = new Weapon(id, formattedName, size, rarity, value, attack, defense, skill, skillCost, skillDescription, weaponDescription);
            weaponMap.put(id, weapon);
        }
    }

    /**
     * Reads a file and returns the data from it
     * @param fileName The path of the file
     */
    private static JSONArray readFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader(fileName));
        return data;
    }
    public static Map<Integer, Weapon> getWeaponMap() {
        return weaponMap;
    }

    public static void main(String[] args) throws IOException, ParseException {
        createWeapons(readFile("./src/data/Weapon.json"));
        Weapon weapon = weaponMap.get(4);
        weapon.printStats();
    }
}