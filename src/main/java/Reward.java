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
        RewardSystem rewardSystem = new RewardSystem();
        RuleFactory ruleFactory = new RuleFactory();
        rewardSystem.addRule(ruleFactory.createRule("Rule 1"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 2"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 4"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 6"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 7"));
        String output = rewardSystem.getReward(transactions);
        System.out.println(output);
    }
}
