package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event CS2030 = new EventBuilder().withName("CS2030")
            .withDescription("Object oriented Programming module")
            .withStatus(EventStatus.DONE)
            .withPriority(EventPriority.HIGH)
            .build();
    public static final Event CS2107 = new EventBuilder().withName("CS2107")
            .withDescription("Introduction to Information Security")
            .withStatus(EventStatus.IN_PROGRESS).build();
    public static final Event CS1010S = new EventBuilder().withName("CS1010S")
            .withDescription("Programming Methodology I")
            .withStatus(EventStatus.DONE).build();
    public static final Event COMPLETE_ASSIGNMENT = new EventBuilder()
            .withName("Complete Assignment 1")
            .withDescription("Assignment from CS2030")
            .withStatus(EventStatus.TODO).build();

    //Manually added
    public static final Event CS3230 = new EventBuilder().withName("CS3230")
            .withDescription("Design and Analysis of Algorithms")
            .withStatus(EventStatus.IN_PROGRESS).build();

    public static final Event CS2100 = new EventBuilder().withName("CS2100")
            .withDescription("Computer Organisation")
            .withStatus(EventStatus.DONE).build();

    TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }
        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(CS2030, CS2107,
                CS1010S, COMPLETE_ASSIGNMENT));
    }
}
