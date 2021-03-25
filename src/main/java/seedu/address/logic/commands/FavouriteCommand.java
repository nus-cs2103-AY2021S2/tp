package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the person at the index in the address book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";
    public static final String MESSAGE_FAV_IN_PROGRESS = "Favourite command is still being implemented.";
    public static final String MESSAGE_FAV_PERSON_SUCCESS = "Favourited Person: %1$s";

//    private final Index index;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_FAV_IN_PROGRESS);
    }
}
