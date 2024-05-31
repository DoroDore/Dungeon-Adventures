public class Item {
    private int id;
    private String name;
    private int size;
    private String rarity;
    private int value;
    private String description;
    public Item(int id, String name, int size, String rarity, int value, String description) {
        String color = ConsoleColors.colorMapping.getOrDefault(rarity, ConsoleColors.CYAN); // Cyan for any other rarity
        this.name = color + name + ConsoleColors.RESET; // Reset color after the name
        this.id = id;
        this.size = size;
        this.rarity = rarity;
        this.value = value;
        this.description = description;
    }
    public void setID(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public String getRarity() {
        return rarity;
    }
    public int getValue() {
        return value;
    }
    public String getDescription() {
        return description;
    }
}
