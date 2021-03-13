package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * BMI update command
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "bmi_update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the user's gender, age, height, weight"
            + " and ideal weight.\nParameters: g/gender a/age h/height(cm) w/weight(kg) "
            + "i/ideal_weight";

    public static final String MESSAGE_SUCCESS = "Success in updating user information";

    private final User temporaryUser;

    /**
     * Creates a User command to update the user object.
     */
    public EditUserCommand(User user) {
        temporaryUser = user;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.editUser(temporaryUser);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
