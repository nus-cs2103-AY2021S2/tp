package seedu.dictionote.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.logic.commands.AddContentCommand;
import seedu.dictionote.logic.commands.AddDefinitionCommand;
import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.logic.commands.Command;
import seedu.dictionote.logic.commands.CommandResult;
import seedu.dictionote.logic.commands.DeleteNoteCommand;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;
import seedu.dictionote.logic.commands.MarkAsUndoneNoteCommand;
import seedu.dictionote.logic.commands.SortNoteCommand;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.logic.parser.DictionoteParser;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ReadOnlyAddressBook;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.storage.Storage;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final DictionoteParser dictionoteParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        dictionoteParser = new DictionoteParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = dictionoteParser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            if (command instanceof AddNoteCommand || command instanceof DeleteNoteCommand
                || command instanceof EditNoteCommand || command instanceof MarkAsDoneNoteCommand
                || command instanceof SortNoteCommand || command instanceof MarkAsUndoneNoteCommand) {
                storage.saveNoteBook(model.getNoteBook());
            } else if (command instanceof AddContentCommand) {
                storage.saveDictionary(model.getDictionary());
            } else if (command instanceof AddDefinitionCommand) {
                storage.saveDefinitionBook(model.getDefinitionBook());
            } else {
                storage.saveAddressBook(model.getAddressBook());
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return model.getFilteredNoteList();
    }

    @Override
    public ObservableList<Content> getFilteredContentList() {
        return model.getFilteredContentList();
    }

    @Override
    public ObservableList<Definition> getFilteredDefinitionList() {
        return model.getFilteredDefinitionList();
    }

    @Override
    public ObservableList<? extends DisplayableContent> getFilteredCurrentDictionaryList() {
        return model.getFilteredCurrentDictionaryList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void setDictionaryContentConfig(DictionaryContentConfig dictionaryContentConfig) {
        model.setDictionaryContentConfig(dictionaryContentConfig);
    }

    @Override
    public void setNoteContentConfig(NoteContentConfig noteContentConfig) {
        model.setNoteContentConfig(noteContentConfig);
    }
}
