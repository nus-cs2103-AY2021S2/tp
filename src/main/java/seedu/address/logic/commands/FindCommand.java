package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;
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

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1d client%s listed!";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE = "%1d client%s listed with %s attribute%s!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose chosen field contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Use a flag (n/, p/, e/, a/, t/, i/, m/) to search by name, phone, email, address, tags, insurance"
            + "policies or meetings respectively.\n"
            + "Use '&' to find for multiple search terms.\n"
            + "Specify attributes by typing '-[ATTRIBUTE] after keywords\n"
            + "Keywords cannot be empty.\n"
            + "Parameters: FLAG/KEYWORD [& MORE_KEYWORDS]... [-ATTRIBUTE]... (attributes must be i for policy, "
            + "p for phone, e for email, a for address or m for meeting)\n"
            + "Example: " + COMMAND_WORD + " e/alice@mail.com & bob@mail.com -a -i";

    private final Predicate<Person> predicate;

    private final Set<Attribute> attributes;

    /**
     * Create a FindCommand that lists persons that satisfy given {@code Predicate}
     * @param predicate by which to filter list
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
        this.attributes = new HashSet<>();
    }

    /**
     * Create a FindCommand that lists {@code Attribute} of persons that satisfy given {@code Predicate}
     * @param predicate by which to filter list
     * @param attributes that find will show
     */
    public FindCommand(Predicate<Person> predicate, Set<Attribute> attributes) {
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
            case MEETING:
                attributeName.append("meeting");
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

        int sizeOfFilteredList = model.getFilteredPersonList().size();

        if (!this.isAttributeSpecified()) {
            if (sizeOfFilteredList == 1) {
                return new CommandResult(
                        String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size(), ""));
            } else {
                return new CommandResult(
                        String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size(), "s"));
            }

        } else {
            model.updatePersonListByAttribute(this.attributes);
            String attributeName = getAttributesString();
            String attributeSuccessMessage;

            int numOfAttributes = this.attributes.size();

            if (numOfAttributes == 1 && sizeOfFilteredList == 1) {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "", "");
            } else if (numOfAttributes == 1 && sizeOfFilteredList > 1) {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "s", "");
            } else if (numOfAttributes > 1 && sizeOfFilteredList == 1) {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "", "s");
            } else {
                attributeSuccessMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTRIBUTE,
                        model.getFilteredPersonList().size(), attributeName, "s", "s");
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
