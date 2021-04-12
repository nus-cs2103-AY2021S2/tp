package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.DELIVERY_DATE_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.DELIVERY_DATE_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_DELIVERY_DATE_DESC1;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_ORDER_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.cakecollate.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ORDER_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ORDER_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ORDER_ITEM_INDEXES_1;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ORDER_ITEM_INDEXES_2;
import static seedu.cakecollate.logic.commands.CommandTestUtil.ORDER_ITEM_INDEXLIST_2;
import static seedu.cakecollate.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.cakecollate.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.cakecollate.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_BERRY_ORDER;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_CHOCOLATE_ORDER;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_DELIVERY_DATE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_ITEM_IDX;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.AMY;
import static seedu.cakecollate.testutil.TypicalOrders.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.tag.Tag;
import seedu.cakecollate.testutil.AddOrderDescriptorBuilder;
import seedu.cakecollate.testutil.OrderBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Order expectedOrder = new OrderBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(expectedOrder).build();
        IndexList indexList = null;

        // todo requests are actually different here
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        //  multiple order descriptions - all accepted
        Order expectedOrderMultipleOrderDescriptions = new OrderBuilder(BOB)
                .withOrderDescriptions(VALID_CHOCOLATE_ORDER, VALID_BERRY_ORDER)
                .withTags(VALID_TAG_FRIEND)
                .build();

        AddCommand.AddOrderDescriptor descriptorMultipleDesc =
                new AddOrderDescriptorBuilder(expectedOrderMultipleOrderDescriptions).build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_AMY + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptorMultipleDesc));

        // multiple tags - all accepted
        Order expectedOrderMultipleTags = new OrderBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        AddCommand.AddOrderDescriptor descriptorMultipleTags =
                new AddOrderDescriptorBuilder(expectedOrderMultipleTags).build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptorMultipleTags));

        //  multiple delivery dates - last accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_AMY + DELIVERY_DATE_DESC_BOB,
                new AddCommand(indexList, descriptor));

        IndexList lastIndexList = ORDER_ITEM_INDEXLIST_2;

        // multiple order indexes - only last prefix accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB
                        + ORDER_ITEM_INDEXES_1 + ORDER_ITEM_INDEXES_2,
                new AddCommand(lastIndexList, descriptor));
    }


    @Test
    public void parse_optionalFieldsMissing_success() {
        Order expectedOrder = new OrderBuilder(AMY).withTags().build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(expectedOrder).build();
        IndexList indexList = null;

        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + ORDER_DESC_AMY + DELIVERY_DATE_DESC_AMY, new AddCommand(indexList, descriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + ORDER_DESC_BOB + DELIVERY_DATE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + ORDER_DESC_BOB + DELIVERY_DATE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + ORDER_DESC_BOB + DELIVERY_DATE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + ORDER_DESC_BOB + DELIVERY_DATE_DESC_BOB, expectedMessage);

        // missing order description prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_BERRY_ORDER + DELIVERY_DATE_DESC_BOB, expectedMessage);

        // missing delivery date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + ORDER_DESC_BOB + VALID_DELIVERY_DATE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ADDRESS_BOB + VALID_BERRY_ORDER + VALID_DELIVERY_DATE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                Address.MESSAGE_EMPTY);

        // invalid order description
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_ORDER_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB,
                OrderDescription.MESSAGE_EMPTY);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + ORDER_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + DELIVERY_DATE_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid delivery date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_DELIVERY_DATE_DESC1,
                DeliveryDate.MESSAGE_CONSTRAINTS_FORMAT);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + ORDER_DESC_BOB + DELIVERY_DATE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + DELIVERY_DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    // ========== TESTS WITH INDEX LIST ==========

    @Test
    public void parse_withOrderItemIndexAndDesc_success() {
        Order expectedOrder = new OrderBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(expectedOrder).build();

        IndexList indexList = new IndexList(new ArrayList<>());
        indexList.add(INDEX_FIRST_ORDER);
        indexList.add(INDEX_SECOND_ORDER);

        String orderItemIndex = " " + PREFIX_ORDER_ITEM_IDX + " 1 2 ";

        // 1 order and a set of indices
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + ORDER_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB + orderItemIndex,
                new AddCommand(indexList, descriptor));
    }

    @Test
    public void parse_withOrderItemIndexAndNoDesc_success() {
        Order expectedOrder = new OrderBuilder(BOB).withTags(VALID_TAG_FRIEND).withOrderDescriptions().build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(expectedOrder).build();

        // doesn't matter if this index doesn't exist in order items model; this test is independent of model
        IndexList indexList = new IndexList(new ArrayList<>());
        indexList.add(INDEX_FIRST_ORDER);
        indexList.add(INDEX_SECOND_ORDER);

        String orderItemIndex = " " + PREFIX_ORDER_ITEM_IDX + " 1 2 ";

        // 1 order and a set of indices
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB + orderItemIndex,
                new AddCommand(indexList, descriptor));
    }


    // ========== TESTS RELATED TO ADDING MULTIPLE CAKES TO THE SAME ORDER ==========
    @Test
    public void parse_recogniseDuplicateIndex_success() {
        Order expectedOrder = new OrderBuilder(BOB).withTags(VALID_TAG_FRIEND).withOrderDescriptions().build();
        AddCommand.AddOrderDescriptor descriptor = new AddOrderDescriptorBuilder(expectedOrder).build();

        IndexList expectedList = new IndexList(new ArrayList<>());
        expectedList.add(INDEX_FIRST_ORDER);
        expectedList.add(INDEX_FIRST_ORDER);

        String orderItemIndex = " " + PREFIX_ORDER_ITEM_IDX + " 1 1 ";

        // 1 order and a set of indices
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + DELIVERY_DATE_DESC_BOB + orderItemIndex,
                new AddCommand(expectedList, descriptor));
    }

}
