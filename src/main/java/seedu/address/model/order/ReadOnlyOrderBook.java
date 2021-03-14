package seedu.address.model.order;

import javafx.collections.ObservableList;
import seedu.address.model.Book;

public interface ReadOnlyOrderBook extends Book {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Order> getOrderList();

}
