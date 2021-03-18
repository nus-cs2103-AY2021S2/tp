package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.Attribute;


/**
 * Lists all persons in the address book to the user. Attribute can be specified to show only specific attributes.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    public static final String MESSAGE_SUCCESS_ATTRIBUTE = "Listed all clients with %s attribute as filter";

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!this.isAttributeSpecified()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.updatePersonListByAttribute(this.attribute.get());
            String attributeName = "";
            switch (this.attribute.get()) {
            case EMAIL:
                attributeName = "email";
                break;
            case PHONE:
                attributeName = "phone number";
                break;
            case POLICY_ID:
                attributeName = "policy Ids";
                break;
            case ADDRESS:
                attributeName = "address";
                break;
            default:
                throw new CommandException("Could not list with filtered attribute");
            }
            String attributeSuccessMessage = String.format(MESSAGE_SUCCESS_ATTRIBUTE,
                    attributeName);
            return new CommandResult(attributeSuccessMessage);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && attribute.equals(((ListCommand) other).attribute)); // state check
    }
}
