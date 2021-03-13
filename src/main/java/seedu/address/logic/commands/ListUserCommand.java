package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * BMI query command
 */
public class ListUserCommand extends Command {
    public static final String COMMAND_WORD = "bmi_query";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the user's gender, age, height, weight"
            + " and ideal weight.\nParameters: g/gender a/age h/height(cm) w/weight(kg) "
            + "i/ideal_weight";

    /**
     * Creates a User command to update the user object.
     */
    public ListUserCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String details = model.listUser();
        return new CommandResult(details);
    }
}
