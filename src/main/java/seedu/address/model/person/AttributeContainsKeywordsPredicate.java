package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Tests that a {@code Passenger}'s {@code Name}, {@code Phone}, {@code Address} or {@code Tag}
 * matches any of the keywords given.
 */
public class AttributeContainsKeywordsPredicate implements Predicate<Passenger> {
    private final List<String> keywords;
    private final Predicate<Passenger> predicate;

    /**
     * Constructs Predicate which is a logical or combination with Name, Phone, Address and Tag predicates.
     * @param keywords search terms to perform search on.
     */
    public AttributeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;

        List<String> keywordsForName = parseKeyword(keywords, PREFIX_NAME.toString());
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywordsForName);

        List<String> keywordsForPhone = parseKeyword(keywords, PREFIX_PHONE.toString());
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(keywordsForPhone);

        List<String> keywordsForAddress = parseKeyword(keywords, PREFIX_ADDRESS.toString());
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(keywordsForAddress);

        List<String> keywordsForTags = parseKeyword(keywords, PREFIX_TAG.toString());
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(keywordsForTags);

        predicate = namePredicate.or(phonePredicate.or(addressPredicate.or(tagPredicate)));
    }

    /**
     * Parses the keywords for each of the predicates. If parsing fails, the keyword is dropped as it isn't a valid
     * search term.
     * @param keywords search terms to be parsed.
     * @param prefix prefix to operate on.
     * @return list of valid keywords to search on for corresponding prefix.
     */
    private List<String> parseKeyword(List<String> keywords, String prefix) {
        List<String> outputList = new ArrayList<>();

        for (String s : keywords) {
            try {
                switch (prefix) {
                case "n/":
                    outputList.add(ParserUtil.parseName(s).toString());
                    break;
                case "a/":
                    outputList.add(ParserUtil.parseAddress(s).toString());
                    break;
                case "p/":
                    outputList.add(ParserUtil.parsePhone(s).toString());
                    break;
                case "tag/":
                    outputList.add(ParserUtil.parseTag(s).toString());
                    break;
                default:
                    break;
                }

            } catch (ParseException ignored) {
                // keyword is dropped as it isn't a valid term determined by parser
            }
        }

        return outputList;
    }

    @Override
    public boolean test(Passenger passenger) {
        return predicate.test(passenger);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttributeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
