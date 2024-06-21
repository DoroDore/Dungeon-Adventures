import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bag {
    private int bagSpace; //Tracks the total size of the bag
    private final List<Item> items; //The dynamic list which keeps track of everything in the player's bag, the bagSpace is calculated based off the totalBagSpace - the sum of the space of all the items.
    public Bag(int bagSpace) {
        this.bagSpace = bagSpace;
        this.items = new ArrayList<>();
    }
    /**Adds an item to the list, but only if there is sufficient bag space.*/
    public void addItem(Item item) {
        while (item.getSize() > getAvailableSpace()) {
            System.out.println("Your bag is full. Would you like to get rid of an item to make room for the " + item.getName() + "?");
            System.out.println("Yes/No");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                System.out.println("Enter the index of the item you want to remove:");
                listBagContents(); // Assuming you have a method to display the items in the bag
                int index = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (index >= 0 && index < items.size()) {
                    Item removedItem = items.remove(index-1);
                    System.out.println("Removed item: " + removedItem.getName());
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
            }
            else if (response.equals("no")) {
                System.out.println("Item not added to the bag.");
                return; // Exit the method if the user chooses not to remove any items
            }
            else {
                System.out.println("Please input a valid option.");
            }
        }

        if (getTotalSize() + item.getSize() <= bagSpace) {
            items.add(item);
            System.out.println("You have added " + item.getName() + " to your inventory!");
        }
    }
    /**Used to list the contents of the bag in an organized manner*/
    public void listBagContents() {
        int counter = 1;
        String suffix;
        for (Item item : items) {
            if (item instanceof Weapon) {
                suffix = "(W)";
            } else {
                suffix = "(I)";
            }
            System.out.println("[" + counter + "] Name: " + item.getName() + ", Size: " + item.getSize() + " " + suffix);
            counter++;
        }
    }
    /**Lists exclusively the weapons found in the bag.*/
    public void listBagWeapons() {
        int counter = 1;
        for (Item item : items) {
            if (item instanceof Weapon weapon) { // Check if the item is an instance of Weapon
                System.out.println("[" + counter + "] Name: " + weapon.getName() + ", Size: " + weapon.getSize());
                counter++;
            }
        }
    }
    /**Used to list all items except weapons*/
    public void listAllItemsExceptWeapons() {
        int counter = 1;
        for (Item item : items) {
            if (!(item instanceof Weapon)) {
                System.out.println("[" + counter + "] Name: " + item.getName() + ", Size: " + item.getSize());
                counter++;
            }
        }
    }
    /**Returns an ArrayList of Weapons found in the bag.*/
    public ArrayList<Weapon> bagWeaponsArrayList() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon weapon) {
                weapons.add(weapon);
            }
        }
        return weapons;
    }
    public ArrayList<Item> bagItemsArrayList() {
        ArrayList<Item> itemList = new ArrayList<>();
        for (Item item : items) {
            if (!(item instanceof Weapon)) {
                itemList.add(item);
            }
        }
        return itemList;
    }
    /**Used to switch the current weapon of the player.*/
    public void switchWeapon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which weapon would you like to equip?");
        System.out.println("---------------------------");
        Character.bag.listBagWeapons();
        ArrayList<Weapon> weapons = Character.bag.bagWeaponsArrayList(); //Creates a separate List for easier choosing.
        int chosenWeaponIndex;
        do {
            System.out.print("Enter the index of your chosen weapon: ");
            chosenWeaponIndex = scanner.nextInt();
            scanner.nextLine();
        } while (chosenWeaponIndex < 1 || chosenWeaponIndex > weapons.size()); //While part makes easier logic to check for illegal inputs
        System.out.println("You have equipped the " + weapons.get(chosenWeaponIndex - 1).getName() + "!");
        Character.bag.removeItem(weapons.get(chosenWeaponIndex -1)); //This is where weapons ArrayList gets put to good use
        if (Character.weapon != null) { //Additional Logic if player has a weapon equipped
            System.out.println("You unequipped the " + Character.weapon.getName() + ".");
            Character.bag.addItem(Character.weapon);
        }
        Character.weapon = weapons.get(chosenWeaponIndex - 1);
        System.out.println("Input anything to continue...");
        scanner.nextLine();
    }
    /**Used to return a specific item using an index*/
    public Item getItem(int index) {
        return items.get(index);
    }
    /**Removes an item from a specific index*/
    public void removeItem(int index) {
        items.remove(index);
    }
    /**Removes the first instance of a specified item*/
    public void removeItem(Item item) {
        int index = Character.bag.items.indexOf(item);
        if (index != -1) {
            Character.bag.items.remove(index);
        }
    }
    /**Returns available space from simple formula*/
    public int getAvailableSpace() {
        return bagSpace - getTotalSize();
    }
    /**Calculates total size of items inside bag*/
    private int getTotalSize() {
        int totalSize = 0;
        for (Item item : items) {
            totalSize += item.getSize();
        }
        return totalSize;
    }
    public List<Item> getItems() {
        return items;
    }
}
