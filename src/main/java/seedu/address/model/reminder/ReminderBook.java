package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;

public class ReminderBook implements ReadOnlyReminderBook{


    private final UniqueReminderList reminders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        reminders = new UniqueReminderList();
    }

    public ReminderBook() {}

    /**
     * Creates an ReminderBook using {@code toBeCopied}
     */
    public ReminderBook(ReadOnlyReminderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public ReminderBook(MeetingBook meetingBook){
        this();
        refreshRemindersFromMeetings(meetingBook);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the reminders
     */
    public void setReminders(List<Reminder> reminders) {
        this.reminders.setReminders(reminders);
    }

    /**
     * Resets the existing data of this {@code ReminderBook} with {@code newData}.
     */
    public void resetData(ReadOnlyReminderBook newData) {
        requireNonNull(newData);
        setReminders(newData.getReminderList());
    }


    public void refreshRemindersFromMeetings(MeetingBook meetingBook){
        ObservableList<Meeting> meetinglist = meetingBook.getMeetingList();
//        List<Reminder> remindersList = meetinglist.stream().map(Reminder::new).sorted(new Comparator<Reminder>() {
//            @Override
//            public int compare(Reminder reminder1, Reminder reminder2) {
//                return reminder1.getRawTimeUntilStart().compareTo(reminder2.getRawTimeUntilStart());
//            }
//        }).collect(Collectors.toList());
        List<Reminder> remindersList = meetinglist.stream().map(Reminder::new).sorted(Comparator.comparing(
                Reminder::getRawTimeUntilStart)).collect(Collectors.toList());
        setReminders(remindersList);
    }

    /**
     * Returns an unmodifiable view of the reminders list.
     */
    @Override
    public ObservableList<Reminder> getReminderList() {
        return this.reminders.asUnmodifiableObservableList();
    }



}
