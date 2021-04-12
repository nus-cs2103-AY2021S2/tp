package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all rooms in SunRez to the user.
 */
public class ListRoomCommand extends Command {

    public static final String COMMAND_WORD = "olist";

    public static final String MESSAGE_SUCCESS = "Listed all rooms";
    private static final Logger logger = LogsCenter.getLogger(ListRoomCommand.class);


    /**
     * Executes the ListRoomCommand on the provided {@code Model} list all the rooms.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the number of {@code Room} objects that will be listed.
     * @throws NullPointerException If {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        logger.info("ListRoomCommand successfully updated the model");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
