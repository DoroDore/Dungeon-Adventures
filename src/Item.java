public class Item {
    private int id;
    private String name;
    private int size;
    private String rarity;
    private int value;
    private String description;
    private final String[] effects;
    int[] effectIntensities;
    public Item(int id, String name, int size, String rarity, int value, String description, String[] effects, int[] effectIntensities) {
        String color = ConsoleColors.colorMapping.getOrDefault(rarity, ConsoleColors.CYAN); // Cyan for any other rarity
        this.name = color + name + ConsoleColors.RESET; // Reset color after the name
        this.id = id;
        this.size = size;
        this.rarity = rarity;
        this.value = value;
        this.description = description;
        this.effects = effects;
        this.effectIntensities = effectIntensities;
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
    public String[] getEffects() {
        return effects;
    }
    public int[] getEffectIntensities() {
        return effectIntensities;
    }
    public void displayFormattedStats() {
        System.out.println("Name: " + getName() + "\t Size: " + getSize() + "\t Rarity: " + getRarity());
        System.out.println("Value: " + getValue());
        System.out.println("Description: " + getDescription());
    }
    public void displayItemEffects() {
        System.out.println("Item Effects:");
        for (int i = 0; i < effects.length; i++) {
            String effect = effects[i];
            int intensity = effectIntensities[i];
            System.out.println("Effect: " + effect + ", Intensity: " + intensity);
        }
    }
    public void useItem() {
        System.out.println("You used the " + name + ".");
        String modifier;
        for (int i = 0; i < effects.length; i++) {
            if (effectIntensities[i] >= 0) {
                modifier = "increased";
            }
            else {
                modifier = "decreased";
            }
            switch (effects[i]) {
                case "PlayerATK":
                    Character.attack += effectIntensities[i];
                    System.out.println("Your base attack " + modifier + " by " + effectIntensities[i]);
                    break;
                case "PlayerDEF":
                    Character.defense += effectIntensities[i];
                    System.out.println("Your base defense " + modifier + " by " + effectIntensities[i]);
                    break;
                case "PlayerHP":
                    Character.HP += effectIntensities[i];
                    System.out.println("Your HP " + modifier + " by " + effectIntensities[i]);
                    break;
                case "WeaponATK":
                    int originalAttack = Character.weapon.getAttack();
                    Character.weapon.setAttack(originalAttack + effectIntensities[i]);
                    System.out.println("Your weapon's attack " + modifier + " by " + effectIntensities[i]);
                    break;
                case "WeaponATK*":
                    originalAttack = Character.weapon.getAttack();
                    Character.weapon.setAttack(originalAttack*effectIntensities[i]);
                    System.out.println("Your weapon's attack was multiplied by a value of " + effectIntensities[i]);
                    break;
            }
        }
    }
}
