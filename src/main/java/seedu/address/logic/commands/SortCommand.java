package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CRITERIA;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

enum Criteria {
    NAME("name"), PHONENUMBER("phone");
    private String criteria;

    private Criteria(String criteria) {
        this.criteria = criteria;
    }
}
/**
 * Sorts all persons in the address book by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the entries in the contact list according to a specific criteria.\n"
            + "Parameters: " + PREFIX_SORT_CRITERIA + "CRITERIA "
            + PREFIX_DIRECTION + "ASCENDING_OR_DESCENDING \n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_SORT_CRITERIA + "name "
            + PREFIX_DIRECTION + "ascending";

    public static final String MESSAGE_SUCCESS = "Successfully sorted the list";

    public static final String CRITERIA_MESSAGE_CONSTRAINTS = "Criteria should be one of the following: name, phone";

    private final Criteria criteria;
    private final boolean isAscending;

    /**
     * Creates a sort command to sort the list by a specified criteria.
     */
    public SortCommand(String criteria, boolean isAscending) throws ParseException {
        requireAllNonNull(criteria, isAscending);
        this.criteria = convertStringToCriteria(criteria);
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (criteria.equals(Criteria.NAME)) {
            model.sortByName(isAscending);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private static Criteria convertStringToCriteria(String criteria) throws ParseException {
        if (!(criteria.equals("name") || (criteria.equals("phone")))) {
            throw new ParseException(CRITERIA_MESSAGE_CONSTRAINTS);
        }
        if (criteria.equals("name")) {
            return Criteria.NAME;
        } else {
            return Criteria.PHONENUMBER;
        }
    }
}
