package seedu.address.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.AddressBookParser;

/**
 * Represents a Command in the address book.
 * Guarantees: immutable; command to alias is valid as declared in {@link #isValidCommand(String)}
 */
public class Command {

    public static final String MESSAGE_CONSTRAINTS =
            "Command to alias should be a valid existing command, and it should not be blank";

    public final String command;

    /**
     * Constructs a {@code Command}.
     *
     * @param command A valid command word.
     */
    public Command(String command) {
        requireNonNull(command);
        checkArgument(isValidCommand(command), MESSAGE_CONSTRAINTS);
        this.command = command;
    }

    /**
     * Returns true if a given string is a valid command word.
     */
    public static boolean isValidCommand(String test) {
        return new AddressBookParser().isValidCommandToAlias(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Command // instanceof handles nulls
                && command.equals(((Command) other).command)); // state check
    }

    @Override
    public int hashCode() {
        return command.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return command;
    }

}
