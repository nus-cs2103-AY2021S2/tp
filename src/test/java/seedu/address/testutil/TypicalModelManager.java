package seedu.address.testutil;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TypicalModelManager {

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static ModelManager getTypicalModelManager() {
        ModelManager modelManager =
            new ModelManager(TypicalAppointments.getTypicalAppointmentBook(),
                TypicalProperties.getTypicalPropertyBookWithClient(), new UserPrefs());
        return modelManager;
    }
}
