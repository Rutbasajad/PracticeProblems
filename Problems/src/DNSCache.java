import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, long ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {

    private Map<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return entry.ip;
            } else {
                cache.remove(domain);
            }
        }

        misses++;

        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(ip, 300));

        return ip;
    }

    private String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public void stats() {
        double rate = (hits * 100.0) / (hits + misses);
        System.out.println("Hit Rate: " + rate + "%");
    }
}