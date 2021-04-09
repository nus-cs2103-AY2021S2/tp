package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;

class BorrowCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void constructor_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BorrowCommand(null));
    }
//    @Test
//    public void verifyNameRegistration_noBookFound_throwsCommandException(){
//        Model emptyModel = new ModelStub();
//        assertThrows(CommandException.class, BorrowCommand.NO_BOOK_FOUND,
//                ()->verifyNameRegistration(emptyModel));
//
//    }
    @Test
    public void execute() {
        Record record = model.getSmartLib().getRecordList().get(0);
        //assertCommandFailure(new BorrowCommand(record), model, AddReaderCommand.MESSAGE_DUPLICATE_READER);
    }

    /**
     * A Model stub that contains a single book and a single reader.
     */
    private class ModelStubWithBook extends ModelStub {
        private final Book book;
        private final Reader reader;

        ModelStubWithBook(Book book, Reader reader) {
            requireNonNull(book);
            requireNonNull(reader);
            this.book = book;
            this.reader = reader;
        }

//        @Override
//        public boolean hasBook(Book book) {
//            requireNonNull(book);
//            return this.book.isSameBook(book);
//        }
    }
}
