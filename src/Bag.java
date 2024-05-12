import java.util.ArrayList;
import java.util.List;

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

    public boolean removeItem(Items item) {
        return items.remove(item);
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
