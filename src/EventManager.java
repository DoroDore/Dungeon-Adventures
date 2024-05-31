import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EventManager {
    private static final Map<Integer, EventTree> eventMap = new HashMap<>();
    private static final String DIRECTORY_PATH = "./src/data/events"; //Specifies the path that files will be pulled from when creating events
    /**Creates a file array from all the files within the package "events" within the package "data" and constructs each one into a tree*/
    public static void loadEvents() {
        File directory = new File(DIRECTORY_PATH);
        File[] jsonFiles = directory.listFiles((dir, name) -> name.endsWith(".json")); //Logic for getting each individual json file
        if (jsonFiles != null) {
            int idAssigner = 1; //Assigns each one to an id for each access
            for (File jsonFile : jsonFiles) {
                EventTree eventTree = new EventTree();
                eventTree.buildTreeFromJSON(jsonFile);
                eventMap.put(idAssigner, eventTree);
                idAssigner++;
            }
        }
    }

    // Example method to retrieve an EventTree based on its ID
    public static EventTree getEventTree(int eventId) {
        return eventMap.get(eventId);
    }
    public static int getEventMapSize() {
        return eventMap.size();
    }
    /**Runs a random event from the eventMap through a random number within the size of the eventMap*/
    public static void runRandomEvent() {
        Random rand = new Random();
        EventTree eventTree = eventMap.get(rand.nextInt(EventManager.getEventMapSize())+1);
        eventTree.runInteraction();
    }
}
