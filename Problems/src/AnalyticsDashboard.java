import java.util.*;

public class AnalyticsDashboard {

    Map<String, Integer> pageViews = new HashMap<>();
    Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    Map<String, Integer> trafficSource = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors
                .computeIfAbsent(url, k -> new HashSet<>())
                .add(userId);

        trafficSource.put(source,
                trafficSource.getOrDefault(source, 0) + 1);
    }

    public List<String> topPages() {

        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        (a, b) -> pageViews.get(b) - pageViews.get(a));

        pq.addAll(pageViews.keySet());

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            result.add(pq.poll());
        }

        return result;
    }
}
