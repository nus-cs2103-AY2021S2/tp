package seedu.address.logic.parser.persons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.GROUP_DESC_FRIEND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.GROUP_DESC_HUSBAND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandParserTest {
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withGroups(VALID_GROUP_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple groups - all accepted
        Person expectedPersonMultipleGroups = new PersonBuilder(BOB).withGroups(VALID_GROUP_FRIEND, VALID_GROUP_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND, new AddPersonCommand(expectedPersonMultipleGroups));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero group
        Person expectedPerson = new PersonBuilder(AMY).withGroups().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddPersonCommand(expectedPerson));

        Person expectedPersonOptional = new Person(new PersonName(VALID_NAME_AMY), new Phone(Phone.PLACE_HOLDER),
                new Email(Email.PLACE_HOLDER), new Address(Address.PLACE_HOLDER), new HashSet<>());
        assertParseSuccess(parser, NAME_DESC_AMY,
                new AddPersonCommand(expectedPersonOptional));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);
        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND, PersonName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_GROUP_DESC + VALID_GROUP_FRIEND, Group.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                PersonName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GROUP_DESC_HUSBAND + GROUP_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }
}
