package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Authentication;

public class LockCommand extends Command {

    public static final String COMMAND_WORD = "lock";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Locks ClientBook with a password.\n"
            + "Parameters: CURRENT_PASSWORD(if any) NEW_PASSWORD\n"
            + "Example: " + COMMAND_WORD + " 12345";

    public static final String MESSAGE_LOCK_SUCCESS = "Locked ClientBook.";

    public static final String MESSAGE_INCORRECT_PASSWORD = "You have entered the wrong password.";

    private final Optional<String> currentPassword;
    private final String newPassword;

    /**
     * Instantiates a LockCommand object with the new password.
     * @param newPassword password used to lock the addressbook.json file into a zip file.
     */
    public LockCommand(String newPassword) {
        this.currentPassword = Optional.empty();
        this.newPassword = newPassword;
    }

    /**
     * Instantiates a LockCommand object with the current password and new password.
     * @param currentPassword current password that is used to lock the zip file containing addressbook.json.
     * @param newPassword password used to lock the addressbook.json file into a zip file.
     */
    public LockCommand(String currentPassword, String newPassword) {
        this.currentPassword = Optional.of(currentPassword);
        this.newPassword = newPassword;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Authentication authentication = model.getAuthentication();

        //There is an existing password
        if (authentication.isPasswordPresent()) {
            //Verify that the current password entered by user is the same as the existing password.
            if (!this.currentPassword.isPresent() || !authentication.getPassword().equals(this.currentPassword.get())) {
                throw new CommandException(MESSAGE_INCORRECT_PASSWORD);
            }
        }
        authentication.setPassword(this.newPassword);
        return new CommandResult(MESSAGE_LOCK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LockCommand // instanceof handles nulls
                && currentPassword.equals(((LockCommand) other).currentPassword)
                && newPassword.equals(((LockCommand) other).newPassword)); // state check
    }
}
