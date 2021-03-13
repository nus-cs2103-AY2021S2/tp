package seedu.address.logic.parser;

import java.util.regex.Pattern;

public class CommandValidator {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Returns true if a given user input is a valid command.
     *
     * @param userInput full user input string
     */
    public static boolean isValidCommand(String userInput) {
        // TO BE DONE
        return true;
    }

}
