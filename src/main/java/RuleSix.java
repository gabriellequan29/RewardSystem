import java.util.List;

public class RuleSix extends Rule {
    int reward = 75;
    int point = 20;
    @Override
    public int calculate(List<Transaction> list) {
        int sportAmount = 0;
        int res = 0;

        // get amount
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    sportAmount = t.getAmountCents();
                    break;
                default:
            }
        }

        // apply rule 6
        int count = sportAmount / point;
        res += reward * count;
        sportAmount -= point * count;

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
