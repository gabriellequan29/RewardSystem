import java.util.List;

public abstract class Rule {
    protected Transaction sportcheck;
    protected Transaction timhortons;
    protected Transaction subway;
    public abstract int calculate(List<Transaction> list);

    public void reset() {
        this.sportcheck = null;
        this.timhortons = null;
        this.subway = null;
    }

}
