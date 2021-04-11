package seedu.address.model.schedule;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;


public class TimetablePrefs {
    private final SimpleObjectProperty<LocalDate> timetableStartDate;

    /**
     * Default initialises the timetableStartDate to the localDate.
     */
    public TimetablePrefs(LocalDate localDate) {
        timetableStartDate = new SimpleObjectProperty<LocalDate>(localDate);
    }


    /**
     * Sets the timetableStartDate to a specified date.
     */
    public void setTimetableStartDate(LocalDate localDate) {
        timetableStartDate.setValue(localDate);
    }

    /**
     * Returns the timetable start date wrapped in Observable value wrapper.
     */
    public ObservableValue<LocalDate> getReadOnlyStartDate() {
        return timetableStartDate;
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof TimetablePrefs) {
            TimetablePrefs otherTimetablePrefs =(TimetablePrefs) other;
            return otherTimetablePrefs.timetableStartDate.get()
                    .equals(otherTimetablePrefs.timetableStartDate.get());
        } else {
            return false;
        }
    }

}

