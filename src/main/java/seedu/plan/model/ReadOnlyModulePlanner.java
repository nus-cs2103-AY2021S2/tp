package seedu.plan.model;

import javafx.collections.ObservableList;
import seedu.plan.model.plan.Plan;
import seedu.plan.storage.JsonModule;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyModulePlanner {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Plan> getPersonList();

    /**
     * Returns current semester number.
     */
    Integer getCurrentSemesterNumber();

    /**
     * Returns　an array of module information from moduleinfo.json
     */
    JsonModule[] getModuleInfo();

    void setFoundModule(JsonModule foundModule);

    ObservableList<JsonModule> getFoundModule();
}
