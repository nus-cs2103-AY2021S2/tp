package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

public class Description {
    public final String value;

    public Description(String desc) {
        requireNonNull(desc);
        //checkArguments
        value = desc;
    }

    public static boolean isValidDescription() {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Description && value.equals(((Description) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
