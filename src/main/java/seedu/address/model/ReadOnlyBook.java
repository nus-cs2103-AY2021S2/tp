package seedu.address.model;

import javafx.collections.ObservableList;

public interface ReadOnlyBook<T extends Item> {
    ObservableList<T> getItemList();
}
