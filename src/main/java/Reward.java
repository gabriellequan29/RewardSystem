import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reward {
    public static void main(String[] args) {
        String transactions =
                "{\"transactions\" : [" +
                "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 21000}," +
                "{\"id\": \"T02\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 8700}," +
                "{\"id\": \"T03\",\"date\": \"2021-05-03\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 323}," +
                "{\"id\": \"T04\",\"date\": \"2021-05-04\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 1267}," +
                "{\"id\": \"T05\",\"date\": \"2021-05-05\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2116}," +
                "{\"id\": \"T06\",\"date\": \"2021-05-06\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2211}," +
                "{\"id\": \"T07\",\"date\": \"2021-05-07\", \"merchant_code\" : \"subway\", \"amount_cents\": 1853}," +
                "{\"id\": \"T08\",\"date\": \"2021-05-08\", \"merchant_code\" : \"subway\", \"amount_cents\": 2153}," +
                "{\"id\": \"T09\",\"date\": \"2021-05-09\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7326}," +
                "{\"id\": \"T10\",\"date\": \"2021-05-10\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 1321}" +
                        "]}";
        String output = getReward(transactions);
        System.out.println(output);
    }

    private static Map<String, Integer> amounts;
    private static final String MERCHANT = "merchant_code";
    private static final String AMOUNT = "amount_cents";
    private static final String TRANSACTIONS = "transactions";
    public static String getReward(String transactions) {
        // merchant_code -> total amount_cent
        amounts = new HashMap<>();
        // parse input string
        JSONObject obj = new JSONObject(transactions);
        JSONArray arr = obj.getJSONArray(TRANSACTIONS);
        StringBuilder output = new StringBuilder();
        // append different rules according to priority
        List<Rule> rules = new ArrayList<>();
        rules.add(new RuleOne());
        rules.add(new RuleTwo());
        rules.add(new RuleFour());
        rules.add(new RuleSix());
        rules.add(new RuleSeven());
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
