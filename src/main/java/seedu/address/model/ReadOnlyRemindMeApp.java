package seedu.address.model;

import java.util.List;

import seedu.address.model.module.Module;

/**
 * Unmodifiable view of the RemindMe App.
 */
public interface ReadOnlyRemindMeApp {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     * @return
     */
    List<Module> getModuleList();
}
