package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.Sochedule;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }


    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddEventCommandTest.ModelStubAcceptingEventAdded modelStub =
                new AddEventCommandTest.ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_ADD_EVENT_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    private class ModelStubWithEvent extends ModelStub {
        private final Event event;
        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }
        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlySochedule getSochedule() {
            return new Sochedule();
        }
    }
}
