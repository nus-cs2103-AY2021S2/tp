package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.logic.Logic;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;

/**
 * A Calendar storage to extract events from {@code Logic} and store them for future calendar usage.
 */
public class CalendarStorage {
    private HashMap<LocalDate, EventList> storage;
    private ArrayList<Birthday> birthdays;
    private Logic logic;

    /**
     * Constructs new storage for the calendar of RemindMe.
     *
     * @param logic Logic that calendar storage uses.
     */
    public CalendarStorage(Logic logic) {
        storage = new HashMap<>();
        this.logic = logic;
        this.birthdays = new ArrayList<>();
    }

    /**
     * Returns sorted {@code Event} in a {@code EventsList} that occurs on a certain date.
     *
     * @param date Date which event occurs.
     * @return {@code EventList} that contains all events for a certain date.
     */
    public EventList getDateEvents(LocalDate date) {
        EventList events = new EventList();

        //Add assignments, exams, general events to returning event list
        if (isDateOccupied(date)) {
            events = storage.get(date);
        }

        //Add birthday to returning event list
        for (Birthday bday : birthdays) {
            if (bday.isDate(date)) {
                events.add(bday);
            }
        }

        //Sort the event according to time
        events.sort();

        return events;
    }

    /**
     * Stores the event via date into storage.
     *
     * @param date Date in which the event occurs.
     * @param event Event that occurs.
     */
    public void storeEvent(LocalDate date, Event event) {
        EventList events = storage.getOrDefault(date, new EventList());
        events.add(event);
        storage.put(date, events);
    }

    /**
     * Checks if calendar date has any assignments, exams, general events.
     *
     * @param date Date to check in calendar.
     * @return True if date occupied, else False.
     */
    private boolean isDateOccupied(LocalDate date) {
        return storage.containsKey(date);
    }

    /**
     * Refreshes storage with the latest information for the Calendar.
     */
    public void refreshStorage() {
        clearStorage();
        loadModuleEvents();
        loadGeneralEvents();
        loadBirthdays();
    }

    private void clearStorage() {
        storage.clear();
        birthdays.clear();
    }

    private void loadModuleEvents() {
        ObservableList<Module> moduleList = logic.getFilteredModuleList();
        for (Module m : moduleList) {
            ExamList exams = m.getExams();
            for (Exam e : exams.getExams()) {
                LocalDate date = e.getDate();
                storeEvent(date, e);
            }
            AssignmentList assignments = m.getAssignments();
            for (Assignment a: assignments.getAssignments()) {
                LocalDate date = a.getDate();
                storeEvent(date, a);
            }
        }
    }

    private void loadGeneralEvents() {
        ObservableList<GeneralEvent> generalEventList = logic.getFilteredEventList();
        for (GeneralEvent ge : generalEventList) {
            LocalDate date = ge.getDate();
            storeEvent(date, ge);
        }
    }

    // As Birthdays can occur every year, we cannot simply add the birthday into the hashmap storage.
    // The year of the birth date would not be the same as the current year, meaning that Birthday
    // would not be able to be stored on the day itself of any year except the year the person was born.
    // Therefore birthday is stored in a separate list.
    private void loadBirthdays() {
        ObservableList<Person> personList = logic.getFilteredPersonList();
        for (Person p : personList) {
            Birthday bday = p.getBirthday();
            birthdays.add(bday);
        }
    }
}
