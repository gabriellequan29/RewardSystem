import java.util.List;

public class RuleFive extends Rule {
    int reward = 75;
    int p1 = 25;
    int p2 = 10;
    @Override
    public int calculate(List<Transaction> list) {
        // get transaction
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    sportcheck = t;
                    break;
                case Constants.TIM_HORTONS:
                    timhortons = t;
                    break;
                case Constants.SUBWAY:
                    subway = t;
                    break;
                default:
            }
        }
        // get amount
        int sportAmount = sportcheck == null ? 0 : sportcheck.getAmountCents();
        int timsAmount = timhortons == null ? 0 : timhortons.getAmountCents();
        int res = 0;
        // apply rule 5
        if (sportAmount >= p1 && timsAmount >= p2) {
            int count = Math.min(sportAmount / p1, timsAmount / p2);
            res += count * reward;
            sportAmount -= p1 * count;
            timsAmount -= p2 * count;
        }
        // update amount
        if (sportcheck != null) {
            sportcheck.setAmountCents(sportAmount);
        }
        if (timhortons != null) {
            timhortons.setAmountCents(timsAmount);
        }
        reset();
        return res;
    }
}
