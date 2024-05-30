import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LootManager {
    public static void plainLoot(int rewardCount) {
        Random rand = new Random();
        ArrayList<Items> rewards = new ArrayList<>();
        for (int i = 0; i < rewardCount; i++) {
            int randInt = rand.nextInt(2);
            switch (randInt) {
                case 0: //Used to put a weapon inside the arrayList
                    rewards.add(getRandomItemFromHashMap(WeaponManager.getWeaponMap()));
                    break;
                case 1: //Used to put a normal item inside the arrayList;
                    Items item = new Items(0, "Dummy", 1, "Legendary", 10);
                    rewards.add(item);
            }
        }
        System.out.println("Choose your reward!");

        int index = 1;
        for (Items reward : rewards) {
            System.out.println("[" + index + "] " + reward.getName());
            index++;
        }
        Scanner scanner = new Scanner(System.in);
        int selectedRewardIndex;
        do {
            System.out.print("Enter the index of your chosen reward: ");
            selectedRewardIndex = scanner.nextInt();
        } while (selectedRewardIndex < 1 || selectedRewardIndex > rewards.size());

        Items selectedReward = rewards.get(selectedRewardIndex - 1);
        if (selectedReward.getSize() > Character.bag.getAvailableSpace()) {
            System.out.println("You do not have enough space to put this item in your bag.");
        }
        else {
            System.out.println("You have added " + selectedReward.getName() + " to your inventory!");
            Character.bag.addItem(selectedReward);
        }
    }
    public static <K, V> V getRandomItemFromHashMap(Map<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(map.size());

        return map.get(map.keySet().toArray()[randomIndex]);
    }
}
