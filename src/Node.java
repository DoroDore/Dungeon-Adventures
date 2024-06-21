import java.util.ArrayList;
import java.util.List;

public class Node {
    int id, parentID;
    String description, choiceDialogue;
    private final List<Node> children;
    String[] effects;
    int[] effectValues;

    public Node(int id, int parentID, String description, String choiceDialogue, String[] effects, int[] effectValues) {
        this.id = id;
        this.parentID = parentID;
        this.description = description;
        this.choiceDialogue = choiceDialogue;
        this.children = new ArrayList<>();
        this.effects = effects;
        this.effectValues = effectValues;
    }

    /**
     * Adds a child to the Node's List of children
     * @param node The specified node that will be added
     */
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
    //Note to self, I basically stopped here where the nodes give rewards
    public void getOutcomes() {
        String modifier;
        for (int i = 0; i < effects.length; i++) {
            if (effectValues[i] >= 0) {
                modifier = "increased";
            }
            else {
                modifier = "decreased";
            }
            switch (effects[i]) {
                case "Item":
                    Character.bag.addItem(ItemManager.getItemMap().get(effectValues[i]));
                    break;
                case "PlayerHP":
                    Character.HP += effectValues[i];
                    System.out.println("Your HP " + modifier + " by " + effectValues[i]);
                    break;
            }
        }
    }
}
