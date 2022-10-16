import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardSystem {

    private static Map<String, Integer> amounts;
    private static final String MERCHANT = "merchant_code";
    private static final String AMOUNT = "amount_cents";
    private static final String TRANSACTIONS = "transactions";
    private List<Rule> rules;
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public RewardSystem() {
        this.amounts = new HashMap<>();
        this.rules = new ArrayList<>();
    }

    public String getReward(String transactions) {
        // merchant_code -> total amount_cent
        amounts = new HashMap<>();
        // parse input string
        JSONObject obj = new JSONObject(transactions);
        JSONArray arr = obj.getJSONArray(TRANSACTIONS);
        StringBuilder output = new StringBuilder();
        // calculate max reward points for each transaction
        for (int i = 0; i < arr.length(); i++)
        {
            // parse the transaction
            JSONObject transaction = arr.getJSONObject(i);
            String id = transaction.getString("id");
            String merchant_code = transaction.getString(MERCHANT);
            int amount_cents = transaction.getInt(AMOUNT);
            // update total_amount_cents for merchant_code
            amounts.put(merchant_code, amounts.getOrDefault(merchant_code, 0) + amount_cents);
            long points = 0;
            List<Transaction> list = new ArrayList<>();
            Transaction t = new Transaction(merchant_code, amount_cents);
            list.add(t);
            // apply rules to each transaction according to priority
            for (Rule rule : rules) {
                points += rule.calculate(list);
            }
            // append the result to the output
            output.append(id).append("-").append(points).append("\n");
        }
        // calculate total max reward points
        List<Transaction> list = new ArrayList<>();
        for (String key : amounts.keySet()) {
            Transaction t = new Transaction(key, amounts.get(key));
            list.add(t);
        }
        long total = 0;
        for (Rule rule : rules) {
            total += rule.calculate(list);
        }
        output.append("Total-").append(total);
        return output.toString();
    }
}
