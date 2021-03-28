package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Option {
    private final String option;

    public Option(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String toString() {
        return getOption();
    }

    @Override
    public int hashCode() {
        return option == null ? 0 : option.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Option)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Option otherOption = (Option) obj;
        return otherOption.getOption().equals(getOption());
    }
}
