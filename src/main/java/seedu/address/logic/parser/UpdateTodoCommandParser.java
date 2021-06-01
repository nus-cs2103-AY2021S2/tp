package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

/**
 * Parses input arguments and creates a new UpdateTodoCommand object
 */
public class UpdateTodoCommandParser implements Parser<UpdateTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code UpdateTodoCommand}.
     * and returns an {@code UpdateTodoCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public UpdateTodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_DESCRIPTION)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTodoCommand.MESSAGE_USAGE));
        }

        Index projectIndex;

        try {
            projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, pe);
        }

        Index targetTodoIndex;

        try {
            targetTodoIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TODO_DISPLAYED_INDEX, pe);
        }

        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        CompletableTodo todo = new Todo(description);

        return new UpdateTodoCommand(projectIndex, targetTodoIndex, todo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

