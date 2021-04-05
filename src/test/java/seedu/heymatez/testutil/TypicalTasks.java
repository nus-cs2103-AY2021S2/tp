package seedu.heymatez.testutil;

import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_ASSIGNEE_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_ASSIGNEE_MEETING;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PRIORITY_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_STATUS_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task HOMEWORK = new TaskBuilder().withTitle("Homework").withDescription("do CS2103tp")
            .withDeadline("2021-02-04").withTaskStatus("uncompleted").withPriority("unassigned").build();

    public static final Task RETURNBOOK = new TaskBuilder().withTitle("Return a book")
            .withDescription("Go to National Library").withDeadline("2021-05-04").withTaskStatus("uncompleted")
            .withPriority("unassigned").withAssignees(VALID_ASSIGNEE_MEETING).build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task MARATHON = new TaskBuilder().withTitle(VALID_TITLE_MARATHON)
            .withDescription(VALID_DESCRIPTION_MARATHON).withDeadline("2021-05-06")
            .withTaskStatus(VALID_STATUS_MARATHON).withPriority(VALID_PRIORITY_MARATHON)
            .withAssignees(VALID_ASSIGNEE_MARATHON).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code HeyMatez} with all the typical tasks.
     */
    public static HeyMatez getTypicalHeyMatez() {
        HeyMatez hm = new HeyMatez();
        for (Task task : getTypicalTasks()) {
            hm.addTask(task);
        }

        for (Person person : getTypicalPersons()) {
            hm.addPerson(person);
        }

        return hm;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, RETURNBOOK, MARATHON));
    }
}
