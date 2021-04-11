package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.CLASS_MEETING;
import static seedu.address.testutil.TypicalEntries.CONSULTATION;
import static seedu.address.testutil.TypicalEntries.EXTRA_CLASS;
import static seedu.address.testutil.TypicalEntries.getTypicalEntriesList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistant;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.exceptions.OverdueEntryException;
import seedu.address.model.entry.exceptions.OverlappingEntryException;
import seedu.address.testutil.EntryBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddEntryCommand}.
 */
public class AddEntryCommandTest {
    private Model emptyModel = new ModelManager(new TeachingAssistant(), new UserPrefs());
    private Model model = new ModelManager(getTypicalEntriesList(), new UserPrefs());

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEntryCommand(null));
    }

    @Test
    public void equals() {
        Entry copiedConsultation = new EntryBuilder(CONSULTATION).build();

        AddEntryCommand addEntryCommand0 = new AddEntryCommand(CONSULTATION);
        AddEntryCommand addEntryCommand1 = new AddEntryCommand(CONSULTATION);
        AddEntryCommand addEntryCommand2 = new AddEntryCommand(copiedConsultation);
        AddEntryCommand addEntryCommand3 = new AddEntryCommand(CLASS_MEETING);

        assertEquals(addEntryCommand0, addEntryCommand1);
        assertEquals(addEntryCommand0, addEntryCommand2);
        assertNotEquals(addEntryCommand0, addEntryCommand3);
        assertNotEquals(addEntryCommand1, addEntryCommand3);
    }

    @Test
    public void execute_nonOverlappingEntry_commandSuccess() {
        // totally disjoint entries
        emptyModel.addEntry(CLASS_MEETING);
        emptyModel.addEntry(CONSULTATION);

        // one's start date is the same as the other's end date
        Entry edgeCaseEntry0 = new EntryBuilder(EXTRA_CLASS)
                .withStartDate("2022-02-01 16:30")
                .build();
        emptyModel.addEntry(edgeCaseEntry0);

        // one's end date is the same as the other's start date
        Entry edgeCaseEntry1 = new EntryBuilder(CONSULTATION)
                .withStartDate("2022-01-01 01:00")
                .withEndDate("2022-02-01 13:00")
                .build();
        emptyModel.addEntry(edgeCaseEntry1);
    }

    @Test
    public void execute_sameEntryObjects_throwsOverlappingEntryException() {
        emptyModel.addEntry(CONSULTATION);
        assertThrows(OverlappingEntryException.class, () -> emptyModel.addEntry(CONSULTATION));
        assertThrows(OverlappingEntryException.class, () -> model.addEntry(CONSULTATION));
    }

    @Test
    public void execute_sameFieldEntryObjects_throwsOverlappingEntryException() {
        emptyModel.addEntry(CONSULTATION);
        Entry copiedConsultation = new EntryBuilder(CONSULTATION).build();

        assertThrows(OverlappingEntryException.class, () -> emptyModel.addEntry(copiedConsultation));
        assertThrows(OverlappingEntryException.class, () -> model.addEntry(copiedConsultation));
    }

    @Test
    public void execute_overlappingEntryObjects_throwsOverlappingEntryException() {
        Entry overlappingEntry0 = new EntryBuilder(CONSULTATION)
                .withStartDate("2022-02-01 14:00")
                .withEndDate("2022-02-01 15:00")
                .build();
        Entry overlappingEntry1 = new EntryBuilder(CONSULTATION)
                .withStartDate("2022-02-04 08:15")
                .withEndDate("2022-02-04 09:15")
                .build();

        emptyModel.addEntry(CONSULTATION);
        assertThrows(OverlappingEntryException.class, () -> emptyModel.addEntry(overlappingEntry0));
        assertThrows(OverlappingEntryException.class, () -> model.addEntry(overlappingEntry1));
    }

    @Test
    public void execute_overdueEntry_throwsOverdueEntryException() {
        Entry overdueEntry = new EntryBuilder(CONSULTATION)
                .withStartDate("2000-01-01 01:00")
                .withEndDate("2000-01-01 02:00")
                .build();
        assertThrows(OverdueEntryException.class, () -> emptyModel.addEntry(overdueEntry));
    }

}
