import java.util.ArrayList;
import java.util.List;

public class Node {
    int id, parentID;
    String description, choiceDialogue;
    private final List<Node> children;

    public Node(int id, int parentID, String description, String choiceDialogue) {
        this.id = id;
        this.parentID = parentID;
        this.description = description;
        this.choiceDialogue = choiceDialogue;
        this.children = new ArrayList<>();
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public int getId() {
        return id;
    }

    public int getParentID() {
        return parentID;
    }

    public List<Node> getChildren() {
        return children;
    }
    public String getDescription() {
        return description;
    }
    public String getChoiceDialogue() {
        return choiceDialogue;
    }
    public boolean hasChildren() {
        return !children.isEmpty();
    }
}
