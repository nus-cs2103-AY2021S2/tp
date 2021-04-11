package seedu.partyplanet.logic.autocomplete;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.partyplanet.logic.autocomplete.exceptions.AutocompleteException;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

public class AutocompleteParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final String autocompleteError = "Unable to match input String. Returning input String.";

    /**
     * Parses input string and retrieves the relevant Autocomplete Util.
     */
    public AutocompleteUtil parseCommand(String input)
        throws ParseException, CommandException, AutocompleteException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new AutocompleteException(autocompleteError);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandWord.equals("edit")) {
            return new EditAutocompleteUtil(arguments);
        }

        if (commandWord.equals("eedit")) {
            return new EEditAutocompleteUtil(arguments);
        }

        throw new AutocompleteException(autocompleteError);
    }

}
