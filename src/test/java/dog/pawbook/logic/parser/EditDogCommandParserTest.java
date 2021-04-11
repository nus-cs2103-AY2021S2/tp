package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.BREED_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.BREED_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.DATEOFBIRTH_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DATEOFBIRTH_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_BREED_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SEX_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_ZERO_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.OWNERID_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.OWNERID_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.SEX_DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.SEX_DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_QUIET;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditDogCommand;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.EditDogDescriptorBuilder;

public class EditDogCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDogCommand.MESSAGE_USAGE);

    private final EditDogCommandParser parser = new EditDogCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no ID specified
        assertParseFailure(parser, VALID_NAME_ASHER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, String.valueOf(ID_ONE), EditDogCommand.MESSAGE_NOT_EDITED);

        // no ID and no field specified
        assertParseFailure(parser, EMPTY_STRING, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative ID
        assertParseFailure(parser, INVALID_NEGATIVE_ID_STRING + NAME_DESC_ASHER, MESSAGE_INVALID_FORMAT);

        // zero ID
        assertParseFailure(parser, INVALID_ZERO_ID_STRING + NAME_DESC_ASHER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, ID_ONE + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, ID_ONE + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid breed
        assertParseFailure(parser, ID_ONE + INVALID_BREED_DESC, Breed.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, ID_ONE + INVALID_SEX_DESC, Sex.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ID_ONE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid dateOfBirth
        assertParseFailure(parser, ID_ONE + INVALID_DATEOFBIRTH_DESC, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid breed followed by valid date of birth
        assertParseFailure(parser, ID_ONE + INVALID_BREED_DESC + DATEOFBIRTH_DESC_ASHER, Breed.MESSAGE_CONSTRAINTS);

        // valid breed followed by invalid breed. The test case for invalid breed followed by valid breed
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, ID_ONE + BREED_DESC_BELL + INVALID_BREED_DESC, Breed.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Dog} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, ID_ONE + TAG_DESC_FRIENDLY + TAG_DESC_QUIET + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_DESC_FRIENDLY + TAG_EMPTY + TAG_DESC_QUIET, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_EMPTY + TAG_DESC_FRIENDLY + TAG_DESC_QUIET, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC + INVALID_BREED_DESC + VALID_DATEOFBIRTH_ASHER
                + VALID_SEX_BELL, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Integer targetId = ID_TWO;
        String userInput = targetId + BREED_DESC_ASHER + TAG_DESC_FRIENDLY + OWNERID_DESC_ASHER
                + DATEOFBIRTH_DESC_ASHER + SEX_DESC_ASHER + NAME_DESC_ASHER + TAG_DESC_QUIET;

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_ASHER)
                .withBreed(VALID_BREED_ASHER).withDob(VALID_DATEOFBIRTH_ASHER).withSex(VALID_SEX_ASHER)
                .withOwnerId(VALID_OWNERID_ASHER)
                .withTags(VALID_TAG_FRIENDLY, VALID_TAG_QUIET).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsExceptOwnerIdSpecified_success() {
        Integer targetId = ID_ONE;
        String userInput = targetId + BREED_DESC_BELL + DATEOFBIRTH_DESC_ASHER;

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withBreed(VALID_BREED_BELL)
                .withDob(VALID_DATEOFBIRTH_ASHER).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Integer targetId = ID_THREE;
        String userInput = targetId + NAME_DESC_ASHER;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_ASHER).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // breed
        userInput = targetId + BREED_DESC_ASHER;
        descriptor = new EditDogDescriptorBuilder().withBreed(VALID_BREED_ASHER).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // dob
        userInput = targetId + DATEOFBIRTH_DESC_ASHER;
        descriptor = new EditDogDescriptorBuilder().withDob(VALID_DATEOFBIRTH_ASHER).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sex
        userInput = targetId + SEX_DESC_ASHER;
        descriptor = new EditDogDescriptorBuilder().withSex(VALID_SEX_ASHER).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ownerId
        userInput = targetId + OWNERID_DESC_ASHER;
        descriptor = new EditDogDescriptorBuilder().withOwnerId(VALID_OWNERID_ASHER).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetId + TAG_DESC_FRIENDLY;
        descriptor = new EditDogDescriptorBuilder().withTags(VALID_TAG_FRIENDLY).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Integer targetId = ID_ONE;
        String userInput = targetId + BREED_DESC_ASHER + DATEOFBIRTH_DESC_ASHER + SEX_DESC_ASHER + OWNERID_DESC_BELL
                + TAG_DESC_FRIENDLY + DATEOFBIRTH_DESC_ASHER + SEX_DESC_ASHER + BREED_DESC_ASHER + TAG_DESC_FRIENDLY
                + BREED_DESC_BELL + DATEOFBIRTH_DESC_BELL + SEX_DESC_BELL + TAG_DESC_QUIET + OWNERID_DESC_ASHER;

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withBreed(VALID_BREED_BELL)
                .withDob(VALID_DATEOFBIRTH_BELL).withSex(VALID_SEX_BELL).withOwnerId(VALID_OWNERID_ASHER)
                .withTags(VALID_TAG_FRIENDLY, VALID_TAG_QUIET).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedTags_acceptsUnique() {
        int targetId = ID_ONE;
        String userInput = targetId + TAG_DESC_FRIENDLY + TAG_DESC_QUIET + TAG_DESC_ALL + TAG_DESC_ALL
                + TAG_DESC_FRIENDLY + TAG_DESC_QUIET;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder()
                .withTags(VALID_TAG_FRIENDLY, VALID_TAG_QUIET, VALID_TAG_ALL).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Integer targetId = ID_ONE;
        String userInput = targetId + INVALID_BREED_DESC + BREED_DESC_BELL;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withBreed(VALID_BREED_BELL).build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetId + DATEOFBIRTH_DESC_BELL + INVALID_BREED_DESC + SEX_DESC_BELL
                + BREED_DESC_BELL + OWNERID_DESC_ASHER;
        descriptor = new EditDogDescriptorBuilder().withBreed(VALID_BREED_BELL).withDob(VALID_DATEOFBIRTH_BELL)
                .withOwnerId(VALID_OWNERID_ASHER).withSex(VALID_SEX_BELL).build();
        expectedCommand = new EditDogCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Integer targetId = ID_THREE;
        String userInput = targetId + TAG_EMPTY;

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withTags().build();
        EditDogCommand expectedCommand = new EditDogCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
