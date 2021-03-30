package fooddiary.model.entry;

import static fooddiary.model.entry.Price.PRICE_DOLLAR_SIGN;
import static fooddiary.model.entry.Price.PRICE_RANGE_DASH;
import static fooddiary.model.entry.Rating.RATING_OUT_OF_FIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import fooddiary.commons.util.StringUtil;
import fooddiary.model.tag.Tag;

/**
 * Tests that a {@code Entry}'s {@code Restaurant Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        assert(entry != null);

        List<String> priceKeywords = new ArrayList<>();
        List<String> nonPriceKeywords = new ArrayList<>();

        //sort keywords by price/non-price & convert price to price range if necessary
        for (String s : keywords) {
            if (s.length() < 2) {
                nonPriceKeywords.add(s);
            } else if (Price.isValidPrice(s.substring(1)) && s.contains(PRICE_DOLLAR_SIGN)
                    && s.contains(PRICE_RANGE_DASH)) {
                priceKeywords.add(s.substring(1));
            } else if (Price.isValidPrice(s.substring(1)) && s.contains(PRICE_DOLLAR_SIGN)) {
                priceKeywords.add(s.substring(1) + PRICE_RANGE_DASH + s.substring(1));
            } else {
                nonPriceKeywords.add(s);
            }
        }

        return testPrice(entry, priceKeywords) || testNonPrice(entry, nonPriceKeywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    private boolean testPrice(Entry entry, List<String> priceKeywords) {
        //convert entry price to price range if necessary before comparison test against keywords
        StringBuilder sb = new StringBuilder(entry.getPrice().value);
        if (!sb.toString().contains(PRICE_RANGE_DASH)) {
            sb.append(PRICE_RANGE_DASH).append(entry.getPrice().value);
        }
        return priceKeywords.stream().anyMatch(keyword ->
                Integer.parseInt(keyword.split(PRICE_RANGE_DASH)[0])
                <= Integer.parseInt(sb.toString().split(PRICE_RANGE_DASH)[1])
                && Integer.parseInt(sb.toString().split(PRICE_RANGE_DASH)[0])
                <= Integer.parseInt(keyword.split(PRICE_RANGE_DASH)[1]));
    }

    private boolean testNonPrice(Entry entry, List<String> nonPriceKeywords) {
        //combine name, rating, and address into a single string to test for keywords
        StringBuilder sb = new StringBuilder(entry.getName().fullName);
        sb.append(" ").append(entry.getRating().value).append(RATING_OUT_OF_FIVE);
        sb.append(" ").append(entry.getAddress().value);
        for (Tag t : entry.getTagCategories()) {
            sb.append(" ").append(t.tag);
        }
        for (Tag t : entry.getTagSchools()) {
            sb.append(" ").append(t.tag);
        }
        return nonPriceKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(sb.toString(), keyword));
    }

}
