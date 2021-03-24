package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.NameContainsKeywordsPredicate;
import javafx.util.Pair;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String VALID_NAME_ASHER = "Asher";
    public static final String VALID_NAME_BELL = "Bell";
    public static final String VALID_SEX_ASHER = "male";
    public static final String VALID_SEX_BELL = "female";
    public static final String VALID_DATEOFBIRTH_ASHER = "16-4-2020";
    public static final String VALID_DATEOFBIRTH_BELL = "15-4-2020";
    public static final String VALID_BREED_ASHER = "Corgi";
    public static final String VALID_BREED_BELL = "Greyhound";
    public static final int VALID_OWNERID_9 = 9;
    public static final int VALID_OWNERID_10 = 10;
    public static final String VALID_TAG_FRIENDLY = "friendly";
    public static final String VALID_TAG_QUIET = "quiet";

    public static final String NAME_DESC_ASHER = " " + PREFIX_NAME + VALID_NAME_ASHER;
    public static final String NAME_DESC_BELL = " " + PREFIX_NAME + VALID_NAME_BELL;
    public static final String SEX_DESC_ASHER = " " + PREFIX_SEX + VALID_SEX_ASHER;
    public static final String SEX_DESC_BELL = " " + PREFIX_SEX + VALID_SEX_BELL;
    public static final String BREED_DESC_ASHER = " " + PREFIX_BREED + VALID_BREED_ASHER;
    public static final String BREED_DESC_BELL = " " + PREFIX_BREED + VALID_BREED_BELL;
    public static final String DATEOFBIRTH_DESC_ASHER = " " + PREFIX_DATEOFBIRTH + VALID_DATEOFBIRTH_ASHER;
    public static final String DATEOFBIRTH_DESC_BELL = " " + PREFIX_DATEOFBIRTH + VALID_DATEOFBIRTH_BELL;
    public static final String OWNERID_DESC_9 = " " + PREFIX_OWNERID + VALID_OWNERID_9;
    public static final String OWNERID_DESC_10 = " " + PREFIX_OWNERID + VALID_OWNERID_10;
    public static final String TAG_DESC_FRIENDLY = " " + PREFIX_TAG + VALID_TAG_FRIENDLY;
    public static final String TAG_DESC_QUIET = " " + PREFIX_TAG + VALID_TAG_QUIET;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_SEX_DESC = " " + PREFIX_SEX + "Male1"; // '1' not allowed in sex
    public static final String INVALID_DATEOFBIRTH_DESC = " " + PREFIX_DATEOFBIRTH + "a-a-2020"; // 'a' not
    // allowed in dates of birth
    public static final String INVALID_BREED_DESC = " " + PREFIX_BREED + "poodle!"; // '!' not allowed for breed
    public static final String INVALID_OWNERID_DESC = " " + PREFIX_OWNERID; // empty ownerID not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered owner list and selected owner in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Pair<Integer, Entity>> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEntityList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredEntityList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the dog at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDogAtIndex(Model model, Integer targetIndex) {
        Entity entity = model.getFilteredEntityList().get(0).getValue();
        final String[] splitName = entity.getName().fullName.split("\\s+");
        model.updateFilteredEntityList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEntityList().size());
    }

}
