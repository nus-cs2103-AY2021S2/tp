package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.SelectClearCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SelectCommand object.
 */
public class SelectCommandParser implements Parser<SelectCommand> {

    /**
     * Used for separation of sub command word and args.
     */
    private static final Pattern SELECT_COMMAND_FORMAT = Pattern
            .compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    @Override
    public SelectCommand parse(String args) throws ParseException {
        final Matcher matcher = SELECT_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");

        if (SelectCommand.SHOW_SUB_COMMAND_WORD.equals(subCommandWord)) {
            return new SelectShowCommand();
        } else if (SelectCommand.CLEAR_SUB_COMMAND_WORD.equals(subCommandWord)) {
            return new SelectClearCommand();
        }
        return new SelectIndexCommandParser().parse(args.trim());
    }

    @Override
    public boolean isValidCommandToAlias(String args) {
        if (args.trim().isEmpty()) {
            return true;
        }

        final Matcher matcher = SELECT_COMMAND_FORMAT.matcher(args.trim());
        matcher.matches();
        final String subCommandWord = matcher.group("subCommandWord");

        switch (subCommandWord) {
        case SelectCommand.CLEAR_SUB_COMMAND_WORD:
        case SelectCommand.SHOW_SUB_COMMAND_WORD:
            return true;
        default:
            return new SelectIndexCommandParser().isValidCommandToAlias(args.trim());
        }
    }
}
