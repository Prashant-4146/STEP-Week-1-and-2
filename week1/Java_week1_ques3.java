import java.util.*;

class DNSEntry {
    String domain;
    String ipAddress;
    long expiryTime;

    DNSEntry(String domain, String ipAddress, int ttl) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class Java_week1_ques3 {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;
    private int maxSize = 5;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ipAddress;
            } else {
                cache.remove(domain);
            }
        }

        misses++;
        String newIP = queryUpstreamDNS(domain);
        cacheEntry(domain, newIP, 300);
        return "Cache MISS → " + newIP;
    }

    private void cacheEntry(String domain, String ip, int ttl) {

        if (cache.size() >= maxSize) {
            String firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
        }

        cache.put(domain, new DNSEntry(domain, ip, ttl));
    }

    private String queryUpstreamDNS(String domain) {
        Random r = new Random();
        return "172.217.14." + (200 + r.nextInt(50));
    }

    public String getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0) / total;
        return "Hit Rate: " + hitRate + "%";
    }

    public static void main(String[] args) {

        Java_week1_ques3 dns = new Java_week1_ques3();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("openai.com"));
        System.out.println(dns.resolve("google.com"));

        System.out.println(dns.getCacheStats());
    }
}
