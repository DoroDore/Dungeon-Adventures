import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class EventTree {
    private final HashMap<Integer, Node> eventMap;

    public EventTree() {
        eventMap = new HashMap<>();
    }
    /**A method that reads a json file and creates each individual node, before assigning it to its parent*/
    public void buildTreeFromJSON(File jsonFile) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(jsonFile)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;

                int id = Integer.parseInt(jsonObj.get("ID").toString());
                int parentID = Integer.parseInt(jsonObj.get("ParentID").toString());
                String description = jsonObj.get("Description").toString();
                String choiceDialogue = (String) jsonObj.get("ChoiceDialogue");

                Node node = new Node(id, parentID, description, choiceDialogue);
                eventMap.put(id, node);

                if (parentID != -1) {
                    Node parent = eventMap.get(parentID);
                    parent.addChild(node);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**Retrieves the root node, useful for finding the start of the event*/
    public Node getRootNode() {
        return eventMap.get(0);
    }
    /**Prints out everything in the event starting from a specified node*/
    public void printEventTree(Node node) {
        printNode(node, 0);
    }
    /**
     * Recursively prints the information of a given Node and its child nodes in a hierarchical structure.
     *
     * @param node         The current node to print.
     * @param indentLevel  The level of indentation for formatting purposes.
     */
    private void printNode(Node node, int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("  ");
        }

        System.out.println(indent + "Description: " + node.getDescription());
        System.out.println(indent + "Choice Dialogue: " + node.getChoiceDialogue());

        for (Node child : node.getChildren()) {
            System.out.println(indent + "Child:");
            printNode(child, indentLevel + 1);
        }
    }
    /**Runs the interaction from the Tree that calls it. Goes down the path decided by the user's inputs*/
    public void runInteraction() {
        Scanner scanner = new Scanner(System.in);
        int input;
        Node currentNode = getRootNode();
        System.out.println(ConsoleColors.CYAN + currentNode.getDescription() + ConsoleColors.RESET);
        while (currentNode.hasChildren()) {
            List<Node> children = currentNode.getChildren();
            for (int i = 0; i < children.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + children.get(i).getDescription());
            }
            System.out.println(ConsoleColors.WHITE + "Please input the corresponding number related to the choice." + ConsoleColors.RESET);

            boolean validInput = false;
            while (!validInput) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.next(); // Discard the invalid input
                } else {
                    input = scanner.nextInt();
                    if (input < 1 || input > children.size()) {
                        System.out.println("Invalid input! Please select a valid option.");
                    } else {
                        validInput = true;
                        currentNode = children.get(input - 1);
                        System.out.println(ConsoleColors.CYAN + currentNode.getChoiceDialogue() + ConsoleColors.RESET);
                    }
                }
            }
        }

        System.out.println("Temporary Ending");
    }
}