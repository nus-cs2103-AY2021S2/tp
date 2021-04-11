package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ID_GENERAL;
import static dog.pawbook.logic.commands.CommandTestUtil.DOGID_DESC_FOUR;
import static dog.pawbook.logic.commands.CommandTestUtil.DOGID_DESC_TWO;
import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_DOGID_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_PROGRAMID_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_UNKNOWN_DOGID_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_UNKNOWN_PROGRAMID_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.PROGRAMID_DESC_FIFTEEN;
import static dog.pawbook.logic.commands.CommandTestUtil.PROGRAMID_DESC_SEVENTEEN;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FOUR;
import static dog.pawbook.testutil.TypicalId.ID_SEVENTEEN;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EnrolCommand;

public class EnrolCommandParserTest {
    private EnrolDropCommandParser parser = new EnrolDropCommandParser(true);

    @Test
    public void parse_emptyArguments_failure() {
        // Test that empty arguments ParseException is thrown

        // Empty user input
        assertParseFailure(parser, EMPTY_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EnrolCommand.MESSAGE_USAGE));

        // Empty dogId
        String userInput = PROGRAMID_DESC_FIFTEEN;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EnrolCommand.MESSAGE_USAGE));

        // Empty programId
        userInput = DOGID_DESC_TWO;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EnrolCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeId_failure() {
        // Test that negative ID ParseException is thrown

        // Both dogId and programId negative
        String userInput = INVALID_NEGATIVE_DOGID_DESC + INVALID_NEGATIVE_PROGRAMID_DESC;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);

        // dogId negative
        userInput = INVALID_NEGATIVE_DOGID_DESC + PROGRAMID_DESC_FIFTEEN;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);

        // programId negative
        userInput = DOGID_DESC_TWO + INVALID_NEGATIVE_PROGRAMID_DESC;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);
    }

    @Test
    public void parse_invalidId_failure() {
        // Test that invalid ID parseException is thrown

        // Both dogId and programId invalid
        String userInput = INVALID_UNKNOWN_DOGID_DESC + INVALID_UNKNOWN_PROGRAMID_DESC;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);

        // dogId invalid
        userInput = INVALID_UNKNOWN_DOGID_DESC + PROGRAMID_DESC_FIFTEEN;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);

        // programID invalid
        userInput = DOGID_DESC_TWO + INVALID_UNKNOWN_PROGRAMID_DESC;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_ID_GENERAL);
    }

    @Test
    public void parse_manyDogsManyPrograms_failure() {
        // Test that invalid ID parseException is thrown
        String userInput = DOGID_DESC_TWO + DOGID_DESC_FOUR + PROGRAMID_DESC_FIFTEEN + PROGRAMID_DESC_SEVENTEEN;
        assertParseFailure(parser, userInput, EnrolDropCommandParser.MESSAGE_UNSUPPORTED_BATCH_OPERATION);
    }

    @Test
    public void parse_oneDogOneProgram_success() {
        // Test that no parseException thrown
        Set<Integer> dogIdSet = new HashSet<>();
        Set<Integer> programIdSet = new HashSet<>();
        dogIdSet.add(ID_TWO);
        programIdSet.add(ID_FIFTEEN);
        String userInput = DOGID_DESC_TWO + PROGRAMID_DESC_FIFTEEN;
        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);

        assertParseSuccess(parser, userInput, enrolCommand);
    }

    @Test
    public void parse_oneDogManyPrograms_success() {
        // Test that no parseException thrown
        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_TWO);

        List<Integer> programIdList = Arrays.asList(ID_FIFTEEN, ID_SEVENTEEN);
        Set<Integer> programIdSet = new HashSet<>(programIdList);

        String userInput = DOGID_DESC_TWO + PROGRAMID_DESC_FIFTEEN + PROGRAMID_DESC_SEVENTEEN;
        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);

        assertParseSuccess(parser, userInput, enrolCommand);
    }

    @Test
    public void parse_manyDogsOneProgram_success() {
        // Test that no parseException thrown
        List<Integer> dogIdList = Arrays.asList(ID_TWO, ID_FOUR);
        Set<Integer> dogIdSet = new HashSet<>(dogIdList);

        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_FIFTEEN);

        String userInput = DOGID_DESC_TWO + DOGID_DESC_FOUR + PROGRAMID_DESC_FIFTEEN;
        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);

        assertParseSuccess(parser, userInput, enrolCommand);
    }
}
