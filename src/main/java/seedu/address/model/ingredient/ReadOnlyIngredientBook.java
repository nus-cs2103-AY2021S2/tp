package seedu.address.model.ingredient;

import javafx.collections.ObservableList;
import seedu.address.model.Book;

public interface ReadOnlyIngredientBook extends Book {
    ObservableList<Ingredient> getIngredientList();
}
