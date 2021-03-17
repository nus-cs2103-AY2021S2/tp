package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.logic.Logic;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;

/**
 * A Calendar storage using hash map to store date map to events of that date.
 */
public class CalendarStorage {
    private HashMap<LocalDate, EventList> storage;
    private ArrayList<Birthday> birthdays;
    private Logic logic;

    /**
     * Creates new storage for Calendar.
     * @param logic that calender uses.
     */
    public CalendarStorage(Logic logic) {
        storage = new HashMap<>();
        this.logic = logic;
        this.birthdays = new ArrayList<>();
    }

    /**
     * Returns events in a events list that occurs on a certain date.
     * @param date the date which event occurs.
     * @return event list that contains all events for a certain date.
     */
    public EventList getDateEvents(LocalDate date) {
        EventList events = new EventList();
        if (!isDateFree(date)) {
            events = storage.get(date);
        }

        for (Birthday bday : birthdays) {
            if (bday.isDate(date)) {
                events.add(bday);
            }
        }

        return events;
    }

    /**
     * Store the event via date into storage.
     * @param date date in which the event occurs.
     * @param event event that occurs.
     */
    public void storeEvent(LocalDate date, Event event) {
        EventList events = storage.getOrDefault(date, new EventList());
        events.add(event);
        storage.put(date, events);
    }

    /**
     * Check if calendar date is free of events.
     * @param date date to check in calendar.
     * @return true if date free, else return false.
     */
    public boolean isDateFree(LocalDate date) {
        return !storage.containsKey(date);
    }

    /**
     * At the start of calendar of RemindMe, start up the storage.
     */
    public void refreshStorage() {
        storage.clear();
        loadModuleEvents();
        birthdays.clear();
        loadBirthdays();
    }

    public ArrayList<Birthday> getBirthdays() {
        return new ArrayList<>(birthdays);
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

    private void loadBirthdays() {
        ObservableList<Person> personList = logic.getFilteredPersonList();
        for (Person p : personList) {
            Birthday bday = p.getBirthday();
            birthdays.add(bday);
        }
    }
}
