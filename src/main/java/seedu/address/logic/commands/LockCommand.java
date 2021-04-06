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

    public static final String MESSAGE_LOCK_SUCCESS_NEW_PASSWORD = "Locked ClientBook using new password.";

    public static final String MESSAGE_LOCK_SUCCESS_OLD_PASSWORD = "Locked ClientBook using your previous password.";

    public static final String MESSAGE_LOCK_SUCCESS_UPDATE_PASSWORD = "Successfully updated the password.";

    public static final String MESSAGE_ALREADY_LOCKED_INCORRECT_PASSWORD = "ClientBook is already locked, please "
            + "enter the current password\nand a new password to change the password.";

    public static final String MESSAGE_FAIL_TO_READ_PASSWORD_FILE = "Failed to read password file, please enter "
            + "a new password to lock ClientBook.";

    public static final String MESSAGE_FAILED_TO_STORE_PASSWORD = "Failed to store password file.";


    private final Optional<String> currentPassword;
    private final Optional<String> newPassword;

    /**
     * Instantiates a LockCommand object with the new password.
     * @param newPassword password used to lock the clientbook.json file into a zip file.
     */
    public LockCommand(String newPassword) {
        this.currentPassword = Optional.empty();
        this.newPassword = Optional.of(newPassword);
    }

    /**
     * Instantiates a LockCommand object with the current password and new password.
     * @param currentPassword current password that is used to lock the zip file containing clientbook.json.
     * @param newPassword password used to lock the clientbook.json file into a zip file.
     */
    public LockCommand(String currentPassword, String newPassword) {
        this.currentPassword = Optional.of(currentPassword);
        this.newPassword = Optional.of(newPassword);
    }

    /**
     * Instantiates a LockCommand object with no passwords. The LockCommand will check for any previously used
     * password in the form of a password file.
     */
    public LockCommand() {
        this.currentPassword = Optional.empty();
        this.newPassword = Optional.empty();
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Authentication authentication = model.getAuthentication();

        //There is an existing password
        if (authentication.isPasswordPresent()) {
            //Verify that the current password entered by user is the same as the existing password.
            if (this.currentPassword.isEmpty() || !authentication.getPassword().equals(this.currentPassword.get())) {
                throw new CommandException(MESSAGE_ALREADY_LOCKED_INCORRECT_PASSWORD);
            }
        }
        //If newPassword is not entered, check for password in password file.
        if (this.newPassword.isEmpty()) {
            useOldPassword(authentication);
            return new CommandResult(MESSAGE_LOCK_SUCCESS_OLD_PASSWORD);
        }

        //New password is entered, use this to lock the zip.
        setNewPassword(authentication);

        //Case of updating password, since current password and new password are present.
        if (this.currentPassword.isPresent()) {
            return new CommandResult(MESSAGE_LOCK_SUCCESS_UPDATE_PASSWORD);
        //Case of a new lock without current password.
        } else {
            return new CommandResult(MESSAGE_LOCK_SUCCESS_NEW_PASSWORD);
        }

    }

    /**
     * Attempts to read the user's previous password from a password file and set the password to the model.
     */
    private void useOldPassword(Authentication authentication) throws CommandException {
        try {
            authentication.readPasswordFileAndSetPassword();
        } catch (Exception e) {
            throw new CommandException(MESSAGE_FAIL_TO_READ_PASSWORD_FILE, e);
        }
    }


    private void setNewPassword(Authentication authentication) throws CommandException {
        //New password is entered, use this to lock the zip.
        try {
            authentication.setPassword(this.newPassword);
        } catch (Exception e) {
            System.err.println(e);
            throw new CommandException(MESSAGE_FAILED_TO_STORE_PASSWORD, e);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LockCommand // instanceof handles nulls
                && currentPassword.equals(((LockCommand) other).currentPassword)
                && newPassword.equals(((LockCommand) other).newPassword)); // state check
    }
}
