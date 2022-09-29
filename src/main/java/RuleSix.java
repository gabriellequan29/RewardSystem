import java.util.List;

public class RuleSix extends Rule {
    int reward = 75;
    int point = 20;
    @Override
    public int calculate(List<Transaction> list) {
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
        int sportAmount = sportcheck == null ? 0 : sportcheck.getAmountCents();
        res += reward * (sportAmount / point);
        if (sportcheck != null) {
            sportcheck.setAmountCents(sportAmount % point);
        }
        return res;
    }
}
