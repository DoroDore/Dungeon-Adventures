public class Items {
    private int id;
    private String name;
    private int size;
    private String rarity;
    private int value;
    public Items(int ID, String name, int size, String rarity, int value) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.rarity = rarity;
        this.value = value;
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
}
