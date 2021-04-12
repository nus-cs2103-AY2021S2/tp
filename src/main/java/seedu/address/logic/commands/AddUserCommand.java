package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * BMI initialization command
 */
public class AddUserCommand extends Command {

    public static final String COMMAND_WORD = "bmi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the user's gender, age, height, weight"
            + " and ideal weight.\nParameters: g/gender a/age h/height(cm) w/weight(kg) "
            + "i/ideal_weight";

    public static final String MESSAGE_SUCCESS = "Success in updating user information";

    public static final String MESSAGE_UPDATE = "Unsuccessful! Your particulars have already been set.\n"
            + "Please update your particulars "
            + "using the bmi update command."
            + "\nIf you have not set your particulars before, maybe you have not cleared\n"
            + "the sample data yet? Simply type \n\n"
            + "reset t/ blank\n\nto clear the sample data!";

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

        if (model.hasUser()) {
            return new CommandResult(MESSAGE_UPDATE);
        }

        model.addUser(temporaryUser);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
