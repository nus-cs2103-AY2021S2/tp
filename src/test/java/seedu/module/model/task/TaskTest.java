package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_RECURRENCE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_START_TIME_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_WORKLOAD_2;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.LAB;
import static seedu.module.testutil.TypicalTasks.QUIZ;
import static seedu.module.testutil.TypicalTasks.REVIEW;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.testutil.TaskBuilder;
import seedu.module.testutil.TypicalTasks;


public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isTimeInvalidTest() {
        // QUIZ is valid
        assertFalse(QUIZ.isTimeInvalid());

        // Compare time with time field and time without time field, valid
        Task editedQuiz = new TaskBuilder(QUIZ)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .build();
        assertFalse(editedQuiz.isTimeInvalid());

        // Compare two time, invalid
        editedQuiz = new TaskBuilder(QUIZ)
                .withDeadline(VALID_DEADLINE_LAB)
                .build();
        assertTrue(editedQuiz.isTimeInvalid());

        // Quiz without startTime is always valid
        editedQuiz = new TaskBuilder(QUIZ)
                .deactivateStartTime()
                .build();
        assertFalse(editedQuiz.isTimeInvalid());
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(QUIZ.isSameTask(QUIZ));

        // null -> returns false
        assertFalse(QUIZ.isSameTask(null));

        // same name and different module all other attributes different -> returns false
        Task editedQuiz = new TaskBuilder(QUIZ)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withWorkload(VALID_WORKLOAD_2)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertFalse(QUIZ.isSameTask(editedQuiz));

        // same name and same module, all other attributes different -> returns true
        editedQuiz = new TaskBuilder(REVIEW)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withWorkload(VALID_WORKLOAD_2).build();
        assertTrue(REVIEW.isSameTask(editedQuiz));

        //different name and same module, all other attributes different -> return false
        editedQuiz = new TaskBuilder(QUIZ)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withWorkload(VALID_WORKLOAD_2).build();
        assertFalse(REVIEW.isSameTask(editedQuiz));

        // different name, different module -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withName(VALID_TASK_NAME_LAB).withModule(VALID_MODULE_LAB).build();
        assertFalse(REVIEW.isSameTask(editedQuiz));

        // name differs in case, all other attributes same -> returns false
        Task editedReview = new TaskBuilder(TypicalTasks.REVIEW)
                .withName(VALID_TASK_NAME_PRACTICAL.toLowerCase()).build();
        assertFalse(TypicalTasks.REVIEW.isSameTask(editedReview));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TASK_NAME_PRACTICAL + " ";
        editedReview = new TaskBuilder(TypicalTasks.REVIEW).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalTasks.REVIEW.isSameTask(editedReview));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(QUIZ).build();
        assertTrue(QUIZ.equals(aliceCopy));

        // same object -> returns true
        assertTrue(QUIZ.equals(QUIZ));

        // null -> returns false
        assertFalse(QUIZ.equals(null));

        // different type -> returns false
        assertFalse(QUIZ.equals(5));

        // different task -> returns false
        assertFalse(QUIZ.equals(TypicalTasks.REVIEW));

        // different name -> returns false
        Task editedQuiz = new TaskBuilder(QUIZ).withName(VALID_TASK_NAME_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different startTime -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withStartTime(VALID_START_TIME_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different deadline -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withDeadline(VALID_DEADLINE_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different module -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withModule(VALID_MODULE_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different description -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withDescription(VALID_DESCRIPTION_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different workload -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withWorkload(VALID_WORKLOAD_2).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different done status -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withDoneStatus(String.valueOf(Boolean.TRUE)).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different tags -> returns false
        editedQuiz = new TaskBuilder(QUIZ).withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertFalse(QUIZ.equals(editedQuiz));

        // different isDeadline -> returns false
        editedQuiz = new TaskBuilder(QUIZ).deactivateStartTime().build();
        assertFalse(QUIZ.equals(editedQuiz));
    }

    @Test
    public void makeNextRecurringTaskTest() {
        //EP: recurrence is null
        OptionalField<Recurrence> emptyRecurrence = new OptionalField<>(null);
        assertTrue(Task.makeNextRecurringTask(LAB, emptyRecurrence).equals(LAB));

        //EP: recurrence is valid
        OptionalField<Recurrence> validOptionalRecurrence = new OptionalField<>(new Recurrence(VALID_RECURRENCE_LAB));
        Time oldTime = LAB.getDeadline();
        Task lab = new TaskBuilder(LAB).withRecurrence(VALID_RECURRENCE_LAB).build();
        Time newTime = Task.getRecurringTime(lab, oldTime);
        Task recurredLab = new TaskBuilder(lab).withDeadline(newTime.value).build();
        // check if recurrence is successfully added and isSameTask property still holds
        Task newTask = Task.makeNextRecurringTask(lab, validOptionalRecurrence);
        assertEquals(newTask.getRecurrence(), recurredLab.getRecurrence());
        assertTrue(newTask.isSameTask(recurredLab));

        //EP: recurrence is invalid
        String invalidRecurrenceString = "hourly";
        assertThrows(IllegalArgumentException.class, () -> Task.makeNextRecurringTask(lab,
                new OptionalField<Recurrence>(new Recurrence(invalidRecurrenceString))));

        //EP: Task is null
        assertThrows(NullPointerException.class, () -> Task.makeNextRecurringTask(null,
                validOptionalRecurrence));
    }

    @Test
    public void updateRecurrenceTaskTest() {
        //EP: task is null
        assertThrows(NullPointerException.class, () -> Task.updateRecurrenceTask(null));

        // Recurring task. deadline updates successfully
        Task lab = new TaskBuilder(LAB).withRecurrence(VALID_RECURRENCE_LAB).build();
        Task taskToCheckWith = new TaskBuilder(lab)
                .withDeadline(Task.getRecurringTime(lab, lab.getDeadline()).value)
                .build();

        assertTrue(taskToCheckWith.equals(Task.updateRecurrenceTask(lab)));

        //EP: Non-recurring task. assertion error thrown as task needs to be recurring
        assertThrows(AssertionError.class, () -> LAB.equals(Task.updateRecurrenceTask(LAB)));

    }

    @Test
    public void getRecurringTimeTest() {
        //EP: task is null
        assertThrows(NullPointerException.class, () -> Task.getRecurringTime(null, LAB.getDeadline()));

        //EP: oldTime is null
        assertThrows(AssertionError.class, () -> Task.getRecurringTime(LAB, null));

        //EP: task is not recurring
        assertThrows(AssertionError.class, () -> Task.getRecurringTime(LAB, LAB.getDeadline()));

        Task recurringLab = new TaskBuilder(LAB).withRecurrence(VALID_RECURRENCE_LAB).build();
        //EP: deadline is not expired
        Time nonExpiredDeadlineTime = Time.makeTimeObject(LocalDateTime.now());
        Task nonExpiredRecurringLab = new TaskBuilder(recurringLab).withDeadline(nonExpiredDeadlineTime.value).build();

        assertTrue(nonExpiredRecurringLab.getDeadline().equals(nonExpiredDeadlineTime));

        //EP: deadline is expired
        Time expiredDeadlineTime = Time.makeTimeObject(LocalDateTime.now().minusDays(1));
        Task expiredRecurringLab = new TaskBuilder(recurringLab).withDeadline(expiredDeadlineTime.value).build();

        assertTrue(Task.getRecurringTime(expiredRecurringLab, expiredDeadlineTime).equals(nonExpiredDeadlineTime));
    }
}
