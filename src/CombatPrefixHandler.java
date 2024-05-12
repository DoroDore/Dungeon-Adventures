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
            combatPrefixes.put("Attack", this.getClass().getMethod("handleAttack", String.class));
            combatPrefixes.put("Defend", this.getClass().getMethod("handleDefend", String.class));
            // Add more combat prefixes and their corresponding logic here
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }
    public void handlePrefix(String input) {
        String[] parts = input.split(" ", 2);
        String prefix = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";

        Method prefixMethod = combatPrefixes.get(prefix);
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
    public void handleAttack(String argument) {
        System.out.println("Attacking " + argument);
    }
    public void handleDefend(String argument) {
        System.out.println("Defending " + argument);
    }

    public static void main(String[] args) {
        CombatPrefixHandler combatPrefixHandler = new CombatPrefixHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Command");
        combatPrefixHandler.handlePrefix(scanner.nextLine());
    }

}