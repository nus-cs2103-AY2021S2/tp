package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ContactsListStorage, UserPrefsStorage, NoteBookStorage,
        DictionaryStorage, DefinitionBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getContactsListFilePath();

    @Override
    Optional<ReadOnlyContactsList> readContactsList() throws DataConversionException, IOException;

    @Override
    void saveContactsList(ReadOnlyContactsList addressBook) throws IOException;

    @Override
    Path getNoteBookFilePath();

    @Override
    Optional<ReadOnlyNoteBook> readNoteBook() throws DataConversionException, IOException;

    @Override
    void saveNoteBook(ReadOnlyNoteBook noteBook) throws IOException;

    @Override
    Path getDictionaryFilePath();

    @Override
    Optional<ReadOnlyDictionary> readDictionary() throws DataConversionException, IOException;

    @Override
    void saveDictionary(ReadOnlyDictionary dictionary) throws IOException;

    @Override
    Path getDefinitionBookFilePath();

    @Override
    Optional<ReadOnlyDefinitionBook> readDefinitionBook() throws DataConversionException, IOException;

    @Override
    void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook) throws IOException;

}
