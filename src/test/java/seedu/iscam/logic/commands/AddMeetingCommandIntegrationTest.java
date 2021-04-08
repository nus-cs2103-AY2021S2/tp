package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.assertMeetingCommandFailure;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMeetingCommand}.
 */
public class AddMeetingCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(),
                new UserPrefs());
    }

    @Test
    public void execute_newMeeting_success() {
        Meeting validMeeting = new MeetingBuilder().build();

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        assertCommandSuccess(new AddMeetingCommand(validMeeting), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }

    @Test
    public void execute_conflictingMeeting_throwsCommandException() {
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(0);
        assertMeetingCommandFailure(new AddMeetingCommand(meetingInList), model,
                AddMeetingCommand.MESSAGE_MEETING_CONFLICT);
    }

}
