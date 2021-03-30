package seedu.dictionote.model.note;

import java.util.List;
import java.util.function.Predicate;

public class NoteContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    public NoteContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        if (keywords.isEmpty()) {
            return true;
        }
        return keywords.stream()
                .anyMatch(keyword -> note.getNote().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NoteContainsKeywordsPredicate) other).keywords)); // state check
    }
}
