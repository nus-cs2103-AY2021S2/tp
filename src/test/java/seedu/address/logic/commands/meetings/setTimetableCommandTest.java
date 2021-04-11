package seedu.address.logic.commands.meetings;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;

import java.time.LocalDate;

import static seedu.address.logic.commands.persons.PersonCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteBook;

public class setTimetableCommandTest {
    private Model model = new ModelManager(new AddressBook(), new MeetingBook(),
            getTypicalNoteBook(), new UserPrefs(), new PersonMeetingConnection());

    @Test
    public void execute_setTimetableDate() {
        LocalDate localDate = LocalDate.of(2021, 3, 3);
        SetTimetableCommand validSetTimetableCommand = new SetTimetableCommand(localDate);
        String expectedMessage = String.format(SetTimetableCommand.MESSAGE_SUCCESS,
                DateTimeUtil.prettyPrintFormatLocalDate(localDate));
        ModelManager expectedModel = new ModelManager(new AddressBook(), new MeetingBook(),
                model.getNoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel.setTimetableStartDate(localDate);
        assertCommandSuccess(validSetTimetableCommand, model, expectedMessage, expectedModel);
    }


}
