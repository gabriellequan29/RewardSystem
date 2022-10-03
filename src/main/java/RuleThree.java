import java.util.List;

public class RuleThree extends Rule {
    int reward = 200;
    int point = 75;
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
        int res = 0;
        // get amount
        int sportAmount = sportcheck == null ? 0 : sportcheck.getAmountCents();
        // apply rule 3
        res += reward * (sportAmount / point);
        // update amount
        if (sportcheck != null) {
            sportcheck.setAmountCents(sportAmount % point);
        }
        reset();
        return res;
    }
}
