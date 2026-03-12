import java.util.*;

public class Java_week1_ques5 {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("Top Pages:");

        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Map.Entry<String, Integer> entry = pq.poll();
            String url = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(url).size();

            System.out.println((count + 1) + ". " + url + " - " + views +
                    " views (" + unique + " unique)");
            count++;
        }

        int total = 0;
        for (int c : trafficSources.values()) {
            total += c;
        }

        System.out.println("\nTraffic Sources:");

        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double percent = (entry.getValue() * 100.0) / total;
            System.out.println(entry.getKey() + ": " + percent + "%");
        }
    }

    public static void main(String[] args) {

        Java_week1_ques5 analytics = new Java_week1_ques5();

        analytics.processEvent("/article/breaking-news", "user_123", "google");
        analytics.processEvent("/article/breaking-news", "user_456", "facebook");
        analytics.processEvent("/sports/championship", "user_789", "google");
        analytics.processEvent("/sports/championship", "user_123", "direct");
        analytics.processEvent("/article/breaking-news", "user_999", "google");

        analytics.getDashboard();
    }
}
