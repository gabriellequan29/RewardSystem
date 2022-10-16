public class RuleFactory {
    public static Rule createRule(String rule) {
        switch (rule) {
            case "Rule 1":
                return new RuleOne();
            case "Rule 2" :
                return new RuleTwo();
            case "Rule 3":
                return new RuleThree();
            case "Rule 4":
                return new RuleFour();
            case "Rule 5":
                return new RuleFive();
            case "Rule 6":
                return new RuleSix();
            case "Rule 7":
                return new RuleSeven();
            default:
                return null;
        }
    }
}
