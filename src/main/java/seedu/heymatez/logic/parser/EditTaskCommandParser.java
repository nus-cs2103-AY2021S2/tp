package seedu.heymatez.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.logic.commands.EditTaskCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.assignee.Assignee;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_DEADLINE,
                        PREFIX_PRIORITY, PREFIX_ASSIGNEE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editTaskDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editTaskDescriptor.setStatus(ParserUtil.parseStatus(argMultimap
                    .getValue(PREFIX_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap
                    .getValue(PREFIX_DEADLINE).get()));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editTaskDescriptor.setPriority(ParserUtil.parsePriority(argMultimap
                    .getValue(PREFIX_PRIORITY).get()));
        }

        parseAssigneesForEdit(argMultimap.getAllValues(PREFIX_ASSIGNEE)).ifPresent(editTaskDescriptor::setAssignees);

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> assignees} into a {@code Set<Assignee>} if {@code assignees} is non-empty.
     * If {@code assignees} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Assignee>} containing zero assignees.
     */
    private Optional<Set<Assignee>> parseAssigneesForEdit(Collection<String> assignees) throws ParseException {
        assert assignees != null;

        if (assignees.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ParserUtil.parseAssignees(assignees));
    }
}
