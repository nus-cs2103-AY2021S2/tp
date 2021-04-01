package seedu.iscam.model.util.meetingbook;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import seedu.iscam.model.meeting.Meeting;

/**
 * Observable Meeting object that will notify listeners when the meeting contained changes.
 */
public class ObservableMeeting implements ObservableObjectValue<Meeting> {
    private Meeting meeting;
    private final List<ChangeListener<? super Meeting>> listeners = new ArrayList<>();

    public void setMeeting(Meeting newMeeting) {
        Meeting oldMeeting = this.meeting;
        this.meeting = newMeeting;
        for (ChangeListener<? super Meeting> cl : listeners) {
            cl.changed(this, oldMeeting, newMeeting);
        }
    }

    @Override
    public Meeting get() {
        return meeting;
    }

    @Override
    public void addListener(ChangeListener<? super Meeting> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super Meeting> listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public Meeting getValue() {
        return meeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            ObservableMeeting that = (ObservableMeeting) o;
            return this.equals(that.meeting) && this.listeners.equals(that.listeners);
        }
    }
}
