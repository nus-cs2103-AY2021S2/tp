package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;

/**
 * Clears the delivery list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TimeForWheels' entries has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDeliveryList(new DeliveryList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
