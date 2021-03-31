package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.BREED_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.BREED_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.DATEOFBIRTH_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DATEOFBIRTH_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_BREED_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SEX_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.OWNERID_DESC_10;
import static dog.pawbook.logic.commands.CommandTestUtil.OWNERID_DESC_9;
import static dog.pawbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static dog.pawbook.logic.commands.CommandTestUtil.SEX_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.SEX_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_QUIET;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_10;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalDogs.ASHER;
import static dog.pawbook.testutil.TypicalDogs.BELL;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.DogBuilder;

public class AddDogCommandParserTest {
    private AddDogCommandParser parser = new AddDogCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Dog expectedDog = new DogBuilder(BELL).withTags(VALID_TAG_FRIENDLY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL
                + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDog));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ASHER + NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL
                + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDog));

        // multiple sexes - last sex accepted
        assertParseSuccess(parser, NAME_DESC_BELL + SEX_DESC_ASHER + SEX_DESC_BELL + BREED_DESC_BELL
                + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDog));

        // multiple date of births - last date of birth accepted
        assertParseSuccess(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_ASHER + BREED_DESC_BELL
                + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDog));

        // multiple breeds - last breed accepted
        assertParseSuccess(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_ASHER
                + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDog));

        // multiple tags - all accepted
        Dog expectedDogMultipleTags = new DogBuilder(BELL).withTags(VALID_TAG_FRIENDLY, VALID_TAG_QUIET)
                .build();
        assertParseSuccess(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY, new AddDogCommand(expectedDogMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Dog expectedDog = new DogBuilder(ASHER).withTags().build();
        assertParseSuccess(parser, NAME_DESC_ASHER + SEX_DESC_ASHER + BREED_DESC_ASHER
                        + DATEOFBIRTH_DESC_ASHER + OWNERID_DESC_9, new AddDogCommand(expectedDog));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDogCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BELL + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                        + OWNERID_DESC_10, expectedMessage);

        // missing sex prefix
        assertParseFailure(parser, NAME_DESC_BELL + VALID_SEX_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                        + OWNERID_DESC_10, expectedMessage);

        // missing breed prefix
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + VALID_BREED_BELL + DATEOFBIRTH_DESC_BELL
                        + OWNERID_DESC_10, expectedMessage);

        // missing date of birth prefix
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + VALID_DATEOFBIRTH_BELL
                        + OWNERID_DESC_10, expectedMessage);

        // missing ownerID prefix
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                + VALID_OWNERID_10, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BELL + VALID_SEX_BELL + VALID_BREED_BELL + VALID_DATEOFBIRTH_BELL
                        + VALID_OWNERID_10, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY, Name.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, NAME_DESC_BELL + INVALID_SEX_DESC + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY, Sex.MESSAGE_CONSTRAINTS);

        // invalid breed
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + INVALID_BREED_DESC + DATEOFBIRTH_DESC_BELL
                + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY, Breed.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + INVALID_DATEOFBIRTH_DESC
                + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL
                + OWNERID_DESC_10 + INVALID_TAG_DESC + VALID_TAG_FRIENDLY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SEX_DESC_BELL + BREED_DESC_BELL
                + INVALID_DATEOFBIRTH_DESC + OWNERID_DESC_10, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BELL + SEX_DESC_BELL + BREED_DESC_BELL
                        + DATEOFBIRTH_DESC_BELL + OWNERID_DESC_10 + TAG_DESC_QUIET + TAG_DESC_FRIENDLY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDogCommand.MESSAGE_USAGE));
    }
}
