package seedu.address.logic.parser;


import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.LockCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class LockCommandParser implements Parser<LockCommand> {

    private static final String INVALID_PASSWORD_FORMAT = "Please enter a valid password without special symbols.";

    @Override
    public LockCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] passwords = trimmedArgs.split("\\s+");

        if (trimmedArgs.isEmpty()) {
            return new LockCommand();
        }

        if (passwords.length > 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LockCommand.MESSAGE_USAGE));
        }

        if (!isPasswordValid(passwords[0])) {
            throw new ParseException(INVALID_PASSWORD_FORMAT);
        }

        //Length 1: User only provided new password.
        if (passwords.length == 1) {
            return new LockCommand(passwords[0]);
        }

        if (!isPasswordValid(passwords[1])) {
            throw new ParseException(INVALID_PASSWORD_FORMAT);
        }

        //Length 2: With old and new password.
        return new LockCommand(passwords[0], passwords[1]);
    }

    /**
     * Checks that the password provided contains only ASCII characters.
     * @param password password to be tested.
     * @return true if password is valid.
     */
    private boolean isPasswordValid(String password) {
        return password.chars().allMatch(c -> c >= 0x20 && c < 0x7F);
    }
}
