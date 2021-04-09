package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.weeblingo.logic.commands.CheckCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.flashcard.Answer;

/**
 * Parses input arguments and creates a new CheckCommand object
 */
public class CheckCommandParser implements Parser<CheckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of CheckCommand and returns the CheckCommand
     * object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CheckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
        Answer attempt = ParserUtil.parseAnswer(trimmedArgs);
        return new CheckCommand(attempt);
    }
}
