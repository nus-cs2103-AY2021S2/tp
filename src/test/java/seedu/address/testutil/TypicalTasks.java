package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

public class TypicalTasks {
    public static final Task HOMEWORK = new TaskBuilder().withTitle("Homework").withDescription("do CS2103tp")
            .build();
    public static final Task RETURNBOOK = new TaskBuilder().withTitle("Return a book")
            .withDescription("Go to National Library").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task MARATHON = new TaskBuilder().withTitle(VALID_TITLE_MARATHON)
            .withDescription(VALID_DESCRIPTION_MARATHON).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, RETURNBOOK));
    }
}
