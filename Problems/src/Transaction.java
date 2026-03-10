import java.util.*;

class Transaction {

    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

public class TwoSumFraud {

    public List<int[]> findTwoSum(List<Transaction> list, int target) {

        Map<Integer, Transaction> map = new HashMap<>();

        List<int[]> result = new ArrayList<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                result.add(new int[]{
                        map.get(complement).id,
                        t.id
                });
            }

            map.put(t.amount, t);
        }

        return result;
    }
}