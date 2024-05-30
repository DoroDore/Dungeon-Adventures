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
    public Items getItem(int index) {
        return items.get(index);
    }
    public void removeItem(int index) {
        items.remove(index);
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
