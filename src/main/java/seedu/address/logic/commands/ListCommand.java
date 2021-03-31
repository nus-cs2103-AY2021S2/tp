package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.Attribute;


/**
 * Lists all persons in the address book to the user. Attribute can be specified to show only specific attributes.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    public static final String MESSAGE_SUCCESS_ATTRIBUTE = "Listed all clients with %s attribute%s as filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists clients, along with specified attributes\n"
            + "Parameters: [-ATTRIBUTE]... (attributes must be policy, phone or email)\n"
            + "Example: " + COMMAND_WORD + " -policy -phone";

    private final List<Attribute> attributes;

    /**
     * @param attributes that list will show
     */
    public ListCommand(List<Attribute> attributes) {
        requireNonNull(attributes);
        this.attributes = attributes;
    }

    public ListCommand() {
        this.attributes = new ArrayList<>();
    }

    public boolean isAttributeSpecified() {
        return !this.attributes.isEmpty();
    }

    private String getAttributesString() throws CommandException {
        StringBuilder attributeName = new StringBuilder();
        boolean isFirstAttribute = true;
        for (Attribute attribute : this.attributes) {
            if (isFirstAttribute) {
                isFirstAttribute = false;
            } else {
                attributeName.append(", ");
            }
            switch (attribute) {
            case EMAIL:
                attributeName.append("email");
                break;
            case PHONE:
                attributeName.append("phone number");
                break;
            case POLICY_ID:
                attributeName.append("policy ID");
                break;
            case ADDRESS:
                attributeName.append("address");
                break;
            default:
                throw new CommandException("Could not list with filtered attribute");
            }
        }
        return attributeName.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (!this.isAttributeSpecified()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.updatePersonListByAttribute(this.attributes);
            String attributeName = getAttributesString();
            String attributeSuccessMessage = "";
            if (this.attributes.size() == 1) {
                attributeSuccessMessage = String.format(MESSAGE_SUCCESS_ATTRIBUTE, attributeName, "");
            } else {
                attributeSuccessMessage = String.format(MESSAGE_SUCCESS_ATTRIBUTE, attributeName, "s");
            }
            return new CommandResult(attributeSuccessMessage);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && this.attributes.equals(((ListCommand) other).attributes)); // state check
    }
}
