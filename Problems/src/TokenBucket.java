import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    int refillRate;
    long lastRefill;

    TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {

        long now = System.currentTimeMillis();

        int newTokens =
                (int) ((now - lastRefill) / 1000 * refillRate);

        tokens = Math.min(maxTokens, tokens + newTokens);

        lastRefill = now;

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }
}

public class RateLimiter {

    Map<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId,
                new TokenBucket(1000, 1000 / 3600));

        return clients.get(clientId).allowRequest();
    }
}
