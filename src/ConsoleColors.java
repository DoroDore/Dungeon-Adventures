import java.util.HashMap;
import java.util.Map;
//Very clever class which has all my ANSI Escape Sequences, so I don't have to type them all out every time.
public class ConsoleColors {
    // Color constants
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    // Bright colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    // Bold versions of colors
    public static final String BOLD_BLACK = "\u001B[30;1m";
    public static final String BOLD_RED = "\u001B[31;1m";
    public static final String BOLD_GREEN = "\u001B[32;1m";
    public static final String BOLD_YELLOW = "\u001B[33;1m";
    public static final String BOLD_BLUE = "\u001B[34;1m";
    public static final String BOLD_PURPLE = "\u001B[35;1m";
    public static final String BOLD_CYAN = "\u001B[36;1m";
    public static final String BOLD_WHITE = "\u001B[37;1m";
    // Custom Colors

    // Color mapping for rarity
    public static Map<String, String> colorMapping;

    static {
        colorMapping = new HashMap<>();
        colorMapping.put("Common", RESET);     // Default color
        colorMapping.put("Rare", BLUE);        // Blue
        colorMapping.put("Epic", PURPLE);      // Purple
        colorMapping.put("Legendary", YELLOW); // Yellow
        colorMapping.put("Sacred", RED);       // Red
    }

    public static void main(String[] args) {
        System.out.println(BLACK + "This is black text." + RESET);
        System.out.println(RED + "This is red text." + RESET);
        System.out.println(GREEN + "This is green text." + RESET);
        System.out.println(YELLOW + "This is yellow text." + RESET);
        System.out.println(BLUE + "This is blue text." + RESET);
        System.out.println(PURPLE + "This is purple text." + RESET);
        System.out.println(CYAN + "This is cyan text." + RESET);
        System.out.println(WHITE + "This is white text." + RESET);
    }
}