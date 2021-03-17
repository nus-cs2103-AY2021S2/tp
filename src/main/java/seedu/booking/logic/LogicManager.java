package seedu.booking.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.logic.commands.Command;
import seedu.booking.logic.commands.CommandResult;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.parser.BookingSystemParser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Model;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final BookingSystemParser bookingSystemParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        bookingSystemParser = new BookingSystemParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bookingSystemParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveBookingSystem(model.getBookingSystem());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBookingSystem getBookingSystem() {
        return model.getBookingSystem();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Booking> getUpcomingBookingList() {
        return model.getUpcomingBookingList();
    }

    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return model.getFilteredBookingList();
    }

    @Override
    public ObservableList<Venue> getFilteredVenueList() {
        return model.getFilteredVenueList();
    }

    @Override
    public Path getBookingSystemFilePath() {
        return model.getBookingSystemFilePath();
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
