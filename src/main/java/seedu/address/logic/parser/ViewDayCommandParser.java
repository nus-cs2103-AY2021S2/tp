package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewDayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.predicates.TaskOnDatePredicate;

/**
 * Parses input arguments and creates a new ViewDayCommand object
 */
public class ViewDayCommandParser implements Parser<ViewDayCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewDayCommand
     * and returns an ViewDayCommand object for execution.
     *
     * @param userInput Provided user input for the ViewDayCommand.
     * @return ViewDayCommand with the predicate of the date constructed with the userInput.
     * @throws ParseException if the userInput does not conform to the expected format of a date.
     */
    public ViewDayCommand parse(String userInput) throws ParseException {
        String trimmedInput = userInput.trim();
        if (userInput.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDayCommand.MESSAGE_USAGE));
        }

        try {
            Date date = ParserUtil.parseDate(trimmedInput);
            return new ViewDayCommand(new TaskOnDatePredicate(date), date.getDate());
        } catch (ParseException parseException) {
            throw parseException;
        }
    }
}
