package seedu.partyplanet.logic.autocomplete;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;

public class AutocompleteParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses input string and retrieves the relevant Autocomplete Util.
     */
    public String parse(String input, Model model) throws ParseException, CommandException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return input;
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandWord.equals("edit")) {
            return new EditAutocompleteUtil().parseCommand(arguments, model);
        }

        if (commandWord.equals("eedit")) {
            return new EEditAutocompleteUtil().parseCommand(arguments, model);
        }

        return input;
    }

}
