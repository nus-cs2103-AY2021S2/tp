package seedu.address.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_TO_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditScheduleDescriptor descriptorWithSameValues = new EditScheduleDescriptor(DESC_SCHEDULE_MATHS);
        assertTrue(DESC_SCHEDULE_MATHS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SCHEDULE_MATHS.equals(DESC_SCHEDULE_MATHS));

        // null -> returns false
        assertFalse(DESC_SCHEDULE_MATHS.equals(null));

        // different types -> returns false
        assertFalse(DESC_SCHEDULE_MATHS.equals(5));

        // different values -> returns false
        assertFalse(DESC_SCHEDULE_MATHS.equals(DESC_SCHEDULE_SCIENCE));

        // different title -> returns false
        EditScheduleDescriptor editedMaths = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_MATHS)
                .withTitle(VALID_SCHEDULE_TITLE_TWO).build();
        assertFalse(DESC_SCHEDULE_MATHS.equals(editedMaths));

        // different desc -> returns false
        editedMaths = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_MATHS)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).build();
        assertFalse(DESC_SCHEDULE_MATHS.equals(editedMaths));

        // different timefrom -> returns false
        editedMaths = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_MATHS)
                .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO).build();
        assertFalse(DESC_SCHEDULE_MATHS.equals(editedMaths));

        // different timeto -> returns false
        editedMaths = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_MATHS)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_TWO).build();
        assertFalse(DESC_SCHEDULE_MATHS.equals(editedMaths));
    }
}
