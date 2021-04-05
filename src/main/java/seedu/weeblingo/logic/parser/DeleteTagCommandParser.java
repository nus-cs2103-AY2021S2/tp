package seedu.weeblingo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.DeleteTagCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.tag.Tag;

public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of DeleteTagCommand.
     *
     * @param args Given arguments
     * @return The DeleteTagCommand
     * @throws ParseException if the given arguments do not comply with the requirements of the DeleteTagCommand
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(pe.getMessage(), DeleteTagCommand.MESSAGE_USAGE));
        }

        Set<Tag> tags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).orElse(Collections.emptySet());

        return new DeleteTagCommand(index, tags);
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
