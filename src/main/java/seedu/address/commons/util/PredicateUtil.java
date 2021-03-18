package seedu.address.commons.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.AbstractId;

public class PredicateUtil {

    /**
     * Returns true if the string matches any of the given keywords (even as a prefix).
     * Uses { @code String.containsPrefixWordIgnoreCase }.
     *
     * @param string string to check against
     * @param keywords keywords
     * @return
     */
    public static boolean containsPrefixWordIgnoreCase(String string, List<String> keywords) {
        requireAllNonNull(string, keywords);
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsPrefixWordIgnoreCase(string, keyword));
    }

    /**
     * Returns the similarity score of two strings.
     * Strings that matches the keywords better will have a higher score.
     *
     * @param string string to check against
     * @param keywords keywords
     * @return similarity score
     */
    public static double getWordSimilarityScoreIgnoreCase(String string, List<String> keywords) {
        requireAllNonNull(string, keywords);
        return keywords.stream()
                .mapToDouble(keyword -> {
                    if (StringUtil.containsWordIgnoreCase(string, keyword)) {
                        return keyword.length() * keyword.length();
                    } else if (StringUtil.containsPrefixWordIgnoreCase(string, keyword)) {
                        return keyword.length();
                    } else {
                        return 0;
                    }
                }).sum();
    }

    /**
     * Returns whether the keywords list contains the id.
     *
     * @param id id to check against
     * @param keywords keywords
     * @return
     */
    public static boolean matchIntegerId(AbstractId id, List<String> keywords) {
        requireAllNonNull(id, keywords);
        return keywords.stream()
            .mapToInt(x -> StringUtil.isNonZeroUnsignedInteger(x) ? Integer.parseInt(x) : -1)
            .anyMatch(keyword -> keyword == id.value);
    }

}
