import java.util.*;

public class Java_Week1_Question1_UsernameAvailabilityChecker {

    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public Java_Week1_Question1_UsernameAvailabilityChecker() {
        users.put("john_doe", 101);
        users.put("admin", 102);
        users.put("prashant", 103);
        users.put("guest", 104);
    }

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add(username + "1");
        suggestions.add(username + "2");
        suggestions.add(username.replace("_", "."));
        suggestions.add(username + "_official");
        return suggestions;
    }

    public String getMostAttempted() {
        String mostAttempted = "";
        int maxAttempts = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxAttempts) {
                mostAttempted = entry.getKey();
                maxAttempts = entry.getValue();
            }
        }

        return mostAttempted + " (" + maxAttempts + " attempts)";
    }

    public static void main(String[] args) {
        Java_Week1_Question1_UsernameAvailabilityChecker checker =
                new Java_Week1_Question1_UsernameAvailabilityChecker();

        String username = "john_doe";

        boolean available = checker.checkAvailability(username);

        if (available) {
            System.out.println("Username '" + username + "' is available.");
        } else {
            System.out.println("Username '" + username + "' is already taken.");
            System.out.println("Suggestions: " + checker.suggestAlternatives(username));
        }

        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("jane_smith");

        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}
