package seedu.storemando.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.logic.commands.CommandResult;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.Location;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the StoreMando.
     *
     * @see seedu.storemando.model.Model#getStoreMando()
     */
    ReadOnlyStoreMando getStoreMando();

    /**
     * Returns an unmodifiable view of the filtered list of items.
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Returns an unmodifiable list of unique locations of items.
     */
    ObservableList<Location> getLocationList();

    /**
     * Returns a unmodified view of the list of items.
     */
    ObservableList<Item> getItemList();

    /**
     * Returns the user prefs' storemando file path.
     */
    Path getStoreMandoFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
