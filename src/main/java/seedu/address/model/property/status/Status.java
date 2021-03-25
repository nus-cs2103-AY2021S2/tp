package seedu.address.model.property.status;

public interface Status {
    /**
     * @return the next status, representing the next stage of the property buying process.
     */
    Status next();

    /**
     * @return the amount offered
     */
    Offer getAmount();
}
