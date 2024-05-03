public class Items {
    private String name;
    private int size;
    private String rarity;
    private int value;
    public Items(String name, int size, String rarity, int value) {
        this.name = name;
        this.size = size;
        this.rarity = rarity;
        this.value = value;
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
