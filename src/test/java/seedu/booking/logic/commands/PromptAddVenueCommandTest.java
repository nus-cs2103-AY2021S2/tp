package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_CAPACITY_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_VENUE_DESC_MESSAGE;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_DESCRIPTION_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_TAGS_HALL;
import static seedu.booking.logic.commands.states.AddVenueCommandState.STATE_CAPACITY;
import static seedu.booking.logic.commands.states.AddVenueCommandState.STATE_DESC;
import static seedu.booking.logic.commands.states.AddVenueCommandState.STATE_TAG;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddVenueCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.VenueName;

public class PromptAddVenueCommandTest {
    private final ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    void execute_enterVenueName_stateChangeToCapacitySuccessful() throws CommandException {
        CommandResult expectedResult = new CommandResult(PROMPT_CAPACITY_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
        CommandResult result = new PromptAddVenueCommand(new VenueName((VALID_VENUE_NAME_HALL))).execute(model);
        assertEquals(expectedResult, result);

        String state = StatefulLogicManager.getState();
        assertEquals(STATE_CAPACITY, state);
        assertTrue(StatefulLogicManager.isStateActive());

        StatefulLogicManager.resetCommandState();
    }

    @Nested
    class PromptAddVenueCommandActiveStatesTest {
        @BeforeEach
        public void setUp() {
            CommandState commandState = new AddVenueCommandState(new VenueName(VALID_VENUE_NAME_HALL));
            StatefulLogicManager.setCommandState(commandState);
            StatefulLogicManager.setStateActive();
        }

        @Test
        void execute_enterCapacity_stateChangeToDescriptionSuccessful() {
            StatefulLogicManager.setState(STATE_CAPACITY);

            PromptVenueCapacityCommand command = new PromptVenueCapacityCommand(
                    new Capacity(VALID_VENUE_CAPACITY_HALL));
            CommandResult expectedResult = new CommandResult(PROMPT_VENUE_DESC_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
            CommandResult result;

            try {
                result = command.execute(model);
                assertEquals(expectedResult, result);
            } catch (CommandException ex) {
                throw new AssertionError("Execution of command should not fail.");
            }

            String state = StatefulLogicManager.getState();
            assertEquals(STATE_DESC, state);
            assertTrue(StatefulLogicManager.isStateActive());

            StatefulLogicManager.resetCommandState();
        }

        @Test
        void execute_enterDescription_stateChangeTagsSuccessful() {
            StatefulLogicManager.setState(STATE_DESC);

            PromptVenueDescCommand command = new PromptVenueDescCommand(VALID_VENUE_DESCRIPTION_HALL);
            CommandResult expectedResult = new CommandResult(PROMPT_TAG_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
            CommandResult result;

            try {
                result = command.execute(model);
                assertEquals(expectedResult, result);
            } catch (CommandException ex) {
                throw new AssertionError("Execution of command should not fail.");
            }

            String state = StatefulLogicManager.getState();
            assertEquals(STATE_TAG, state);
            assertTrue(StatefulLogicManager.isStateActive());

            StatefulLogicManager.resetCommandState();
        }
    }

    @Test
    void execute_enterTags_endActiveStateSuccessful() {
        CommandState commandState = new AddVenueCommandState(new VenueName(VALID_VENUE_NAME_HALL));
        StatefulLogicManager.setCommandState(commandState);
        StatefulLogicManager.setStateActive();
        StatefulLogicManager.setState(STATE_CAPACITY);
        commandState.processInput(new Capacity(VALID_VENUE_CAPACITY_HALL));
        StatefulLogicManager.setState(STATE_DESC);
        commandState.processInput(VALID_VENUE_DESCRIPTION_HALL);
        StatefulLogicManager.setState(STATE_TAG);

        Set<Tag> set = new HashSet<>();
        set.add(new Tag(VALID_VENUE_TAGS_HALL));
        PromptVenueTagsCommand command = new PromptVenueTagsCommand(set);

        try {
            command.execute(model);
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.");
        }

        String state = StatefulLogicManager.getState();
        assertNull(state);
        assertFalse(StatefulLogicManager.isStateActive());

        StatefulLogicManager.resetCommandState();
    }

    @Test
    void execute_changeStateAfterTagState_invalidState() {
        CommandState commandState = new AddVenueCommandState(new VenueName(VALID_VENUE_NAME_HALL));
        StatefulLogicManager.setState(STATE_TAG);
        commandState.setNextState();
        String state = StatefulLogicManager.getState();

        assertEquals(STATE_TAG, state);
        assertFalse(StatefulLogicManager.isStateActive());

        StatefulLogicManager.resetCommandState();
    }
}
