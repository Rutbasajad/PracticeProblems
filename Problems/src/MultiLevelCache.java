import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}

public class MultiLevelCache {

    LRUCache<String, String> L1 = new LRUCache<>(10000);
    Map<String, String> L2 = new HashMap<>();
    Map<String, String> database = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id)) {
            return L1.get(id);
        }

        if (L2.containsKey(id)) {

            String video = L2.get(id);
            L1.put(id, video);
            return video;
        }

        String video = database.get(id);

        if (video != null) {
            L2.put(id, video);
        }

        return video;
    }
}