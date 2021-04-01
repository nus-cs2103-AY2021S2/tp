package seedu.address.model.appointment.predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptTagsContainKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptTagsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        Set<Tag> tagSet = appointment.getTags();

        for (Tag tag : tagSet) {
            boolean isFound = super.getKeywords().stream().anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(tag.getTagName(), keyword));
            if (isFound) {
                return true;
            }
        }

        return false;
    }

}
