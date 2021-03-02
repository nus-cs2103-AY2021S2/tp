package seedu.dictionote.logic.parser;

/**
 * A Option (also known as a flag or switch) modifies the operation of a command.
 * E.g. '-c' in 'open -c'.
 */
public class Option {
    private final String option;

    public Option(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

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

        Option otherPrefix = (Option) obj;
        return otherPrefix.getOption().equals(getOption());
    }
}
