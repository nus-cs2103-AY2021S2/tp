package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import dog.pawbook.logic.commands.EditEntityCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public abstract class EditCommandParser<T extends EditEntityCommand> extends CommandWithPrefixParser<T> {
    protected static final Prefix[] EDIT_COMMON_PREFIXES = {PREFIX_NAME, PREFIX_TAG};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public final T parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = extractArguments(args);

        int id = extractId(argMultimap);

        return genEditCommand(id, argMultimap);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Extract name and tags from provided arguments if possible and modify {@code editEntityDescriptor} accordingly.
     */
    protected static void fillCommonAttributes(EditEntityCommand.EditEntityDescriptor editEntityDescriptor,
            ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEntityDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEntityDescriptor::setTags);
    }

    private int extractId(ArgumentMultimap argMultimap) throws ParseException {
        int id;
        try {
            id = Integer.parseInt(argMultimap.getPreamble());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageText()), e);
        }

        if (id < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageText()));
        }
        return id;
    }

    /**
     * The child classes are responsible for creating their own entity-specific {@code EditDescriptor} for returning the
     * corresponding EditCommand at the end of parsing.
     */
    protected abstract T genEditCommand(int id, ArgumentMultimap argMultimap) throws ParseException;
}
