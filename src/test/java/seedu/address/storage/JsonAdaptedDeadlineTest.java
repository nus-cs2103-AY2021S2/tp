package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;

public class JsonAdaptedDeadlineTest {
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_DESCRIPTION = DeadlineBuilder.DEFAULT_DESCRIPTION;
    private static final Boolean VALID_IS_DONE = DeadlineBuilder.DEFAULT_IS_DONE;
    private static final String VALID_DATE_STRING = "02-02-2020";
    private static final LocalDate VALID_DATE = DeadlineBuilder.DEFAULT_DATE;

    private static final Deadline ASSIGNMENT = new DeadlineBuilder()
            .withDescription(VALID_DESCRIPTION).withIsDone(VALID_IS_DONE).withByDate(VALID_DATE).build();

    @Test
    public void toModelType_validDeadlineDetails_returnsDeadline() throws Exception {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(ASSIGNMENT);
        assertEquals(ASSIGNMENT, deadline.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalArgumentException() {
        JsonAdaptedDeadline deadline =
                new JsonAdaptedDeadline(INVALID_DESCRIPTION, VALID_DATE_STRING, VALID_IS_DONE);
        String expectedMessage = CompletableDeadline.MESSAGE_CONSTRAINTS_DESCRIPTION;
        assertThrows(IllegalArgumentException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalArgumentException() {
        JsonAdaptedDeadline deadline =
                new JsonAdaptedDeadline(INVALID_DESCRIPTION, VALID_DATE_STRING, VALID_IS_DONE);
        assertThrows(IllegalArgumentException.class, deadline::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsNullPointerException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(null, VALID_DATE_STRING, VALID_IS_DONE);
        assertThrows(NullPointerException.class, deadline::toModelType);
    }
}
