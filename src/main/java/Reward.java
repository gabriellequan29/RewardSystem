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
        getReward(transactions);
    }

    private static Map<String, Integer> amounts;
    private static final String MERCHANT = "merchant_code";
    private static final String AMOUNT = "amount_cents";
    public static String getReward(String transactions) {
        amounts = new HashMap<>();
        JSONObject obj = new JSONObject(transactions);
        JSONArray arr = obj.getJSONArray("transactions");
        StringBuilder sb = new StringBuilder();
        Rule rule1 = new RuleOne();
        Rule rule2 = new RuleTwo();
        Rule rule4 = new RuleFour();
        Rule rule6 = new RuleSix();
        Rule rule7 = new RuleSeven();
        for (int i = 0; i < arr.length(); i++)
        {
            JSONObject transaction = arr.getJSONObject(i);
            String id = transaction.getString("id");
            String merchant_code = transaction.getString(MERCHANT);
            int amount_cents = transaction.getInt(AMOUNT);
            amounts.put(merchant_code, amounts.getOrDefault(merchant_code, 0) + amount_cents);
            int points = 0;
            List<Transaction> list = new ArrayList<>();
            Transaction t = new Transaction(merchant_code, amount_cents);
            list.add(t);
            points += rule6.calculate(list);
            points += rule7.calculate(list);
            sb.append(id).append("-").append(points).append("\n");
        }
        List<Transaction> list = new ArrayList<>();
        for (String key : amounts.keySet()) {
            Transaction t = new Transaction(key, amounts.get(key));
            list.add(t);
        }
        int total = 0;
        total += rule1.calculate(list);
        total += rule2.calculate(list);
        total += rule4.calculate(list);
        total += rule6.calculate(list);
        total += rule7.calculate(list);
        sb.append("Total-").append(total);
        System.out.println(sb);
        return sb.toString();
    }

    public static int calEach(String merchant_code, int amount_cents) {
        int res = 0;
        amount_cents = amount_cents / 100;
        switch (merchant_code) {
            case "sportcheck":
                while (amount_cents > 0) {
                    // Rule 6
                    if (amount_cents >= 20) {
                        res += (amount_cents / 20) * 75;
                        amount_cents = amount_cents % 20;
                    }
                    // Rule 7
                    else {
                        res += amount_cents;
                        amount_cents = 0;
                    }
                }
                return res;
            case "tim_hortons":
            case "subway":
                // Rule 7
                res += amount_cents;
                return res;
        }
        return res;
    }

    public static int calTotal(int sport, int tims, int subway) {
        sport = sport / 100; tims = tims / 100; subway = subway / 100;
        int res = 0;
        while (sport > 0 || tims > 0 || subway > 0) {
            if (sport >= 75 && tims >= 25 && subway >= 25) {
                int count = Math.min(Math.min(sport/75, tims/25), subway/25);
                res += count * 500;
                sport -= 75 * count;
                tims -= 25 * count;
                subway -= 25 * count;
            }
            else if (sport >= 75 && tims >= 25) {
                int count = Math.min(sport/75, tims/25);
                res += count * 300;
                sport -= 75 * count;
                tims -= 25 * count;
            }
            else if (sport >= 25 && tims >= 10 && subway >= 10) {
                int count = Math.min(Math.min(sport/25, tims/10), subway/10);
                res += count * 150;
                sport -= 25 * count;
                tims -= 10 * count;
                subway -= 10 * count;
            }
            else if (sport >= 20) {
                res += 75 * (sport / 20);
                sport = sport % 20;
            }
            else {
                res += sport + tims + subway;
                sport = 0;
                tims = 0;
                subway = 0;
            }
        }
        return res;
    }
}
