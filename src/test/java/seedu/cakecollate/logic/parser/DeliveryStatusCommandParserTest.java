package seedu.cakecollate.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.DeleteCommand;
import seedu.cakecollate.logic.commands.DeliveryStatusCommand;
import seedu.cakecollate.model.order.DeliveryStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

class DeliveryStatusCommandParserTest {
    private DeliveryStatusCommandParser parser = new DeliveryStatusCommandParser(new DeliveryStatus());

    @Test
    public void parse_validArgs_returnsDeliveryStatusCommand() {
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        assertParseSuccess(parser, "1", new DeliveryStatusCommand(indexList, new DeliveryStatus()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryStatusCommand.getMessageUsage(new DeliveryStatus().toString())));
    }
}