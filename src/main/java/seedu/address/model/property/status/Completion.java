package seedu.address.model.property.status;

public class Completion implements Status {
    public static final String TOSTRING_MESSAGE = "Completed sale at ";

    private final Offer amount;

    Completion(Offer amount) {
        this.amount = amount;
    }

    @Override
    public Completion next() {
        return this;
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
