package seedu.us.among.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.exceptions.AbortRequestException;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.Model;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Endpoint;

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
    CommandResult execute(String commandText) throws CommandException, ParseException,
            RequestException, AbortRequestException;

    /**
     * Returns the EndpointList.
     *
     * @see Model#getEndpointList()
     */
    ReadOnlyEndpointList getEndpointList();

    /** Returns an unmodifiable view of the filtered list of endpoints */
    ObservableList<Endpoint> getFilteredEndpointList();

    /**
     * Returns the user prefs' API endpoint list file path.
     */
    Path getEndpointListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
