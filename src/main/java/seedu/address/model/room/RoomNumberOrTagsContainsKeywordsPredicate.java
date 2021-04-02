package seedu.address.model.room;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Room}'s {@code RoomNumber} or {@Tag} matches any of the keywords given.
 */

public class RoomNumberOrTagsContainsKeywordsPredicate implements Predicate<Room> {
    private final List<String> keywords;

    public RoomNumberOrTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if any of the keywords are contained in either the room's number or any of its tags.
     *
     * @param room Room whose number and tags need to be checked to see if they contain any of the given keywords.
     * @return True if keyword is contained in the room's number or tags.
     */
    @Override
    public boolean test(Room room) {
        return keywords.stream()
                .anyMatch(keyword -> room.getRoomNumber().roomNumber.contains(keyword)
                        || room.getTags().stream()
                        .anyMatch(t -> t.tagName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumberOrTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoomNumberOrTagsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
