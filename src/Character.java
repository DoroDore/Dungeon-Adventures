public class Character {
    public static String gPlayerName;
    public static int gPlayerATK;
    public static int gPlayerDEF;
    public static int gPlayerHP;
    public static int gPlayerMana;
    public static void loadData() {
        gPlayerName = Saves.getFileName();
        gPlayerATK = Saves.getATK();
        gPlayerDEF = Saves.getDEF();
        gPlayerHP = Saves.getHP();
        gPlayerMana = Saves.getMana();
    }
    public static String getPlayerName() {
        return gPlayerName;
    }
    public static int getPlayerATK() {
        return gPlayerATK;
    }
    public static int getPlayerDEF() {
        return gPlayerDEF;
    }
    public static int getPlayerHP() {
        return gPlayerHP;
    }
    public static int getPlayerMana() {
        return gPlayerMana;
    }
    public static void playerDamage(int damageReceived) {
        gPlayerHP -= (damageReceived - gPlayerDEF);
    }
    public static void displayPlayerStats() {
        System.out.println("Character Name: " + gPlayerName + "\tCurrent HP: " + gPlayerHP);
        System.out.println("ATK: " + gPlayerATK + "\tDEF: " + gPlayerDEF + "\tMana: " + gPlayerMana);
    }
}