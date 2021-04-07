package seedu.address.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.VALID_GENERAL_EVENT_1;
import static seedu.address.testutil.TypicalRemindMe.VALID_GENERAL_EVENT_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.GeneralEvent;
import seedu.address.testutil.ModelStub;

public class AddEventCommandTest {
    private AddEventCommand addEventCommandDummy = new AddEventCommand(VALID_GENERAL_EVENT_1);

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        ModelStubWithEvent modelStubWithEvent = new ModelStubWithEvent();

        assertThrows(CommandException.class,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT, () -> addEventCommandDummy.execute(modelStubWithEvent));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithoutEvent modelStubWithoutEvent = new ModelStubWithoutEvent();

        CommandResult commandResult = addEventCommandDummy.execute(modelStubWithoutEvent);
        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, VALID_GENERAL_EVENT_1),
                commandResult.getFeedbackToUser());
        assertTrue(modelStubWithoutEvent.isEventAdded);
    }

    @Test
    public void equals() {
        AddEventCommand addEventCommand1 = new AddEventCommand(VALID_GENERAL_EVENT_1);
        AddEventCommand addEventCommand2 = new AddEventCommand(VALID_GENERAL_EVENT_2);

        //same object -> returns true
        assertTrue(addEventCommand1.equals(addEventCommand1));

        //same values -> returns true
        AddEventCommand addEventCommand1Copy = new AddEventCommand(VALID_GENERAL_EVENT_1);
        assertTrue(addEventCommand1.equals(addEventCommand1Copy));

        // different types -> returns false
        assertFalse(addEventCommand1.equals(1));

        // null -> returns false
        assertFalse(addEventCommand1.equals(null));

        // different event -> returns false
        assertFalse(addEventCommand1.equals(addEventCommand2));
    }

    private class ModelStubWithEvent extends ModelStub {
        @Override
        public boolean hasEvent(GeneralEvent event) {
            return true;
        }
    }

    private class ModelStubWithoutEvent extends ModelStub {
        private boolean isEventAdded = false;

        @Override
        public boolean hasEvent(GeneralEvent event) {
            return false;
        }

        @Override
        public void addEvent(GeneralEvent event) {
            isEventAdded = true;
        }
    }
}
