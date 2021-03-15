package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.garment.Garment;

/**
 * Unmodifiable view of an wardrobe
 */
public interface ReadOnlyWardrobe {

    /**
     * Returns an unmodifiable view of the garments list.
     * This list will not contain any duplicate garments.
     */
    ObservableList<Garment> getGarmentList();

}
