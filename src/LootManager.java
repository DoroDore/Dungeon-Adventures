import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LootManager {
    /**The basic loot generation method that gets random weapons and items and allows the user to pick one.*/
    public static void plainLoot(int rewardCount) {
        Random rand = new Random();
        ArrayList<Item> rewards = new ArrayList<>();
        for (int i = 0; i < rewardCount; i++) {
            int randInt = rand.nextInt(2);
            switch (randInt) {
                case 0: //Used to put a weapon inside the arrayList
                    rewards.add(getRandomItemFromHashMap(WeaponManager.getWeaponMap()));
                    break;
                case 1: //Used to put a normal item inside the arrayList;
                    rewards.add(getRandomItemFromHashMap(ItemManager.getItemMap()));
                    break;
            }
        }
        System.out.println("Choose your reward!");

        int index = 1;
        for (Item reward : rewards) {
            String suffix = "";
            if (reward instanceof Weapon) {
                suffix = "(W)";
            } else {
                suffix = "(I)";
            }
            System.out.println("[" + index + "] " + reward.getName() + " " + suffix);
            index++;
        }
        Scanner scanner = new Scanner(System.in);
        int selectedRewardIndex = 1;
        boolean validInput = false;
        do {
            System.out.print("Enter the index of your chosen reward: ");
            String input = scanner.nextLine();
            try {
                selectedRewardIndex = Integer.parseInt(input);
                if (selectedRewardIndex >= 1 && selectedRewardIndex <= rewards.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid index.");
            }
        } while (!validInput);

        Item selectedReward = rewards.get(selectedRewardIndex - 1);
        if (selectedReward.getSize() > Character.bag.getAvailableSpace()) {
            System.out.println("You do not have enough space to put this item in your bag.");
        } else {
            System.out.println("You have added " + selectedReward.getName() + " to your inventory!");
            Character.bag.addItem(selectedReward);
        }
    }
    /**Used to get a random item from a hashMap and return it.*/
    public static <K, V> V getRandomItemFromHashMap(Map<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(map.size());

        return map.get(map.keySet().toArray()[randomIndex]);
    }
}
