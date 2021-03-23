package seedu.student.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.student.commons.core.GuiSettings;
import seedu.student.commons.core.LogsCenter;
import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.logic.parser.StudentBookParser;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Student;
import seedu.student.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StudentBookParser studentBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        studentBookParser = new StudentBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = studentBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStudentBook(model.getStudentBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return model.getStudentBook();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public Path getStudentBookFilePath() {
        return model.getStudentBookFilePath();
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
