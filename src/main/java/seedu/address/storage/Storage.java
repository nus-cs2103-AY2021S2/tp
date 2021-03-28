package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BudgetBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, AppointmentBookStorage, GradeBookStorage,
        UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getAppointmentBookFilePath();

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook addressBook) throws IOException;

    BudgetBook readBudgetBook();

    void saveBudgetBook(BudgetBook budgetBook) throws IOException;

    @Override
    Path getGradeBookFilePath();

    @Override
    Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException, IOException;

    @Override
    void saveGradeBook(ReadOnlyGradeBook gradeBook) throws IOException;

}
