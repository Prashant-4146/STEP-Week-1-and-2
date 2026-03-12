import java.util.*;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefillTime;

    TokenBucket(int maxTokens) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    void refill() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTime;

        if (elapsed >= 3600000) {
            tokens = maxTokens;
            lastRefillTime = now;
        }
    }

    boolean allowRequest() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}

public class Java_week1_ques6 {

    private HashMap<String, TokenBucket> clients = new HashMap<>();
    private int limit = 1000;

    public String checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(limit));
        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            return "Allowed (" + bucket.tokens + " requests remaining)";
        } else {
            return "Denied (0 requests remaining)";
        }
    }

    public String getRateLimitStatus(String clientId) {

        TokenBucket bucket = clients.get(clientId);
        if (bucket == null) {
            return "No data";
        }

        int used = limit - bucket.tokens;
        long resetTime = bucket.lastRefillTime + 3600000;

        return "{used: " + used + ", limit: " + limit + ", reset: " + resetTime + "}";
    }

    public static void main(String[] args) {

        Java_week1_ques6 limiter = new Java_week1_ques6();

        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.checkRateLimit("abc123"));

        for (int i = 0; i < 997; i++) {
            limiter.checkRateLimit("abc123");
        }

        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.getRateLimitStatus("abc123"));
    }
}
