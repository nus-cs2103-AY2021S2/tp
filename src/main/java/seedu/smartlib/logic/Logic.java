package seedu.smartlib.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.logic.commands.CommandResult;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;

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
     * Returns the SmartLib.
     *
     * @see seedu.smartlib.model.Model#getSmartLib()
     */
    ReadOnlySmartLib getSmartLib();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Reader> getFilteredReaderList();

    /** Returns an unmodifiable view of the filtered list of books */
    ObservableList<Book> getFilteredBookList();

    /**
     * Returns the user prefs' smartlib file path.
     */
    Path getSmartLibFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
