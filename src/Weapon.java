public class Weapon extends Items{
    private int attack;
    private int defense;
    public Weapon(String name, int size, String rarity, int value, int attack, int defense) {
        super(name, size, rarity, value);
        this.attack = attack;
        this.defense = defense;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
}
