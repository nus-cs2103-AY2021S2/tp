package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.AddOnCommand;
import fooddiary.logic.commands.AddOnCommand.AddOnToEntryDescriptor;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Review;


/**
 * Parses input arguments and creates a new AddOnCommand object
 */
public class AddOnCommandParser implements Parser<AddOnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOnCommand
     * and returns an AddOnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REVIEW);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOnCommand.MESSAGE_USAGE), pe);
        }

        AddOnToEntryDescriptor addOnToEntryDescriptor = new AddOnToEntryDescriptor();

        parseReviewsForAddOn(argMultimap.getAllValues(PREFIX_REVIEW)).ifPresent(addOnToEntryDescriptor::setReviews);

        if (!addOnToEntryDescriptor.isAnyFieldAddedOn()) {
            throw new ParseException(AddOnCommand.MESSAGE_NOT_ADDED_ON);
        }

        return new AddOnCommand(index, addOnToEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> reviews} into a {@code List<Review>} if {@code reviews} is non-empty.
     * If {@code reviews} contain only one element which is an empty string, it will be parsed into a
     * {@code List<Review>} containing zero reviews.
     */
    private Optional<List<Review>> parseReviewsForAddOn(Collection<String> reviews) throws ParseException {
        assert reviews != null;

        if (reviews.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> reviewList = reviews;
        return Optional.of(ParserUtil.parseReviews(reviewList));
    }

}
