package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_FAVOURITES;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String OPTION_FAV = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists persons in the address book.\n"
            + "Parameters: [" + PREFIX_OPTION + "OPTION]\n"
            + "Options: " + OPTION_FAV + " (to show favourites)\n"
            + "Example: " + COMMAND_WORD + " o/" + OPTION_FAV + " \n";
    public static final String MESSAGE_SUCCESS = "Listed all persons";
    public static final String MESSAGE_LIST_FAV_SUCCESS = "Listed all favourited persons";

    private String option;

    public ListCommand() {}

    public ListCommand(String option) {
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (option == null) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (option.equals(OPTION_FAV)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_FAVOURITES);
            return new CommandResult(MESSAGE_LIST_FAV_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_USAGE);
        }
    }
}
