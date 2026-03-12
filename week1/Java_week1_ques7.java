import java.util.*;

public class Java_week1_ques7 {

    private HashMap<String, Integer> queryFrequency = new HashMap<>();

    public void addQuery(String query) {
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : queryFrequency.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        List<String> results = new ArrayList<>();
        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            Map.Entry<String, Integer> entry = pq.poll();
            results.add(entry.getKey() + " (" + entry.getValue() + " searches)");
            count++;
        }

        return results;
    }

    public void updateFrequency(String query) {
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
        System.out.println(query + " → Frequency: " + queryFrequency.get(query));
    }

    public static void main(String[] args) {

        Java_week1_ques7 auto = new Java_week1_ques7();

        auto.addQuery("java tutorial");
        auto.addQuery("javascript");
        auto.addQuery("java download");
        auto.addQuery("java tutorial");
        auto.addQuery("java 21 features");

        System.out.println("Suggestions for 'jav':");

        List<String> suggestions = auto.search("jav");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ". " + suggestions.get(i));
        }

        auto.updateFrequency("java 21 features");
        auto.updateFrequency("java 21 features");
    }
}
