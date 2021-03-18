package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.testutil.ReaderBuilder;

public class AddReaderCommandTest {

    @Test
    public void constructor_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddReaderCommand(null));
    }

    @Test
    public void execute_readerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingReaderAdded modelStub = new ModelStubAcceptingReaderAdded();
        Reader validReader = new ReaderBuilder().build();

        CommandResult commandResult = new AddReaderCommand(validReader).execute(modelStub);

        assertEquals(String.format(AddReaderCommand.MESSAGE_SUCCESS, validReader), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validReader), modelStub.readersAdded);
    }

    @Test
    public void execute_duplicateReader_throwsCommandException() {
        Reader validReader = new ReaderBuilder().build();
        AddReaderCommand addCommand = new AddReaderCommand(validReader);
        ModelStub modelStub = new ModelStubWithReader(validReader);

        assertThrows(CommandException.class, AddReaderCommand.MESSAGE_DUPLICATE_READER, () ->
            addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Reader alice = new ReaderBuilder().withName("Alice").build();
        Reader bob = new ReaderBuilder().withName("Bob").build();
        AddReaderCommand addAliceCommand = new AddReaderCommand(alice);
        AddReaderCommand addBobCommand = new AddReaderCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddReaderCommand addAliceCommandCopy = new AddReaderCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different reader -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single reader.
     */
    private class ModelStubWithReader extends ModelStub {
        private final Reader reader;

        ModelStubWithReader(Reader reader) {
            requireNonNull(reader);
            this.reader = reader;
        }

        @Override
        public boolean hasReader(Reader reader) {
            requireNonNull(reader);
            return this.reader.isSameReader(reader);
        }
    }

    /**
     * A Model stub that always accept the reader being added.
     */
    private class ModelStubAcceptingReaderAdded extends ModelStub {
        final ArrayList<Reader> readersAdded = new ArrayList<>();

        @Override
        public boolean hasReader(Reader reader) {
            requireNonNull(reader);
            return readersAdded.stream().anyMatch(reader::isSameReader);
        }

        @Override
        public void addReader(Reader reader) {
            requireNonNull(reader);
            readersAdded.add(reader);
        }

        @Override
        public ReadOnlySmartLib getSmartLib() {
            return new SmartLib();
        }
    }

}
