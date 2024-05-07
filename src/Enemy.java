import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Enemy {
    public static Enemy gCurrentEnemy;
    static HashMap<String, Enemy> gEnemyMap = new HashMap<>();
    private String mName;
    private int mID, mATK, mDEF, mHP, mOriginalHP; // New variable to store original HP
    private String[] mMoves;

    public Enemy(int ID, String name, int attack, int defense, int hp, String[] moves) {
        mID = ID;
        mName = name;
        mATK = attack;
        mDEF = defense;
        mHP = hp;
        mOriginalHP = hp; // Store original HP when creating an instance
        mMoves = moves;
    }
    /**Reads the specific file "Enemy.json" and returns that data*/
    private static JSONArray readFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader("./src/Enemy.json"));
        return data;
    }
    /**Takes data from the readFile function and stores it into the gEnemyMap hashmap*/
    public static void createEnemies(JSONArray data) {
        for (Object o : data) {
            JSONObject obj = (JSONObject) o;
            int id = ((Long) obj.get("ID")).intValue();
            String name = (String) obj.get("Name");
            int attack = ((Long) obj.get("ATK")).intValue();
            int defense = ((Long) obj.get("DEF")).intValue();
            int hp = ((Long) obj.get("HP")).intValue();
            JSONArray movesArray = (JSONArray) obj.get("Moves");
            String[] moves = new String[movesArray.size()];
            for (int i = 0; i < movesArray.size(); i++) {
                moves[i] = (String) movesArray.get(i);
            }
            Enemy enemy = new Enemy(id, name, attack, defense, hp, moves);
            gEnemyMap.put(String.valueOf(id), enemy);
        }
    }
    /**Load mob data into the current slot*/
    public static void loadCurrentMob(String mobID) {
        gCurrentEnemy = gEnemyMap.get(mobID);
    }
    /**getMobData is used to check the data of individual mobs by identifying them by their ID code.*/
    public static void getMobData(String mobID) {
        gCurrentEnemy = gEnemyMap.get(mobID);
        System.out.println("Enemy ID: " + gCurrentEnemy.getEnemyID());
        System.out.println("Enemy Name: " + gCurrentEnemy.getEnemyName());
        System.out.println("Enemy Attack: " + gCurrentEnemy.getEnemyATK());
        System.out.println("Enemy Defense: " + gCurrentEnemy.getEnemyDEF());
        System.out.println("Enemy HP: " + gCurrentEnemy.getEnemyHP());
        String[] tempMoves = gCurrentEnemy.getEnemyMoves();
        System.out.println("Enemy Move 1: " + tempMoves[0]);
        System.out.println("Enemy Move 2: " + tempMoves[1]);
    }
    public static void main(String[] args) throws IOException, ParseException {
        createEnemies(readFile());
        getMobData("1");
    }
    public static void displayEnemyStats() {
        System.out.println("Enemy: " + gCurrentEnemy.mName + "\tEnemy HP: " + gCurrentEnemy.mHP);
        System.out.println("ATK: " + gCurrentEnemy.mATK + "\tDEF: " + gCurrentEnemy.mDEF);
    }
    public static void attackEnemyNormal(int damage) {
        if (gCurrentEnemy.mDEF >= damage) {
            System.out.println("The " + gCurrentEnemy.mName + " took no damage!");
        }
        else {
            System.out.println("The " + gCurrentEnemy.mName + " took " + (damage-gCurrentEnemy.mDEF) + " damage!");
            gCurrentEnemy.mHP -= damage-gCurrentEnemy.getEnemyDEF();
        }

    }
    public static void enemyTurn() {
        Random rand = new Random();
        int attackChoice = rand.nextInt(10)+1;
        if (attackChoice <=5) {
            System.out.println("The " + gCurrentEnemy.mName + " attacks!");
            if (gCurrentEnemy.mATK <= Character.getPlayerDEF()) {
                System.out.println("You took no damage!");
            }
            else {
                System.out.println("You took " + (gCurrentEnemy.mATK-Character.getPlayerDEF()) + " damage!");
                Character.playerDamage(gCurrentEnemy.mATK-Character.getPlayerDEF());
            }
        }
        else if (attackChoice >= 6 && attackChoice <= 8) {
            callMove(0);
        }
        else {
            callMove(1);
        }
        stall();
    }
    /**Used for calling on the method that executes code for a specific move*/
    private static void callMove(int num) {
        String firstMove = gCurrentEnemy.getEnemyMoves()[num];
        switch (firstMove) {
            case "Leap":
                enMoveLeap();
                break;
            case "Reformat":
                enMoveReformat();
                break;
            case "Heavy Slam":
                enMoveHeavySlam();
                break;
            case "Rage":
                enMoveRage();
                break;
            case "Bulldoze":
                enMoveBulldoze();
                break;
            case "Glitch":
                enMoveGlitch();
                break;
            case "Gust":
                enMoveGust();
                break;
            case "Shatter Shot":
                enMoveShatterShot();
                break;
            case "Natural Cure":
                enMoveNaturalCure();
                break;
            case "Control":
                enMoveControl();
                break;
            case "Wooden Strike":
                enMoveWoodenStrike();
                break;
            case "Banana Storm":
                enMoveBananaStorm();
                break;
            case "Banana Blast":
                enMoveBananaBlast();
                break;
            case "Thunderbolt":
                enMoveThunderbolt();
                break;
            case "Soak":
                enMoveSoak();
                break;
            default:
                // Handle the case when the move is not recognized
                System.out.println("Invalid move: " + firstMove);
                break;
        }
    }
    public int getEnemyID() {
        return mID;
    }
    public String getEnemyName() {
        return mName;
    }
    public int getEnemyATK() {
        return mATK;
    }
    public int getEnemyDEF() {
        return mDEF;
    }
    public int getEnemyHP() {
        return mHP;
    }
    public String[] getEnemyMoves() {
        return mMoves;
    }
    public void resetHPToOriginal() {
        mHP = mOriginalHP; // Reset HP to original value
    }
    private static String doesDamage(int incomingDamage, int damageMit) {
        if (incomingDamage <= damageMit) {
            return "no";
        }
        return String.valueOf(incomingDamage-damageMit);
    }
    private static int damageCalc(int incomingDamage, int damageMit) {
        return incomingDamage-damageMit;
    }
    public static void enMoveLeap() {
        System.out.println("The " + gCurrentEnemy.mName + " leaps into the air!");
        System.out.println("The enemy slams down on you, dealing " + doesDamage(gCurrentEnemy.getEnemyATK()*2, Character.getPlayerDEF()) + " damage!");
        Character.playerDamage(damageCalc(gCurrentEnemy.getEnemyATK()*2, Character.getPlayerDEF()));
    }
    public static void enMoveReformat() {
        Random rand = new Random();
        System.out.println("The " + gCurrentEnemy.mName + " uses Reformat!");
        int regen = rand.nextInt(6)+5;
        System.out.println("The " + gCurrentEnemy.mName + " reformats it's body and regenerates " + regen + "HP!");
        gCurrentEnemy.mHP += regen;
    }
    public static void enMoveHeavySlam() {
        System.out.println("The " + gCurrentEnemy.mName + " uses Heavy Slam!");
        System.out.println("The enemy uses it's heavy weight to crush you, dealing " + doesDamage(gCurrentEnemy.getEnemyDEF()*2+3, Character.getPlayerDEF()) + " damage!");
        Character.playerDamage(damageCalc(gCurrentEnemy.getEnemyDEF()*2+3, Character.getPlayerDEF()));
    }
    public static void enMoveRage() {
        Random rand = new Random();
        System.out.println("The " + gCurrentEnemy.mName + " uses Rage!");
        int atkRaise = rand.nextInt(3)+1;
        System.out.println("The enemy's attack rose by " + atkRaise + " points!");
    }
    public static void enMoveBulldoze() {
        System.out.println("The " + gCurrentEnemy.mName + " uses Bulldoze!");
        System.out.println("The enemy slams into you, dealing " + doesDamage(gCurrentEnemy.mHP/2, Character.getPlayerDEF()));
        Character.playerDamage(damageCalc(gCurrentEnemy.getEnemyHP()/2, Character.getPlayerDEF()));
    }
    public static void enMoveGlitch() {
        Random rand = new Random();
        System.out.println("The " + gCurrentEnemy.mName + " uses Glitch!");
        int glitchCrit = rand.nextInt(3)+1;
        if (glitchCrit == 3) {
            System.out.println("The  glitch causes the " + gCurrentEnemy.mName + " to bug out, dealing extra damage!");
            System.out.println("You took " + doesDamage(gCurrentEnemy.mATK*3, Character.getPlayerDEF()) + " damage!");
        }
        else {
            System.out.println("The glitch causes the " + gCurrentEnemy.mName + " to malfunction, making it take damage instead!");
            System.out.println("The " +gCurrentEnemy.mName + "takes " + gCurrentEnemy.mHP/4 + " damage!");
            attackEnemyNormal(gCurrentEnemy.mHP/4);
        }
    }
    public static void enMoveGust() {
        System.out.println("The " + gCurrentEnemy.mName + " uses Gust!");
        System.out.println("The piercing winds pierce through your defenses, dealing " + doesDamage((int) (gCurrentEnemy.mATK * 1.5),Character.getPlayerDEF()/2));
        Character.playerDamage(damageCalc((int) (gCurrentEnemy.mATK * 1.5),Character.getPlayerDEF()/2));
    }
    public static void enMoveShatterShot() {
        System.out.println("The " + gCurrentEnemy.mName + " uses Shatter Shot!");

    }
    public static void enMoveNaturalCure() {

    }
    public static void enMoveControl() {

    }
    public static void enMoveWoodenStrike() {

    }
    public static void enMoveBananaStorm() {

    }
    public static void enMoveBananaBlast() {

    }
    public static void enMoveThunderbolt() {

    }
    public static void enMoveSoak() {

    }
    private static void stall() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}