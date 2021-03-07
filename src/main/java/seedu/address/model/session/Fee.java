package seedu.address.model.session;

import static java.util.Objects.requireNonNull;

import seedu.address.model.session.exceptions.SessionException;

/**
 * Represents a Session's fee per hour
 * Guarantees: immutable;
 */
public class Fee {

    private static final String INCORRECT_FEE_FORMAT_ERROR_MESSAGE = "Format of fee input is incorrect: ";
    private double fee;

    /**
     * Constructs a {@code Fee}.
     *
     * @param value The fee per hour for that specified session
     */
    public Fee(String value) throws SessionException {
        try {
            requireNonNull(value);
            double fullFee = Double.parseDouble(value);
            this.fee = fullFee;
        } catch (NumberFormatException e) {
            throw new SessionException(INCORRECT_FEE_FORMAT_ERROR_MESSAGE + e, e);
        }
    }
}
