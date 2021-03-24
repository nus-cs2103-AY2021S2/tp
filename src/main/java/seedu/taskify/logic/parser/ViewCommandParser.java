package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import seedu.taskify.logic.commands.ViewCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.predicates.TaskHasSameDatePredicate;

public class ViewCommandParser implements Parser<ViewCommand> {

    private static final String TODAY = "today";
    private static final String TOMORROW = "tomorrow";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        assert args != null : "String should not be null";
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }


        if (trimmedArgs.equalsIgnoreCase(TODAY)) {
            LocalDate inputDate = LocalDate.now();
            return new ViewCommand(new TaskHasSameDatePredicate(inputDate));
        } else if (trimmedArgs.equalsIgnoreCase(TOMORROW)) {
            LocalDate inputDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
            return new ViewCommand(new TaskHasSameDatePredicate(inputDate));
        } else {
            try {
                LocalDate inputDate = LocalDate.parse(trimmedArgs, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new ViewCommand(new TaskHasSameDatePredicate(inputDate));
            } catch (DateTimeParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
        }
    }
}
