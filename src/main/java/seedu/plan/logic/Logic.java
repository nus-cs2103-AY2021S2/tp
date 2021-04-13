package seedu.plan.logic;

import java.nio.file.Path;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.logic.commands.CommandResult;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.logic.parser.exceptions.ParseException;
import seedu.plan.model.Model;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.plan.Plan;
import seedu.plan.storage.JsonModule;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getPlans()
     */
    ReadOnlyModulePlanner getModulePlanner();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Plan> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getModulePlannerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns an unmodifiable view of module info */
    ObservableList<JsonModule> getModuleInfoList();

    /** Returns a command that would change the panel list view */
    public StringProperty getDisplayPanelListCommand();

    /** Returns an unmodifiable view of a single module info */
    ObservableList<JsonModule> getSingleModuleInfoList();
}
