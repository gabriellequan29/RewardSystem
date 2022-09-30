public class Transaction {
    private String merchantCode;
    private int amountCents;

    public Transaction(String merchantCode, int amountCents) {
        this.merchantCode = merchantCode;
        this.amountCents = amountCents / 100;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(int amountCents) {
        this.amountCents = amountCents;
    }
}
