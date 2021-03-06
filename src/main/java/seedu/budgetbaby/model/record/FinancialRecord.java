package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetBaby.commons.util.AppUtil.checkArgument;

public class FinancialRecord {

    public final String description;
    public final Double amount;

    /**
     * Constructs a {@code FinancialRecord}.
     *
     * @param description A valid description.
     * @param amount      A valid amount.
     */
    public FinancialRecord(String description, Double amount) {
//        requireNonNull(tagName);
//        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.amount = amount;
    }
}
