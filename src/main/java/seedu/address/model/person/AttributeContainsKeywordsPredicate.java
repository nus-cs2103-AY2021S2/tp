package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.parseAttributePredicateKeywords;

import java.util.List;
import java.util.function.Predicate;

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

        List<String> keywordsForName = parseAttributePredicateKeywords(keywords, PREFIX_NAME.toString());
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywordsForName);

        List<String> keywordsForPhone = parseAttributePredicateKeywords(keywords, PREFIX_PHONE.toString());
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(keywordsForPhone);

        List<String> keywordsForAddress = parseAttributePredicateKeywords(keywords, PREFIX_ADDRESS.toString());
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(keywordsForAddress);

        List<String> keywordsForTags = parseAttributePredicateKeywords(keywords, PREFIX_TAG.toString());
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(keywordsForTags);

        predicate = namePredicate.or(phonePredicate.or(addressPredicate.or(tagPredicate)));
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
