package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Remark {
    private static final String MESSAGE_CONSTRAINTS = "Remarks can take any value, but it should not be blank";

    private static final String VALIDATION_REGEX = "[^\\s].*";

    public final String remark;

    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidName(remark), MESSAGE_CONSTRAINTS);
        this.remark = remark;
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Remark)) {
            return false;
        }
        Remark otherRemark = (Remark) other;
        return remark.equals(otherRemark.remark);
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
