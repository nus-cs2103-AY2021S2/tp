package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_FAVOURITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_SHOW_FAVOURITES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all contacts in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists contacts in the address book.\n"
            + "Parameters: [" + PREFIX_OPTION + "OPTION]\n"
            + "Options: " + OPTION_FAVOURITE + " (to show favourites)\n"
            + "Example: " + COMMAND_WORD + " o/" + OPTION_FAVOURITE + " \n";
    public static final String MESSAGE_SUCCESS = "Listed all contacts";
    public static final String MESSAGE_LIST_FAV_SUCCESS = "Listed all favourited contacts";

    private String option;

    public ListCommand() {}

    public ListCommand(String option) {
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (option == null) {
            model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (option.equals(OPTION_FAVOURITE)) {
            model.updateFilteredContactList(PREDICATE_SHOW_FAVOURITES);
            return new CommandResult(MESSAGE_LIST_FAV_SUCCESS);
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    @Override
    public boolean equals(Object other) {
        // state check
        // short circuit if same object
        return (other instanceof ListCommand); // instanceof handles nulls
    }
}
