package seedu.address.model.property.status;

public interface Status {
    /**
     *
     * @return the next status.
     */
    Status next();

    /**
     *
     * @return the amount offered
     */
    Offer getAmount();
}
