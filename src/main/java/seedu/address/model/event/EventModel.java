package seedu.address.model.event;

import java.util.function.Predicate;

import javafx.collections.ObservableList;

public interface EventModel {
    Predicate<Event> PREDICATE_SHOW_ALL_EVENT = unused -> true;

    ObservableList<Event> getFilteredEventList();
}
