package seedu.address.commons.util;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.model.tag.Tag;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Returns true if the {@code tags} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       Set&#60;Tag&#62; tags = new HashSet<>(Arrays.asList("ABc", "def"));
     *       containsWordIgnoreCase(tags, "abc") == true
     *       containsWordIgnoreCase(tags, "DEF") == true
     *       containsWordIgnoreCase(tags, "AB") == false //not a full word match
     *       </pre>
     * @param tags cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean tagContainsWordIgnoreCase(Set<Tag> tags, String word) {
        requireNonNull(tags);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String[] wordsInTagSet = tagsToStringArray(tags);

        return Arrays.stream(wordsInTagSet)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and a full word match is not required.
     *   <br>examples:<pre>
     *       containsPartWordIgnoreCase("ABc def", "abc") == true
     *       containsPartWordIgnoreCase("ABc def", "DEF") == true
     *       containsPartWordIgnoreCase("ABc def", "AB") == true
     *       </pre>
     * @param tags cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean tagContainsPartWordIgnoreCase(Set<Tag> tags, String word) {
        requireNonNull(tags);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        preppedWord = preppedWord.toLowerCase();
        String wordsInTagSet = tagsToString(tags);

        return wordsInTagSet.contains(preppedWord);
    }

    /**
     * Returns an array of String from a set of Tag
     *
     * @param tags set of Tag
     * @return array of String from the set of Tag
     */
    private static String[] tagsToStringArray(Set<Tag> tags) {
        String[] stringArr = new String[tags.size()];

        int i = 0;
        for (Tag tag : tags) {
            stringArr[i] = tag.tagName;
            i++;
        }

        return stringArr;
    }

    /**
     * Returns a String from a set of Tag with space in between each Tag
     *
     * @param tags set of Tag
     * @return String from the set of Tag
     */
    private static String tagsToString(Set<Tag> tags) {
        String tagString = "";

        for (Tag tag : tags) {
            tagString += tag.tagName.toLowerCase() + " ";
        }
        tagString.trim();

        return tagString;
    }
}
