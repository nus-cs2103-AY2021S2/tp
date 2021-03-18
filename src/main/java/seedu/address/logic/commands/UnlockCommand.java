package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Authentication;

public class UnlockCommand extends Command {

    public static final String COMMAND_WORD = "unlock";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unlocks ClientBook.\n"
            + "Parameters: CURRENT_PASSWORD\n"
            + "Example: " + COMMAND_WORD + " 12345";

    public static final String MESSAGE_UNLOCK_SUCCESS = "Unlocked ClientBook.";

    public static final String MESSAGE_ALREADY_UNLOCKED = "ClientBook is not locked.";

    public static final String MESSAGE_INCORRECT_PASSWORD = "You have entered the wrong password.";

    private final String currentPassword;

    /**
     * Instantiates an UnlockCommand object with the password that the user has entered.
     * @param currentPassword user entered password used to unlock the zip file.
     */
    public UnlockCommand(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Authentication authentication = model.getAuthentication();
        if (!authentication.isPasswordPresent()) {
            throw new CommandException(MESSAGE_ALREADY_UNLOCKED);
        }
        if (!authentication.getPassword().equals(currentPassword)) {
            throw new CommandException(MESSAGE_INCORRECT_PASSWORD);
        }
        authentication.removePassword();
        return new CommandResult(MESSAGE_UNLOCK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnlockCommand // instanceof handles nulls
                && currentPassword.equals(((UnlockCommand) other).currentPassword)); // state check
    }
}
