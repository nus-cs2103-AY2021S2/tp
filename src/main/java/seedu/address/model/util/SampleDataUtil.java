package seedu.address.model.util;

import seedu.address.model.EventBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * Contains utility methods for populating {@code EventBook} with sample data.
 */
public class SampleDataUtil {


    public static ReadOnlyEventBook getSampleEventBook() {
        EventBook sampleEb = new EventBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleEb.addEvent(sampleEvent);
        }
        return sampleEb;
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventName("CS2107 Finals"), EventStatus.BACKLOG,
                    EventPriority.LOW, new Description("Finals on 3rd May 2021")),
            new Event(new EventName("CS2030 Lab 1"), EventStatus.TODO,
                    EventPriority.MEDIUM, new Description("Lab 1 to complete")),
            new Event(new EventName("CS2103T TP"), EventStatus.IN_PROGRESS,
                    EventPriority.HIGH, new Description("Team Project for CS2103T")),
            new Event(new EventName("CS2105 Assignment 1"), EventStatus.IN_PROGRESS,
                    EventPriority.MEDIUM, new Description("Due on 28 Mar 2021")),
            new Event(new EventName("CS2101 OP1"), EventStatus.DONE,
                    EventPriority.HIGH, new Description("OP1 Preparations")) };

    }

}
