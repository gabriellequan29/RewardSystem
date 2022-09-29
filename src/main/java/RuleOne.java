import java.util.List;

public class RuleOne extends Rule {
     int reward = 500;
     int p1 = 75;
     int p2 = 25;
     int p3 = 25;
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

         int sportAmount = sportcheck == null ? 0 : sportcheck.getAmountCents();
         int timsAmount = timhortons == null ? 0 : timhortons.getAmountCents();
         int subAmount = subway == null ? 0 : subway.getAmountCents();
         int res = 0;
         if (sportAmount >= p1 && timsAmount >= p2 && subAmount >= p3) {
             int count = Math.min(Math.min(sportAmount/p1, timsAmount/p2), subAmount/p3);
             res += count * reward;
             sportAmount -= p1 * count;
             timsAmount -= p2 * count;
             subAmount -= p3 * count;
         }

         if (sportcheck != null) {
             sportcheck.setAmountCents(sportAmount);
         }
         if (timhortons != null) {
             timhortons.setAmountCents(timsAmount);
         }
         if (subway != null) {
             subway.setAmountCents(subAmount);
         }
         return res;
     }
}
