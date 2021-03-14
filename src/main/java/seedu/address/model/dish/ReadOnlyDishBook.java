package seedu.address.model.dish;

import javafx.collections.ObservableList;
import seedu.address.model.Book;

public interface ReadOnlyDishBook extends Book {
    ObservableList<Dish> getDishList();
}
