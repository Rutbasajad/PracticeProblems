import java.util.*;
import java.util.concurrent.*;

public class Ecommerce {

    private ConcurrentHashMap<String, AtomicInteger> stockMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Queue<Integer>> waitingList = new ConcurrentHashMap<>();

    public void addProduct(String productId, int stock) {
        stockMap.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new ConcurrentLinkedQueue<>());
    }

    public int checkStock(String productId) {
        return stockMap.get(productId).get();
    }

    public String purchaseItem(String productId, int userId) {

        AtomicInteger stock = stockMap.get(productId);

        while (true) {

            int currentStock = stock.get();

            if (currentStock <= 0) {
                Queue<Integer> queue = waitingList.get(productId);
                queue.add(userId);
                return "Added to waiting list, position #" + queue.size();
            }

            if (stock.compareAndSet(currentStock, currentStock - 1)) {
                return "Success, " + (currentStock - 1) + " units remaining";
            }
        }
    }

    public Queue<Integer> getWaitingList(String productId) {
        return waitingList.get(productId);
    }
}