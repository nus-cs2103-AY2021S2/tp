package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1d persons listed!";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE = "%1d persons listed with %s attribute%s!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose chosen field contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Use a flag (n/, p/, e/, a/, t/, i/) to search by name, phone, email, address, tags or insurance "
            + "policies respectively.\n"
            + "Use '&' to find for multiple search terms.\n"
            + "Specify attributes by typing '-[ATTRIBUTE] after keywords\n"
            + "Keywords cannot be empty.\n"
            + "Parameters: FLAG/KEYWORD [& MORE_KEYWORDS]... [-ATTRIBUTE]... (attributes must be policy, "
            + "phone or email)\n"
            + "Example: " + COMMAND_WORD + " e/alice@mail.com & bob@mail.com -address -policy";

    private final Predicate<Person> predicate;

    private final List<Attribute> attributes;

    /**
     * Create a FindCommand that lists persons that satisfy given {@code Predicate}
     * @param predicate by which to filter list
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
        this.attributes = new ArrayList<>();
    }

    /**
     * Create a FindCommand that lists {@code Attribute} of persons that satisfy given {@code Predicate}
     * @param predicate by which to filter list
     * @param attributes that find will show
     */
    public FindCommand(Predicate<Person> predicate, List<Attribute> attributes) {
        this.predicate = predicate;
        this.attributes = attributes;
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
        model.updateFilteredPersonList(predicate);
        if (!this.isAttributeSpecified()) {
            return new CommandResult(
                    String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        } else {
            model.updatePersonListByAttribute(this.attributes);
            String attributeName = getAttributesString();
            String attributeSuccessMessage = "";
            if (this.attributes.size() == 1) {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "");
            } else {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "s");
            }
            return new CommandResult(attributeSuccessMessage);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
