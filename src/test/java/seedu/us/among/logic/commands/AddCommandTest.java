package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.ReadOnlyUserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EndpointBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullEndpoint_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_endpointAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEndpointAdded modelStub = new ModelStubAcceptingEndpointAdded();
        Endpoint validEndpoint = new EndpointBuilder().build();

        CommandResult commandResult = new AddCommand(validEndpoint).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEndpoint), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEndpoint), modelStub.endpointsAdded);
    }

    @Test
    public void execute_duplicateEndpoint_throwsCommandException() {
        Endpoint validEndpoint = new EndpointBuilder().build();
        AddCommand addCommand = new AddCommand(validEndpoint);
        ModelStub modelStub = new ModelStubWithEndpoint(validEndpoint);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ENDPOINT, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Endpoint get = new EndpointBuilder().withMethod("GET").build();
        Endpoint post = new EndpointBuilder().withMethod("POST").build();
        AddCommand addGetCommand = new AddCommand(get);
        AddCommand addPostCommand = new AddCommand(post);

        // same object -> returns true
        assertTrue(addGetCommand.equals(addGetCommand));

        // same values -> returns true
        AddCommand addGetCommandCopy = new AddCommand(get);
        assertTrue(addGetCommand.equals(addGetCommandCopy));

        // different types -> returns false
        assertFalse(addGetCommand.equals(1));

        // null -> returns false
        assertFalse(addGetCommand.equals(null));

        // different endpoint -> returns false
        assertFalse(addGetCommand.equals(addPostCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEndpointListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEndpointListFilePath(Path endpointListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEndpoint(Endpoint endpoint) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEndpointList(ReadOnlyEndpointList endpointList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isEndpointListEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEndpointList getEndpointList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEndpoint(Endpoint endpoint) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEndpoint(Endpoint target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEndpoint(Endpoint target, Endpoint editedEndpoint) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Endpoint> getFilteredEndpointList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEndpointList(Predicate<Endpoint> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single endpoint.
     */
    private class ModelStubWithEndpoint extends ModelStub {
        private final Endpoint endpoint;

        ModelStubWithEndpoint(Endpoint endpoint) {
            requireNonNull(endpoint);
            this.endpoint = endpoint;
        }

        @Override
        public boolean hasEndpoint(Endpoint endpoint) {
            requireNonNull(endpoint);
            return this.endpoint.isSameEndpoint(endpoint);
        }
    }

    /**
     * A Model stub that always accept the endpoint being added.
     */
    private class ModelStubAcceptingEndpointAdded extends ModelStub {
        final ArrayList<Endpoint> endpointsAdded = new ArrayList<>();

        @Override
        public boolean hasEndpoint(Endpoint endpoint) {
            requireNonNull(endpoint);
            return endpointsAdded.stream().anyMatch(endpoint::isSameEndpoint);
        }

        @Override
        public void addEndpoint(Endpoint endpoint) {
            requireNonNull(endpoint);
            endpointsAdded.add(endpoint);
        }

        @Override
        public ReadOnlyEndpointList getEndpointList() {
            return new EndpointList();
        }
    }

}
