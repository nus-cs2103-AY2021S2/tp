package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_RATING,
                        CliSyntax.PREFIX_PRICE, CliSyntax.PREFIX_REVIEW,
                        CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG_CATEGORY, CliSyntax.PREFIX_TAG_SCHOOL);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ADDRESS,
                CliSyntax.PREFIX_RATING, CliSyntax.PREFIX_PRICE, CliSyntax.PREFIX_REVIEW)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Rating rating = ParserUtil.parseRating(argMultimap.getValue(CliSyntax.PREFIX_RATING).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(CliSyntax.PREFIX_PRICE).get());
        List<Review> reviewList = ParserUtil.parseReviews(argMultimap.getValueInList(CliSyntax.PREFIX_REVIEW));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
        Set<TagCategory> tagCategoriesList = ParserUtil.parseTagsCategories(argMultimap
                                                .getAllValues(CliSyntax.PREFIX_TAG_CATEGORY));
        Set<TagSchool> tagSchoolList = ParserUtil.parseTagSchool(argMultimap.getAllValues(CliSyntax.PREFIX_TAG_SCHOOL));

        Entry entry = new Entry(name, rating, price, reviewList, address, tagCategoriesList, tagSchoolList);

        return new AddCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
