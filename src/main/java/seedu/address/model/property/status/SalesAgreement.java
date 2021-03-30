package seedu.address.model.property.status;

public class SalesAgreement implements Status {
    public static final String TOSTRING_MESSAGE = "Sales and Purchase Agreement at ";

    private final Offer amount;

    SalesAgreement(Offer amount) {
        this.amount = amount;
    }

    @Override
    public Completion next() {
        return new Completion(amount);
    }

    @Override
    public Offer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return TOSTRING_MESSAGE + amount.toString();
    }
}
