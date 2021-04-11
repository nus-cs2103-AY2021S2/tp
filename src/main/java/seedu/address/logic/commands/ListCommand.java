package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GARMENTS;

import seedu.address.model.Model;

/**
 * Lists all garments in the wardrobe to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all garments in wardrobe\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all garments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGarmentList(PREDICATE_SHOW_ALL_GARMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
