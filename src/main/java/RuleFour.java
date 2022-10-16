import java.util.List;

public class RuleFour extends Rule{
    int reward = 150;
    int p1 = 25;
    int p2 = 10;
    int p3 = 10;
    @Override
    public int calculate(List<Transaction> list) {

        int sportAmount = 0;
        int timsAmount = 0;
        int subAmount = 0;
        int res = 0;

        // get amount
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    sportAmount = t.getAmountCents();
                    break;
                case Constants.TIM_HORTONS:
                    timsAmount = t.getAmountCents();
                    break;
                case Constants.SUBWAY:
                    subAmount = t.getAmountCents();
                    break;
                default:
            }
        }

        // apply rule 4
        if (sportAmount >= p1 && timsAmount >= p2 && subAmount >= p3) {
            int count = Math.min(Math.min(sportAmount/p1, timsAmount/p2), subAmount/p3);
            res += count * reward;
            sportAmount -= p1 * count;
            timsAmount -= p2 * count;
            subAmount -= p3 * count;
        }

        // update amount
        for (Transaction t : list) {
            switch (t.getMerchantCode()) {
                case Constants.SPORT_CHECK:
                    t.setAmountCents(sportAmount);
                    break;
                case Constants.TIM_HORTONS:
                    t.setAmountCents(timsAmount);
                    break;
                case Constants.SUBWAY:
                    t.setAmountCents(subAmount);
                    break;
                default:
            }
        }
        return res;
    }
}
