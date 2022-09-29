import java.util.List;

public class RuleSeven extends Rule {
    int reward = 1;
    int point = 1;
    int sportCheck = 0;

    @Override
    public int calculate(List<Transaction> list) {
        int res = 0;
        for (Transaction t : list) {
            res += reward * (t.getAmountCents() / point);
            t.setAmountCents(0);
        }
        return res;
    }
}
