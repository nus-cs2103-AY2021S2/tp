package seedu.address.model.event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;

/**
 * EventTracker is used to check for conflicting Appointment or Schedule when retrieving data from storage.
 */
public class EventTracker {
    private final ReadOnlyAppointmentBook appointmentBook;
    private final ReadOnlyScheduleTracker scheduleTracker;

    /**
     * Primary constructor of {@code EventTracker}
     */
    public EventTracker(ReadOnlyAppointmentBook appointmentBook, ReadOnlyScheduleTracker scheduleTracker) {
        this.appointmentBook = appointmentBook;
        this.scheduleTracker = scheduleTracker;
    }

    /**
     * Combine {@code AppointmentList} and {@code ScheduleList} into eventList for predicate purpose.
     * @return
     */
    private FilteredList<Event> getEventList() {
        ObservableList<Event> eventList = FXCollections.observableArrayList();
        eventList.addAll(appointmentBook.getAppointmentList());
        eventList.addAll(scheduleTracker.getScheduleList());
        return new FilteredList<>(eventList);
    }

    /**
     * Returns true if there are any clashing dates between Appointment(s) and Schedule(s).
     */
    public boolean hasClashingDateTime() {
        FilteredList<Event> eventList = getEventList();
        return eventList.stream().anyMatch(event ->
                eventList.stream().anyMatch(new DateTimeClashPredicate(event)));
    }
}
