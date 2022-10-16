import java.util.List;

public class RuleThree extends Rule {
    int reward = 200;
    int point = 75;
    @Override
    public int calculate(List<Transaction> list) {
        int sportAmount = 0;
        int res = 0;
        // get transaction
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    sportAmount = t.getAmountCents();
                    break;
                default:
            }
        }

        // apply rule 3
        res += reward * (sportAmount / point);
        // update amount
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    t.setAmountCents(sportAmount);
                    break;
                default:
            }
        }
        return res;
    }
}
