package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TypicalModelManager {

    /**
     * Returns a {@code ModelManager} with the typical appointment book and property book.
     */
    public static ModelManager getTypicalModelManager() {
        return new ModelManager(getTypicalAppointmentBook(), getTypicalPropertyBook(), new UserPrefs());
    }

}
