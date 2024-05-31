public class Character {
    public static String playerName;
    public static int playerATK;
    public static int playerDEF;
    public static int playerHP;
    public static int playerMana;
    public static Weapon weapon;
    public static Bag bag;
    /**Gets the data from the save file that was read.*/
    public static void loadData() {
        playerName = Saves.getFileName();
        playerATK = Saves.getATK();
        playerDEF = Saves.getDEF();
        playerHP = Saves.getHP();
        playerMana = Saves.getMana();
        weapon = WeaponManager.getWeaponMap().get(1);
        bag = new Bag(10);
    }
    public static String getPlayerName() {
        return playerName;
    }
    public static int getPlayerATK() {
        return playerATK;
    }
    public static int getPlayerDEF() {
        return playerDEF;
    }
    public static int getPlayerHP() {
        return playerHP;
    }
    public static int getPlayerMana() {
        return playerMana;
    }
    public static void playerDamage(int damageReceived) {
        playerHP -= (damageReceived - playerDEF);
    }
    public static void displayPlayerStats() {
        System.out.println("Character Name: " + playerName + "\tCurrent HP: " + playerHP);
        System.out.println("ATK: " + playerATK + "\tDEF: " + playerDEF + "\tMana: " + playerMana);
        System.out.println("Weapon: " + weapon.getName() + "\t Bag Space: " + bag.getAvailableSpace());
    }
}