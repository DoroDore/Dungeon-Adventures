import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class CombatPrefixHandler implements PrefixHandler {
    private Map<String, Method> combatPrefixes;

    public CombatPrefixHandler() {
        combatPrefixes = new HashMap<>();
        initializePrefixes();
    }

    public void initializePrefixes() {
        try {
            combatPrefixes.put("Attack", this.getClass().getMethod("handleLook"));
            combatPrefixes.put("Defend", this.getClass().getMethod("handleLook"));
            // Add more combat prefixes and their corresponding logic here
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        }


    }

}