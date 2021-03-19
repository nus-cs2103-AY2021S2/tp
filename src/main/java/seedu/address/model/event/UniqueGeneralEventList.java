package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.event.exceptions.DuplicateGeneralEventException;
import seedu.address.model.event.exceptions.GeneralEventNotFoundException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

public class UniqueGeneralEventList implements Iterable<GeneralEvent> {

    private final ObservableList<GeneralEvent> internalList = FXCollections.observableArrayList();
    private final ObservableList<GeneralEvent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent general event as the {@code toCheck}.
     */
    public boolean contains(GeneralEvent toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds a general event to the list.
     * The general event must not already exist in the list.
     * @param toAdd
     */
    public void add(GeneralEvent toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGeneralEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Gets the general events with the same general event title as {@code event}
     * {@code event} must already exist in the list.
     */
    public GeneralEvent getGeneralEvent(GeneralEvent event) {
        requireNonNull(event);

        Predicate<GeneralEvent> hasGeneralEvent = e -> e.isSameEvent(event);
        FilteredList<GeneralEvent> filteredGeneralEvents = internalList.filtered(hasGeneralEvent);
        if (filteredGeneralEvents.isEmpty()) {
            throw new GeneralEventNotFoundException();
        }
        return filteredGeneralEvents.get(0);
    }

    /**
     * Gets the general event at {@code index}.
     * {@code index} must be within the bounds of the list size.
     */
    public GeneralEvent getGeneralEvent(int index) {
        if (index < 1 || index > size() + 1) {
            throw new GeneralEventNotFoundException();
        }
        return internalList.get(index - 1);
    }

    /**
     * Replaces the generalEvent {@code target} in the list with {@code editedGeneralEvent}.
     * {@code target} must exist in the list.
     * The general event identity of {@code editedGeneralEvent} must not be the same as another
     * existing general event in the list.
     */
    public void setGeneralEvent(GeneralEvent target, GeneralEvent editedGeneralEvent) {
        requireAllNonNull(target, editedGeneralEvent);

        GeneralEvent foundGeneralEvent = getGeneralEvent(target);
        int index = internalList.indexOf(foundGeneralEvent);

        if (hasDuplicate(editedGeneralEvent, foundGeneralEvent)) {
            throw new DuplicateGeneralEventException();
        }

        internalList.set(index, editedGeneralEvent);
    }

    private boolean hasDuplicate(GeneralEvent editedGeneralEvent, GeneralEvent foundGeneralEvent) {
        return !foundGeneralEvent.equals(editedGeneralEvent)
                && contains(editedGeneralEvent);
    }

    /**
     * Removes the equivalent general event from the list.
     * The general event must exist in the list.
     */
    public void remove(GeneralEvent toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public void setGeneralEvents(UniqueGeneralEventList replacedGeneralEvents) {
        requireNonNull(replacedGeneralEvents);
        internalList.setAll(replacedGeneralEvents.internalList);
    }

    /**
     * Replaces the contents of this list with {@code generalEvents}.
     * {@code generalEvents} must not contain duplicate general events.
     */
    public void setGeneralEvents(List<GeneralEvent> generalEvents) {
        requireNonNull(generalEvents);
        if (!areGeneralEventsUnique(generalEvents)) {
            throw new DuplicateGeneralEventException();
        }

        internalList.setAll(generalEvents);
    }

    /**
     * Returns the backing list as an unmodfiable {@code ObservableList}.
     */
    public ObservableList<GeneralEvent> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the number of general events in the list.
     */
    public int size() {
        return internalList.size();
    }

    @Override
    public Iterator<GeneralEvent> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UniqueGeneralEventList)) {
            return false;
        }

        if (other == this) {
            return true;
        }

        UniqueGeneralEventList otherList = (UniqueGeneralEventList) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean areGeneralEventsUnique(List<GeneralEvent> events) {
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
