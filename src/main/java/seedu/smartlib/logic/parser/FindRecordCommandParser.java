package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.smartlib.logic.commands.FindRecordCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.record.RecordContainsBookNamePredicate;

/**
 * Parses input arguments and creates a new FindBookCommand object.
 */
public class FindRecordCommandParser implements Parser<FindRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindRecordCommand
     * and returns a FindRecordCommand object for execution.
     *
     * @param args arguments given in the user input.
     * @return a FindRecordCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindRecordCommand(new RecordContainsBookNamePredicate(Arrays.asList(nameKeywords)));
    }

}

