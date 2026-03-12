import java.util.*;

public class Java_Week1_Question2_FlashSaleInventoryManager {

    private HashMap<String, Integer> inventory = new HashMap<>();
    private HashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new HashMap<>();

    public Java_Week1_Question2_FlashSaleInventoryManager() {
        inventory.put("IPHONE15_256GB", 100);
        waitingList.put("IPHONE15_256GB", new LinkedHashMap<>());
    }

    public synchronized String checkStock(String productId) {
        int stock = inventory.getOrDefault(productId, 0);
        return stock + " units available";
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            inventory.put(productId, stock - 1);
            return "Success, " + (stock - 1) + " units remaining";
        } else {
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            queue.put(userId, queue.size() + 1);
            return "Added to waiting list, position #" + queue.size();
        }
    }

    public static void main(String[] args) {

        Java_Week1_Question2_FlashSaleInventoryManager manager =
                new Java_Week1_Question2_FlashSaleInventoryManager();

        System.out.println(manager.checkStock("IPHONE15_256GB"));

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));

        for (int i = 0; i < 98; i++) {
            manager.purchaseItem("IPHONE15_256GB", i);
        }

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
    }
}
