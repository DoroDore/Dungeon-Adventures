import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bag {
    private int bagSpace;
    private List<Items> items;
    public Bag(int bagSpace) {
        this.bagSpace = bagSpace;
        this.items = new ArrayList<>();
    }
    public boolean addItem(Items item) {
        if (getTotalSize() + item.getSize() <= bagSpace) {
            items.add(item);
            return true;
        }
        return false;
    }
    public void listBagContents() {
        int counter = 1;
        for (Items item : items) {
            System.out.println("[" + counter + "] Name: " + item.getName() + ", Size: " + item.getSize());
            counter++;
        }
        Scanner scanner = new Scanner(System.in);
    }
    public void listBagWeapons() {
        int counter = 1;
        for (Items item : items) {
            if (item instanceof Weapon weapon) { // Check if the item is an instance of Weapon
                System.out.println("[" + counter + "] Name: " + weapon.getName() + ", Size: " + weapon.getSize());
                counter++;
            }
        }
    }
    public ArrayList<Weapon> bagWeaponsArrayList() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Items item : items) {
            if (item instanceof Weapon weapon) {
                weapons.add(weapon);
            }
        }
        return weapons;
    }

    public void switchWeapon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which weapon would you like to equip?");
        System.out.println("---------------------------");
        Character.bag.listBagWeapons();
        ArrayList<Weapon> weapons = Character.bag.bagWeaponsArrayList();
        int chosenWeaponIndex;
        do {
            System.out.print("Enter the index of your chosen weapon: ");
            chosenWeaponIndex = scanner.nextInt();
            scanner.nextLine();
        } while (chosenWeaponIndex < 1 || chosenWeaponIndex > weapons.size());
        System.out.println("You have equipped the " + weapons.get(chosenWeaponIndex - 1).getName() + "!");
        Character.bag.removeItem(weapons.get(chosenWeaponIndex -1));
        if (Character.weapon != null) {
            System.out.println("You unequipped the " + Character.weapon.getName() + ".");
            Character.bag.addItem(Character.weapon);
        }
        Character.weapon = weapons.get(chosenWeaponIndex - 1);
        System.out.println("Input anything to continue...");
        scanner.nextLine();
    }
    public Items getItem(int index) {
        return items.get(index);
    }
    public void removeItem(int index) {
        items.remove(index);
    }
    public void removeItem(Items item) {
        int index = Character.bag.items.indexOf(item);
        if (index != -1) {
            Character.bag.items.remove(index);
        }
    }
    public int getAvailableSpace() {
        return bagSpace - getTotalSize();
    }

    private int getTotalSize() {
        int totalSize = 0;
        for (Items item : items) {
            totalSize += item.getSize();
        }
        return totalSize;
    }

}
