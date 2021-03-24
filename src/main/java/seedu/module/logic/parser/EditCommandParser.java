package seedu.module.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.EditCommand;
import seedu.module.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_START_TIME, PREFIX_DEADLINE,
                    PREFIX_MODULE, PREFIX_DESCRIPTION, PREFIX_WORKLOAD, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            editTaskDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_TASK_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editTaskDescriptor.setStartTime(ParserUtil.parsetime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parsetime(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editTaskDescriptor.setModule(ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor
                .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_WORKLOAD).isPresent()) {
            editTaskDescriptor.setWorkload(ParserUtil.parseWorkload(argMultimap.getValue(PREFIX_WORKLOAD).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
