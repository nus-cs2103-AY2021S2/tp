package fooddiary.model.entry;

import static fooddiary.commons.core.Messages.SUGGESTION_MESSAGE_EMPTY;
import static fooddiary.commons.core.Messages.SUGGESTION_MESSAGE_FOR_PRICE;
import static fooddiary.commons.core.Messages.SUGGESTION_MESSAGE_FOR_RATING;
import static fooddiary.commons.core.Messages.SUGGESTION_MESSAGE_FOR_RATING_AND_PRICE;
import static fooddiary.model.entry.Price.PRICE_DOLLAR_SIGN;
import static fooddiary.model.entry.Price.PRICE_RANGE_DASH;
import static fooddiary.model.entry.Rating.RATING_OUT_OF_FIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import fooddiary.commons.util.StringUtil;
import fooddiary.model.tag.Tag;

/**
 * Tests that an entry matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public NameContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        assert(entry != null);
        if (keywords.isEmpty()) {
            return false;
        }

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

        return testPrice(entry, priceKeywords) && testNonPrice(entry, nonPriceKeywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Helper function that tests that an entry matches all of the price keywords given
     *
     * @param entry an entry to search for price keywords
     * @param priceKeywords the price keywords to be used to search an entry
     * @return true if entry contains all of the price keywords, false otherwise
     */
    private boolean testPrice(Entry entry, List<String> priceKeywords) {
        //convert entry price to price range if necessary before comparison test against keywords
        StringBuilder sb = new StringBuilder(entry.getPrice().value);
        if (!sb.toString().contains(PRICE_RANGE_DASH)) {
            sb.append(PRICE_RANGE_DASH).append(entry.getPrice().value);
        }
        return priceKeywords.stream().allMatch(keyword ->
            Integer.parseInt(keyword.split(PRICE_RANGE_DASH)[0])
            <= Integer.parseInt(sb.toString().split(PRICE_RANGE_DASH)[1])
            && Integer.parseInt(sb.toString().split(PRICE_RANGE_DASH)[0])
            <= Integer.parseInt(keyword.split(PRICE_RANGE_DASH)[1]));
    }

    /**
     * Helper function that tests that an entry matches all of the non-price keywords given
     *
     * @param entry an entry to search for non-price keywords
     * @param nonPriceKeywords the non-price keywords to be used to search an entry
     * @return true if entry contains all of the non-price keywords, false otherwise
     */
    private boolean testNonPrice(Entry entry, List<String> nonPriceKeywords) {
        //combine name, rating, address, categories & schools into a single string to test for keywords
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
            .allMatch(keyword -> StringUtil.containsWordIgnoreCase(sb.toString(), keyword));
    }

    /**
     * Provides suggestion messages based on the keywords given
     *
     * @return suggestion message
     */
    public String getSuggestionMessage() {
        String ratingResemblingRegex = "^[0-9]?[/][0-9]?$";
        String priceResemblingRegex = "^[$][0-9]{0,3}?[-]?[0-9]{0,3}?";
        String ratingRegex = "^[0-5]{1}[/][5]$";

        boolean containsRatingResemblingKeywords = false;
        boolean containsPriceResemblingKeywords = false;

        if (keywords.stream().anyMatch(keyword
            -> keyword.matches(ratingResemblingRegex) && !keyword.matches(ratingRegex))) {
            containsRatingResemblingKeywords = true;
        }
        if (keywords.stream().anyMatch(keyword
            -> keyword.length() > 1 && keyword.matches(priceResemblingRegex)
            && !Price.isValidPrice(keyword.substring(1)))) {
            containsPriceResemblingKeywords = true;
        }

        String suggestionMessage = SUGGESTION_MESSAGE_EMPTY;
        if (containsRatingResemblingKeywords && containsPriceResemblingKeywords) {
            suggestionMessage = SUGGESTION_MESSAGE_FOR_RATING_AND_PRICE;
        } else if (containsRatingResemblingKeywords) {
            suggestionMessage = SUGGESTION_MESSAGE_FOR_RATING;
        } else if (containsPriceResemblingKeywords) {
            suggestionMessage = SUGGESTION_MESSAGE_FOR_PRICE;
        }
        return suggestionMessage;
    }
}
