package seedu.dictionote.model.note;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag}s match all of the keywords given.
 */
public class TagNoteContainKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    public TagNoteContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        return keywords.stream()
                .allMatch(keyword -> note.getTags()
                        .toString()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagNoteContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagNoteContainKeywordsPredicate) other).keywords)); // state check
    }

}
