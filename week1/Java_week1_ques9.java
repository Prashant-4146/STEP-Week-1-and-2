import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    long time;

    Transaction(int id, int amount, String merchant, long time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
    }
}

public class Java_week1_ques9 {

    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void findTwoSum(int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);
                System.out.println("(" + other.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    public void findTwoSumWithTimeWindow(int target, long window) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);

                if (Math.abs(t.time - other.time) <= window) {
                    System.out.println("(" + other.id + ", " + t.id + ")");
                }
            }

            map.put(t.amount, t);
        }
    }

    public void detectDuplicates() {

        HashMap<String, List<Transaction>> map = new HashMap<>();

        for (Transaction t : transactions) {
            String key = t.amount + "_" + t.merchant;
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t);
        }

        for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Duplicate: " + entry.getKey());
            }
        }
    }

    public void findKSum(int k, int target) {
        kSumHelper(new ArrayList<>(), 0, k, target);
    }

    private void kSumHelper(List<Transaction> current, int start, int k, int target) {

        if (k == 0 && target == 0) {
            System.out.print("(");
            for (Transaction t : current) {
                System.out.print(t.id + " ");
            }
            System.out.println(")");
            return;
        }

        if (k == 0 || start >= transactions.size()) return;

        for (int i = start; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            current.add(t);
            kSumHelper(current, i + 1, k - 1, target - t.amount);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        Java_week1_ques9 system = new Java_week1_ques9();

        system.addTransaction(new Transaction(1, 500, "StoreA", 1000));
        system.addTransaction(new Transaction(2, 300, "StoreB", 1100));
        system.addTransaction(new Transaction(3, 200, "StoreC", 1200));
        system.addTransaction(new Transaction(4, 500, "StoreA", 1300));

        System.out.println("Two Sum (500):");
        system.findTwoSum(500);

        System.out.println("Two Sum with 1h window:");
        system.findTwoSumWithTimeWindow(500, 3600);

        System.out.println("Duplicate Detection:");
        system.detectDuplicates();

        System.out.println("K Sum (k=3 target=1000):");
        system.findKSum(3, 1000);
    }
}
