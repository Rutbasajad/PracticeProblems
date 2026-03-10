import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Social_Media{

    private Map<String, Integer> userMap = new ConcurrentHashMap<>();
    private Map<String, Integer> attemptMap = new ConcurrentHashMap<>();

    public boolean checkAvailability(String username) {

        // track attempts
        attemptMap.put(username, attemptMap.getOrDefault(username, 0) + 1);


        return !userMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!userMap.containsKey(candidate))
                suggestions.add(candidate);
        }

        String dotVersion = username.replace("_", ".");
        if (!userMap.containsKey(dotVersion))
            suggestions.add(dotVersion);

        return suggestions;
    }

    public String getMostAttempted() {

        String result = null;
        int max = 0;

        for (String user : attemptMap.keySet()) {
            int count = attemptMap.get(user);
            if (count > max) {
                max = count;
                result = user;
            }
        }

        return result + " (" + max + " attempts)";
    }

    public void registerUser(String username, int userId) {
        userMap.put(username, userId);
    }
}