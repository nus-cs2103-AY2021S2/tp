package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.TypicalBookings.BOOKING_HOON;
import static seedu.booking.testutil.TypicalPersons.HOON;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalVenues.VENUE1;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

/**
 * Contains integration tests involving add booking related prompting commands
 */
public class PromptBookingCommandIntegratedTest {
    private ModelManager model;
    private Booking expectedBooking = BOOKING_HOON;
    private Email emailInput;
    private VenueName venueNameInput;
    private Description descriptionInput;
    private Set<Tag> tagSetInput;
    private StartTime startTimeInput;
    private EndTime endTimeInput;


    @BeforeEach
    void setup() {
        model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        model.addVenue(VENUE1);
        model.addPerson(HOON);

        emailInput = BOOKING_HOON.getBookerEmail();
        venueNameInput = BOOKING_HOON.getVenueName();
        descriptionInput = BOOKING_HOON.getDescription();
        tagSetInput = BOOKING_HOON.getTags();
        startTimeInput = BOOKING_HOON.getBookingStart();
        endTimeInput = BOOKING_HOON.getBookingEnd();

        CommandState commandState = new AddBookingCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
    }

    @Test
    void execute_allPromptCommandExecuted_success() {
        try {
            new PromptAddBookingCommand().execute(model);
            new PromptBookingEmailCommand(emailInput).execute(model);
            new PromptBookingVenueCommand(venueNameInput).execute(model);
            new PromptBookingDescCommand(descriptionInput).execute(model);
            new PromptBookingTagsCommand(tagSetInput).execute(model);
            new PromptBookingStartCommand(startTimeInput).execute(model);
            new PromptBookingEndCommand(endTimeInput).execute(model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.");
        }

        assertTrue(model.hasBooking(expectedBooking));
        assertFalse(ModelManager.isStateActive());

        ModelManager.resetCommandState();
    }
}
