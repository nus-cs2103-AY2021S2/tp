package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.common.Date;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * An event is considered unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and updating of
 * Event uses Event#isSameEvent(Event) for equality so as to ensure that the event being added or updated is
 * unique in terms of identity in the UniqueEventList. However, the removal of a event uses Event#equals(Object) so
 * as to ensure that the event with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent test as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Returns true if the event list is empty.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Adds a event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the list.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        if (!target.isSameEvent(editedEvent) && contains(editedEvent)) {
            throw new DuplicateEventException();
        }

        internalList.set(index, editedEvent);
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    /**
     * Returns number of events happening in the next 7 days.
     */
    public int getNumIncomingEvents() {
        int numIncomingEvents = 0;
        int numTotalEvents = internalList.size();
        LocalDate now = LocalDate.now();
        String nowStr = now.format(DATE_FORMATTER);
        LocalDate inSevenDays = now.plusDays(7);
        String inSevenDaysStr = inSevenDays.format(DATE_FORMATTER);
        for (int i = 0; i < numTotalEvents; i++) {
            Event currentEvent = internalList.get(i);
            if (currentEvent.getStartDate().compareTo(new Date(inSevenDaysStr)) <= 0
                    && currentEvent.getStartDate().compareTo(new Date(nowStr)) > 0) {
                numIncomingEvents++;
            }
        }
        return numIncomingEvents;
    }

    /**
     * Clears expired events (end date time past).
     */
    public void clearExpired() {
        internalList.removeIf(event -> !event.isEndDateTimeBeforeNow());
    }

    /**
     * Returns status of time in a day, with 0 being free, 1 being busy.
     */
    public int[] getTimeStatus(Date date) {
        int [] timeStatus = new int[1440];
        //initialise array to 0 (all minutes in the day are free)
        for (int i = 0; i < 1440; i++) {
            timeStatus[i] = 0;
        }

        int numTotalEvents = internalList.size();
        for (int i = 0; i < numTotalEvents; i++) {
            Event currEvent = internalList.get(i);
            if ((date.compareTo(currEvent.getStartDate()) >= 0) && (date.compareTo(currEvent.getEndDate()) <= 0)) {
                if ((date.equals(currEvent.getStartDate())) && (date.equals(currEvent.getEndDate()))) {
                    //event within the day
                    int startTimeCount = getTimeMinuteCount(currEvent.getStartTime());
                    int endTimeCount = getTimeMinuteCount(currEvent.getEndTime());
                    for (int j = startTimeCount; j <= endTimeCount; j++) {
                        timeStatus[j] = 1;
                    }
                } else if ((date.compareTo(currEvent.getStartDate()) > 0) && (date.equals(currEvent.getEndDate()))) {
                    //event started earlier and ends on the day
                    int endTimeCount = getTimeMinuteCount(currEvent.getEndTime());
                    for (int j = 0; j <= endTimeCount; j++) {
                        timeStatus[j] = 1;
                    }
                } else if ((date.equals(currEvent.getStartDate())) && (date.compareTo(currEvent.getEndDate()) < 0)) {
                    //event starts on the day and ends in the future
                    int startTimeCount = getTimeMinuteCount(currEvent.getStartTime());
                    for (int j = startTimeCount; j < 1440; j++) {
                        timeStatus[j] = 1;
                    }
                } else {
                    //event starts before the day and ends in the future
                    for (int j = 0; j < 1440; j++) {
                        timeStatus[j] = 1;
                    }
                }
            }
        }
        return timeStatus;
    }

    /**
     * Returns minute count of time from 00:00.
     */
    public int getTimeMinuteCount(Time time) {
        String timeStr = time.toString();
        String hourStr = timeStr.split(":")[0];
        String minuteStr = timeStr.split(":")[1];
        int hour = Integer.valueOf(hourStr);
        int minute = Integer.valueOf(minuteStr);
        int minuteCount = hour * 60 + minute;
        return minuteCount;
    }

    /**
     * Returns time from minute count.
     */
    public Time getTimeFromCount(int minuteCount) {
        int minute = minuteCount % 60;
        int hour = (minuteCount - minute) / 60;
        String minuteStr = String.valueOf(minute);
        String hourStr = String.valueOf(hour);
        if (minute < 10) {
            minuteStr = "0" + minuteStr;
        }
        if (hour < 10) {
            hourStr = "0" + hourStr;
        }
        String timeStr = hourStr + ":" + minuteStr;
        Time time = new Time(timeStr);
        return time;
    }

    /**
     * Returns a list of index.
     */
    public ArrayList<Integer> getFreeTimeIndex(int[] timeStatus) {
        ArrayList<Integer> timeIndex = new ArrayList<>();
        ArrayList<Integer> timeStatusArr = new ArrayList<>();
        int len = timeStatus.length;

        for (int i = 0; i < len; i++) {
            timeStatusArr.add(timeStatus[i]);
        }
        int status = timeStatusArr.get(0);


        int index = timeStatusArr.indexOf(toggle(status));
        while (index >= 0) {
            timeIndex.add(index);
            for (int i = 0; i < index; i++) {
                timeStatusArr.set(i, toggle(status));
            }
            status = timeStatusArr.get(0);
            index = timeStatusArr.indexOf(toggle(status));
        }
        return timeIndex;
    }

    /**
     * Adds time slot string to ArrayList.
     */
    public void addTimeSlot(int firstBound, int secondBound, ArrayList<String> freeTimeSlots) {
        Time firstTime = getTimeFromCount(firstBound);
        Time secondTime = getTimeFromCount(secondBound);
        String timeSlotStr;
        if (firstBound == secondBound) {
            timeSlotStr = firstTime.toString();
        } else {
            timeSlotStr = firstTime.toString() + " to " + secondTime.toString();
        }
        freeTimeSlots.add(timeSlotStr);
    }

    /**
     * Adds a list of free time slots in for loop when start status is free.
     */
    public void loopTimeSlotsFree(ArrayList<Integer> timeIndex, ArrayList<String> freeTimeSlots, int len) {
        int firstBound = 0;
        int secondBound = timeIndex.get(0) - 1;
        addTimeSlot(firstBound, secondBound, freeTimeSlots);
        for (int i = 1; i < timeIndex.size() - 1; i = i + 2) {
            firstBound = timeIndex.get(i);
            secondBound = timeIndex.get(i + 1) - 1;
            addTimeSlot(firstBound, secondBound, freeTimeSlots);
        }
        if (timeIndex.size() % 2 == 0) {
            firstBound = timeIndex.get(timeIndex.size() - 1);
            secondBound = len - 1;
            addTimeSlot(firstBound, secondBound, freeTimeSlots);
        }
    }

    /**
     * Adds a list of free time slots in for loop when start status is busy.
     */
    public void loopTimeSlotsBusy(ArrayList<Integer> timeIndex, ArrayList<String> freeTimeSlots, int len) {
        for (int i = 0; i < timeIndex.size() - 1; i = i + 2) {
            int firstBound = timeIndex.get(i);
            int secondBound = timeIndex.get(i + 1) - 1;
            addTimeSlot(firstBound, secondBound, freeTimeSlots);
        }
        if (timeIndex.size() % 2 == 1) {
            int firstBound = timeIndex.get(timeIndex.size() - 1);
            int secondBound = len - 1;
            addTimeSlot(firstBound, secondBound, freeTimeSlots);
        }
    }


    /**
     * Returns a list of free time slots.
     */
    public ArrayList<String> getFreeTimeSlots(Date date) {
        int startStatus = getTimeStatus(date)[0];
        int len = getTimeStatus(date).length;
        ArrayList<Integer> timeIndex = getFreeTimeIndex(getTimeStatus(date));
        ArrayList<String> freeTimeSlots = new ArrayList<>();
        if (timeIndex.size() != 0) {
            if (startStatus == 0) {
                loopTimeSlotsFree(timeIndex, freeTimeSlots, len);
            } else {
                loopTimeSlotsBusy(timeIndex, freeTimeSlots, len);
            }
        } else {
            if (startStatus == 0) {
                String timeSlotStr = "The entire day is free!";
                freeTimeSlots.add(timeSlotStr);
            }
        }
        return freeTimeSlots;
    }

    /**
     * Returns inverted bit.
     */
    public int toggle(int bit) {
        if (bit == 0) {
            return 1;
        } else {
            return 0;
        }
    }



    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEventList // instanceof handles nulls
                && internalList.equals(((UniqueEventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
