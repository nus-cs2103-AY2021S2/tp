package fooddiary.logic.parser;

import static fooddiary.logic.parser.CliSyntax.PREFIX_PRICE;
import static fooddiary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.AddOnCommand;
import fooddiary.logic.commands.AddOnCommand.AddOnToEntryDescriptor;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Price;
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
                ArgumentTokenizer.tokenize(args, PREFIX_REVIEW, PREFIX_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(e.getMessage());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOnCommand.MESSAGE_USAGE), pe);
        }

        AddOnToEntryDescriptor addOnToEntryDescriptor = new AddOnToEntryDescriptor();

        parseReviewsForAddOn(argMultimap.getAllValues(PREFIX_REVIEW)).ifPresent(addOnToEntryDescriptor::setReviews);

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            if (isSinglePriceValue(argMultimap.getValue(PREFIX_PRICE).get())) {
                addOnToEntryDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
            } else {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddOnCommand.MESSAGE_USAGE));
            }

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

    /**
     * Checks is the input price is a single price value or a price range
     * @param price the price to be checked
     * @return a boolean value stating is the input price is a single price value
     */
    private boolean isSinglePriceValue(String price) {
        return !price.contains(Price.PRICE_RANGE_DASH);
    }

}
