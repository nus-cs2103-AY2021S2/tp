package seedu.address.model.property.status;

public class Option implements Status {
    public static final String TOSTRING_MESSAGE = "Option to Purchase at ";

    private final Offer amount;

    public Option(Offer amount) {
        this.amount = amount;
    }

    @Override
    public SalesAgreement next() {
        return new SalesAgreement(amount);
    }

    @Override
    public String toString() {
        return TOSTRING_MESSAGE + amount.toString();
    }
}
