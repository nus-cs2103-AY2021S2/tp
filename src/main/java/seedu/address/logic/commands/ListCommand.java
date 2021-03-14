package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.attribute.Attribute;

import java.util.Optional;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    public static final String MESSAGE_SUCCESS_ATTRIBUTE = "Listed all clients and filtered specified attribute";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists clients, along with specified attributes\n"
            + "Parameters: -ATTRIBUTE (must be policy, phone or email)\n"
            + "Example: " + COMMAND_WORD + " -policy";

    private final Optional<Attribute> attribute;

    /**
     * @param attribute attribute that list will show
     */
    public ListCommand(Attribute attribute) {
        requireNonNull(attribute);
        this.attribute = Optional.of(attribute);
    }

    public ListCommand() {
        this.attribute = Optional.empty();
    }

    public boolean isAttributeSpecified() {
        return this.attribute.isPresent();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (this.attribute.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.updatePersonListByAttribute(this.attribute.get());
            return new CommandResult(MESSAGE_SUCCESS_ATTRIBUTE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && attribute.equals(((ListCommand) other).attribute)); // state check
    }
}
