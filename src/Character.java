public class Character {
    public static String name;
    public static int attack;
    public static int defense;
    public static int HP;
    public static int mana;
    public static Weapon weapon;
    public static Bag bag;
    /**Gets the data from the save file that was read.*/
    public static void loadData() {
        name = Saves.getFileName();
        attack = Saves.getATK();
        defense = Saves.getDEF();
        HP = Saves.getHP();
        mana = Saves.getMana();
        weapon = WeaponManager.getWeaponMap().get(1);
        bag = new Bag(10);
    }
    public static String getName() {
        return name;
    }
    public static int getAttack() {
        return attack;
    }
    public static int getDefense() {
        return defense;
    }
    public static int getHP() {
        return HP;
    }
    public static int getMana() {
        return mana;
    }
    public static void playerDamage(int damageReceived) {
        HP -= (damageReceived - defense);
    }
    public static int getPlayerTotalAttack() {
        return attack + weapon.getAttack();
    }
    public static int getPlayerTotalDefense() {
        return defense + weapon.getDefense();
    }
    public static void displayPlayerStats() {
        System.out.println("Character Name: " + name + "\tCurrent HP: " + HP);
        System.out.println("ATK: " + getPlayerTotalAttack() + "\tDEF: " + getPlayerTotalDefense() + "\tMana: " + mana);
        System.out.println("Weapon: " + weapon.getName() + "\t Bag Space: " + bag.getAvailableSpace());
    }
}