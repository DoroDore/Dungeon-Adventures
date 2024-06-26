import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public interface PrefixHandler {
    Map<String, Method> prefixes = new HashMap<>();

    default void initializePrefixes() {
        try {
            prefixes.put("Look", this.getClass().getMethod("handleLook", String.class));
            prefixes.put("Move", this.getClass().getMethod("handleMove", String.class));
            // Add more prefixes and their corresponding methods here
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    default void handlePrefix(String input) {
        String[] parts = input.split(" ", 2);
        String prefix = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";

        Method prefixMethod = prefixes.get(prefix);
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
    public static void main(String[] args) {
        /*prefixHandler.initializePrefixes();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command");
        prefixHandler.handlePrefix(scanner.nextLine());*/
    }
}