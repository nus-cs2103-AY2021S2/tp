package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * BMI initialization command
 */
import static java.util.Objects.requireNonNull;

public class AddUserCommand extends Command {

    public static final String COMMAND_WORD = "bmi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the user's gender, age, height, weight"
            + " and ideal weight.\nParameters: g/gender a/age h/height(cm) w/weight(kg) "
            + "i/ideal_weight";

    public static final String MESSAGE_SUCCESS = "Success in updating user information";

    public static final String MESSAGE_UPDATE = "Unsuccessful! Please update your particulars "
            + "using the bmi update command.";

    private final User temporaryUser;

    /**
     * Creates a User command to create the user object.
     */
    public AddUserCommand(User user) {
        temporaryUser = user;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //TODO check if user object already exists
        if (model.hasUser()) {
            return new CommandResult(MESSAGE_UPDATE);
        }

        model.addUser(temporaryUser);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
