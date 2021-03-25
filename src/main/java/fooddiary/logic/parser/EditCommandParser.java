package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static fooddiary.logic.parser.CliSyntax.PREFIX_NAME;
import static fooddiary.logic.parser.CliSyntax.PREFIX_PRICE;
import static fooddiary.logic.parser.CliSyntax.PREFIX_RATING;
import static fooddiary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static fooddiary.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;



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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RATING, PREFIX_PRICE,
                PREFIX_REVIEW, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditEntryDescriptor editEntryDescriptor = new EditEntryDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEntryDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editEntryDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editEntryDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        parseReviewsForEdit(argMultimap.getAllValues(PREFIX_REVIEW)).ifPresent(editEntryDescriptor::setReviews);
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editEntryDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEntryDescriptor::setTags);

        if (!editEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEntryDescriptor);
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

    /**
     * Parses {@code Collection<String> reviews} into a {@code List<Review>} if {@code reviews} is non-empty.
     * If {@code reviews} contain only one element which is an empty string, it will be parsed into a
     * {@code List<Review>} containing zero reviews.
     */
    private Optional<List<Review>> parseReviewsForEdit(Collection<String> reviews) throws ParseException {
        assert reviews != null;

        if (reviews.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> reviewList = reviews;
        return Optional.of(ParserUtil.parseReviews(reviewList));
    }

}
