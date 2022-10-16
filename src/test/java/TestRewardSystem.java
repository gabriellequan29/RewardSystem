import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRewardSystem {

    private RewardSystem rewardSystem;
    private RuleFactory ruleFactory;

    @Before
    public void initialize()
    {
        rewardSystem = new RewardSystem();
        ruleFactory = new RuleFactory();
        rewardSystem.addRule(ruleFactory.createRule("Rule 1"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 2"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 4"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 6"));
        rewardSystem.addRule(ruleFactory.createRule("Rule 7"));

    }


    // Rule 4 + Rule 7
    @Test
    public void testSportcheck0() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7500}," +
                        "]}";
        String output = "T01-240" + "\n" + "Total-240";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 4 + Rule 7
    @Test
    public void testSportcheck1() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 9500}," +
                        "]}";
        String output = "T01-315" + "\n" + "Total-315";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 4
    @Test
    public void testSportcheck2() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 2022}," +
                        "]}";
        String output = "T01-75" + "\n" + "Total-75";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 7
    @Test
    public void testSportcheck3() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 1825}," +
                        "]}";
        String output = "T01-18" + "\n" + "Total-18";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 7
    @Test
    public void testTims() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 1523}," +
                        "]}";
        String output = "T01-15" + "\n" + "Total-15";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 7
    @Test
    public void testSubway() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"subway\", \"amount_cents\": 1523}," +
                        "]}";
        String output = "T01-15" + "\n" + "Total-15";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 7
    @Test
    public void testOther() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"walmart\", \"amount_cents\": 2523}," +
                        "]}";
        String output = "T01-25" + "\n" + "Total-25";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 1
    @Test
    public void testThreeCombine0() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7521}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2523}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": 2523},]}";
        String output = "T01-240" + "\n" + "T02-25" + "\n" + "T03-25" + "\n" + "Total-500";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 1 + Rule 7
    @Test
    public void testThreeCombine1() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7521}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2523}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": 2523}," +
                        "{\"id\": \"T04\",\"date\": \"2021-05-02\", \"merchant_code\" : \"walmart\", \"amount_cents\": 523}]}";
        String output = "T01-240" + "\n" + "T02-25" + "\n" + "T03-25" + "\n" + "T04-5" + "\n" + "Total-505";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 1 + Rule 7
    @Test
    public void testThreeCombine2() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 3526}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 3974}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": 2523}," +
                        "{\"id\": \"T04\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2923},]}";
        String output = "T01-90" + "\n" + "T02-94" + "\n" + "T03-25" + "\n" + "T04-29" + "\n" + "Total-504";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 1 + Rule 4
    @Test
    public void testThreeCombine3() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 10023}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 3589}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": 3523}]}";
        String output = "T01-375" + "\n" + "T02-35" + "\n" + "T03-35" + "\n" + "Total-650";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    // Rule 2
    @Test
    public void testTwoCombine() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7521}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2523},]}";
        String output = "T01-240" + "\n" + "T02-25" + "\n" + "Total-300";
        assertEquals(rewardSystem.getReward(transactions), output);
    }
    @Test
    public void testAll() {
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
        String output = "T01-760" + "\n" + "T02-307" + "\n" + "T03-3" + "\n" + "T04-12" + "\n" + "T05-21" + "\n" + "T06-22" +
                "\n" + "T07-18" + "\n" + "T08-21" + "\n" + "T09-238" + "\n" + "T10-13" + "\n" + "Total-1657";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    @Test
    public void testEdge0() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 0}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": -1}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": -5},]}";
        String output = "T01-0" + "\n" + "T02-0" + "\n" + "T03-0" + "\n" + "Total-0";
        assertEquals(rewardSystem.getReward(transactions), output);
    }

    @Test
    public void testEdge1() {
        String transactions =
                "{\"transactions\" : [" +
                        "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 0}," +
                        "{\"id\": \"T02\",\"date\": \"2021-05-02\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": -1}," +
                        "{\"id\": \"T03\",\"date\": \"2021-05-02\", \"merchant_code\" : \"subway\", \"amount_cents\": -5},]}";
        String output = "T01-0" + "\n" + "T02-0" + "\n" + "T03-0" + "\n" + "Total-0";
        assertEquals(rewardSystem.getReward(transactions), output);
    }




}
