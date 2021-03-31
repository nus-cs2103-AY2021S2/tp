package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.person.education.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.LessonDayPredicate;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveDatesBook(model.getDatesBook());
            storage.saveLessonBook(model.getLessonBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public boolean isProceed(String commandText) throws ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        String command = commandText.trim().toLowerCase();
        switch (command) {
        case "y":
            return true;
        case "n":
            return false;
        default:
            throw new ParseException("Please key in either 'y' or 'n'.");
        }

    }

    @Override
    public ObservableList<Lesson> getSortedLessonsForDay(String keyword) {
        this.model.filterThenSortLessonList(new LessonDayPredicate(keyword), Lesson::compareTo);
        return getTransformedLessonList();
        //this.model.filterLesson(new LessonDayPredicate(keyword));
        //return getFilteredLessonList();
    }

    @Override
    public ObservableList<String> getLessonsForDayInString(String keyword) {
        ObservableList<Lesson> lessons = getSortedLessonsForDay(keyword);
        ObservableList<String> lessonsForDayInString = FXCollections.observableArrayList();
        for (Lesson l : lessons) {
            lessonsForDayInString.add(l.getTimeInString() + " " + l.getPersonInString());
        }
        return lessonsForDayInString;
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return model.getFilteredLessonList();
    }

    @Override
    public ObservableList<Lesson> getSortedLessonList() {
        return model.getSortedLessonList();
    }

    @Override
    public ObservableList<Lesson> getTransformedLessonList() {
        return model.getTransformedLessonList();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        return model.getSortedPersonList();
    }

    @Override
    public ObservableList<Person> getTransformedPersonList() {
        return model.getTransformedPersonList();
    }

    @Override
    public Person getSelectedPerson() {
        return model.getSelectedPerson();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public ReadOnlyDatesBook getDatesBook() {
        return model.getDatesBook();
    }

    @Override
    public ObservableList<ImportantDate> getFilteredImportantDatesList() {
        return model.getFilteredImportantDatesList();
    }

    @Override
    public ObservableList<ImportantDate> getSortedImportantDatesList() {
        return model.getSortedImportantDatesList();
    }

    @Override
    public ObservableList<ImportantDate> getTransformedImportantDatesList() {
        return model.getTransformedImportantDatesList();
    }

    @Override
    public Path getDatesBookFilePath() {
        return model.getDatesBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
