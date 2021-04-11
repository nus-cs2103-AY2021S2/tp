package seedu.cakecollate.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_NEGATIVE_INDEX;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;
import seedu.cakecollate.model.tag.Tag;

public class ParserUtilTest {

    private static final String OVERFLOW_NAME =
            "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";
    private static final String OVERFLOW_PHONE = "999999999999999999999999999999999999";
    private static final String OVERFLOW_TAGS = "THIS TAG IS TOO LOOOOOOOOOOOOOONG";
    private static final String OVERFLOW_ORDER_DESCRIPTION =
            "THIS ORDERDESCRIPTION IS TOO LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG";

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ORDER_DESC = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DELIVERY_DATE = "12122012";
    private static final String INVALID_DAYS = "-1";
    private static final String INVALID_DAYS_MULTIPLE = "-1";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ORDER_DESC_1 = "chocolate mousse";
    private static final String VALID_ORDER_DESC_2 = "strawberry thing";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_DELIVERY_DATE = "01/01/2022";
    private static final String VALID_DAYS = "1";

    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX = "1";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES = "1 2";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES = "1 2 3";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACE_AT_FRONT =
            " 1";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACE_AT_BACK =
            "1 ";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK =
            " 1 ";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACE_AT_FRONT =
            " 1 2";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACE_AT_BACK =
            "1 2 ";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK =
            " 1 2 ";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACE_AT_FRONT =
            " 1 2 3";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACE_AT_BACK =
            "1 2 3 ";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK =
            " 1 2 3 ";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_1 =
            "   1   ";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_2 =
            "            1     ";
    private static final String VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_3 =
            "                                1                 ";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_1 =
            "  1  2  ";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_2 =
            "  1          2     ";
    private static final String VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_3 =
            "             1                   2                 ";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_1 =
            "  1  2  3  ";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_2 =
            "  1          2           3  ";
    private static final String VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_3 =
            "             1                   2          3              ";
    private static final String VALID_INDEX_LIST_WITH_RANDOM_ORDER_1 = " 3 2 1 ";
    private static final String VALID_INDEX_LIST_WITH_RANDOM_ORDER_2 = " 3 1 ";
    private static final String VALID_INDEX_LIST_WITH_RANDOM_ORDER_3 = " 2 1 3";

    private static final String INVALID_INDEX_LIST_WITH_ONE_INDEX = "-1";
    private static final String INVALID_INDEX_LIST_WITH_TWO_INDEXES = "-1 -2";
    private static final String INVALID_INDEX_LIST_WITH_THREE_INDEXES = "-1 -2 -3";
    private static final String INVALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_SPACES = "  -1  ";
    private static final String INVALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_SPACES = "    -1    -2   ";
    private static final String INVALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_SPACES = "   -1    -2    -3   ";
    private static final String INVALID_INDEX_LIST_WITH_SINGLE_WHITESPACE = " ";
    private static final String INVALID_INDEX_LIST_WITH_MULTIPLE_WHITESPACES = "     ";
    private static final String INVALID_INDEX_LIST_WITH_COMMAS = "1, 2, 3";
    private static final String INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_1 = "2 -1 3";
    private static final String INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_2 = "2 -1    -3";
    private static final String INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_3 = "-1     3    2";
    private static final String INVALID_INDEX_LIST_WITH_ALPHABETS_1 = "1 2 A";
    private static final String INVALID_INDEX_LIST_WITH_ALPHABETS_2 = "  a 2 A   ";
    private static final String INVALID_INDEX_LIST_WITH_ALPHABETS_3 = "  aaaaa 2  1  ABBB   ";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10aaaaa111111111111a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_NEGATIVE_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
        assertThrows(ParseException.class, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, ()
            -> ParserUtil.parseIndex("111111111111111111111111111111111"));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ORDER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ORDER, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(OVERFLOW_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parsePhone_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(OVERFLOW_PHONE));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseOrderDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderDescription(null));
    }

    @Test
    public void parseOrderDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderDescription(INVALID_ORDER_DESC));
    }

    @Test
    public void parseOrderDescription_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderDescription(OVERFLOW_ORDER_DESCRIPTION));
    }

    @Test
    public void parseOrderDescription_validValue_returnsOrderDescription() throws ParseException {
        OrderDescription expectedOrderDescription = new OrderDescription(VALID_ORDER_DESC_1);
        assertEquals(expectedOrderDescription, ParserUtil.parseOrderDescription(VALID_ORDER_DESC_1));
    }

    @Test
    public void parseOrderDescription_validValueWithWhitespace_returnsTrimmedOrderDescription() throws ParseException {
        String untrimmed = WHITESPACE + VALID_ORDER_DESC_1 + WHITESPACE;
        OrderDescription expectedOrderDescription = new OrderDescription(VALID_ORDER_DESC_1);
        assertEquals(expectedOrderDescription, ParserUtil.parseOrderDescription(untrimmed));
    }

    @Test
    public void parseOrderDescription_collectionWithInvalidOrderDescription_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderDescriptions(Arrays.asList(VALID_ORDER_DESC_1,
                INVALID_ORDER_DESC)));
    }

    @Test
    public void parseOrderDescription_collectionWithValidOrderDescription_returnsSet() throws Exception {
        Map<OrderDescription, Integer> actualOrderDescriptionSet =
                ParserUtil.parseOrderDescriptions(Arrays.asList(VALID_ORDER_DESC_1, VALID_ORDER_DESC_2));
        Map<OrderDescription, Integer> expectedOrderDescriptionSet = new HashMap<>();
        expectedOrderDescriptionSet.put(new OrderDescription(VALID_ORDER_DESC_1), 1);
        expectedOrderDescriptionSet.put(new OrderDescription(VALID_ORDER_DESC_2), 1);

        assertEquals(expectedOrderDescriptionSet, actualOrderDescriptionSet);
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(OVERFLOW_TAGS));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseDeliveryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeliveryDate(null));
    }

    @Test
    public void parseDeliveryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeliveryDate(INVALID_DELIVERY_DATE));
    }

    @Test
    public void parseDeliveryDate_validValueWithoutWhitespace_returnsDeliveryDate() throws Exception {
        DeliveryDate expectedDeliveryDate = new DeliveryDate(VALID_DELIVERY_DATE);
        assertEquals(expectedDeliveryDate, ParserUtil.parseDeliveryDate(VALID_DELIVERY_DATE));
    }

    @Test
    public void parseDeliveryDate_validValueWithWhitespace_returnsTrimmedDeliveryDate() throws Exception {
        String deliveryDateWithWhitespace = WHITESPACE + VALID_DELIVERY_DATE + WHITESPACE;
        DeliveryDate expectedDeliveryDate = new DeliveryDate(VALID_DELIVERY_DATE);
        assertEquals(expectedDeliveryDate, ParserUtil.parseDeliveryDate(deliveryDateWithWhitespace));
    }

    @Test
    public void parseIndexList_validValueWithoutWhitespace_returnsValidIndexList() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        //index list with one index
        IndexList expectedIndexListWithOneIndex = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndex.add(indexOne);
        assertEquals(expectedIndexListWithOneIndex, ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX));

        //index list with two indexes
        IndexList expectedIndexListWithTwoIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexes.add(indexOne);
        expectedIndexListWithTwoIndexes.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexes, ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES));

        //index list with three indexes
        IndexList expectedIndexListWithThreeIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexes.add(indexOne);
        expectedIndexListWithThreeIndexes.add(indexTwo);
        expectedIndexListWithThreeIndexes.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexes, ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES));
    }

    @Test
    public void parseIndexList_validValueWithExtraWhitespaceAtFront_returnsValidIndexList() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        //index list with one index
        IndexList expectedIndexListWithOneIndex = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndex.add(indexOne);
        assertEquals(expectedIndexListWithOneIndex, ParserUtil.parseIndexList(
                VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACE_AT_FRONT));

        //index list with two indexes
        IndexList expectedIndexListWithTwoIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexes.add(indexOne);
        expectedIndexListWithTwoIndexes.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACE_AT_FRONT));

        //index list with three indexes
        IndexList expectedIndexListWithThreeIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexes.add(indexOne);
        expectedIndexListWithThreeIndexes.add(indexTwo);
        expectedIndexListWithThreeIndexes.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACE_AT_FRONT));
    }

    @Test
    public void parseIndexList_validValueWithExtraWhitespaceAtBack_returnsValidIndexList() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        //index list with one index
        IndexList expectedIndexListWithOneIndex = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndex.add(indexOne);
        assertEquals(expectedIndexListWithOneIndex,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACE_AT_BACK));

        //index list with two indexes
        IndexList expectedIndexListWithTwoIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexes.add(indexOne);
        expectedIndexListWithTwoIndexes.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACE_AT_BACK));

        //index list with three indexes
        IndexList expectedIndexListWithThreeIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexes.add(indexOne);
        expectedIndexListWithThreeIndexes.add(indexTwo);
        expectedIndexListWithThreeIndexes.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACE_AT_BACK));
    }

    @Test
    public void parseIndexList_validValueWithExtraWhitespaceAtFrontAndBack_returnsValidIndexList() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        //index list with one index
        IndexList expectedIndexListWithOneIndex = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndex.add(indexOne);
        assertEquals(expectedIndexListWithOneIndex,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK));

        //index list with two indexes
        IndexList expectedIndexListWithTwoIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexes.add(indexOne);
        expectedIndexListWithTwoIndexes.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK));

        //index list with three indexes
        IndexList expectedIndexListWithThreeIndexes = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexes.add(indexOne);
        expectedIndexListWithThreeIndexes.add(indexTwo);
        expectedIndexListWithThreeIndexes.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexes,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_EXTRA_WHITESPACES_AT_FRONT_AND_BACK));
    }

    @Test
    public void parseIndexList_validValueWithMultipleWhitespaces_returnsValidIndexList() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        //index list with one index
        IndexList expectedIndexListWithOneIndexOne = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndexOne.add(indexOne);
        assertEquals(expectedIndexListWithOneIndexOne,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_1));

        IndexList expectedIndexListWithOneIndexTwo = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndexTwo.add(indexOne);
        assertEquals(expectedIndexListWithOneIndexTwo,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_2));

        IndexList expectedIndexListWithOneIndexThree = new IndexList(new ArrayList<Index>());
        expectedIndexListWithOneIndexThree.add(indexOne);
        assertEquals(expectedIndexListWithOneIndexThree,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_WHITESPACES_3));

        //index list with two indexes
        IndexList expectedIndexListWithTwoIndexesOne = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexesOne.add(indexOne);
        expectedIndexListWithTwoIndexesOne.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexesOne,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_1));

        IndexList expectedIndexListWithTwoIndexesTwo = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexesTwo.add(indexOne);
        expectedIndexListWithTwoIndexesTwo.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexesTwo,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_2));

        IndexList expectedIndexListWithTwoIndexesThree = new IndexList(new ArrayList<Index>());
        expectedIndexListWithTwoIndexesThree.add(indexOne);
        expectedIndexListWithTwoIndexesThree.add(indexTwo);
        assertEquals(expectedIndexListWithTwoIndexesThree,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_WHITESPACES_3));

        //index list with three indexes
        IndexList expectedIndexListWithThreeIndexesOne = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexesOne.add(indexOne);
        expectedIndexListWithThreeIndexesOne.add(indexTwo);
        expectedIndexListWithThreeIndexesOne.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexesOne,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_1));

        IndexList expectedIndexListWithThreeIndexesTwo = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexesTwo.add(indexOne);
        expectedIndexListWithThreeIndexesTwo.add(indexTwo);
        expectedIndexListWithThreeIndexesTwo.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexesTwo,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_2));

        IndexList expectedIndexListWithThreeIndexesThree = new IndexList(new ArrayList<Index>());
        expectedIndexListWithThreeIndexesThree.add(indexOne);
        expectedIndexListWithThreeIndexesThree.add(indexTwo);
        expectedIndexListWithThreeIndexesThree.add(indexThree);
        assertEquals(expectedIndexListWithThreeIndexesThree,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_WHITESPACES_3));
    }

    @Test
    public void parseIndexList_validValueWithIndexesInRandomOrder_returnsIndexListWithCorrectOrder() throws Exception {
        Index indexOne = Index.fromOneBased(1);
        Index indexTwo = Index.fromOneBased(2);
        Index indexThree = Index.fromOneBased(3);

        IndexList expectedIndexListRandomOrderOne = new IndexList(new ArrayList<Index>());
        expectedIndexListRandomOrderOne.add(indexOne);
        expectedIndexListRandomOrderOne.add(indexTwo);
        expectedIndexListRandomOrderOne.add(indexThree);
        expectedIndexListRandomOrderOne.sortList();
        assertEquals(expectedIndexListRandomOrderOne,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_RANDOM_ORDER_1));

        IndexList expectedIndexListRandomOrderTwo = new IndexList(new ArrayList<Index>());
        expectedIndexListRandomOrderTwo.add(indexOne);
        expectedIndexListRandomOrderTwo.add(indexThree);
        expectedIndexListRandomOrderTwo.sortList();
        assertEquals(expectedIndexListRandomOrderTwo,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_RANDOM_ORDER_2));

        IndexList expectedIndexListRandomOrderThree = new IndexList(new ArrayList<Index>());
        expectedIndexListRandomOrderThree.add(indexOne);
        expectedIndexListRandomOrderThree.add(indexTwo);
        expectedIndexListRandomOrderThree.add(indexThree);
        expectedIndexListRandomOrderThree.sortList();
        assertEquals(expectedIndexListRandomOrderThree,
                ParserUtil.parseIndexList(VALID_INDEX_LIST_WITH_RANDOM_ORDER_3));
    }

    @Test
    public void parseIndexList_invalidIndexListWithOneIndex_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_ONE_INDEX));
    }

    @Test
    public void parseIndexList_invalidIndexListWithTwoIndexes_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_TWO_INDEXES));
    }

    @Test
    public void parseIndexList_invalidIndexListWithThreeIndexes_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_THREE_INDEXES));
    }

    @Test
    public void parseIndexList_invalidIndexListWithOneIndexAndMultipleSpaces_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_ONE_INDEX_AND_MULTIPLE_SPACES));
    }

    @Test
    public void parseIndexList_invalidIndexListWithTwoIndexesAndMultipleSpaces_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_TWO_INDEXES_AND_MULTIPLE_SPACES));
    }

    @Test
    public void parseIndexList_invalidIndexListWithThreeIndexesAndMultipleSpaces_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_THREE_INDEXES_AND_MULTIPLE_SPACES));
    }

    @Test
    public void parseIndexList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> ParserUtil.parseIndexList(null));
    }

    @Test
    public void parseIndexList_singleWhitespace_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_SINGLE_WHITESPACE));
    }

    @Test
    public void parseIndexList_multipleWhitespaces_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_MULTIPLE_WHITESPACES));
    }

    @Test
    public void parseIndexList_commas_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_COMMAS));
    }

    @Test
    public void parseIndexList_capitalAlphabet_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_ALPHABETS_1));
    }

    @Test
    public void parseIndexList_mixedAlphabets_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_ALPHABETS_2));
    }

    @Test
    public void parseIndexList_multipleAlphabets_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_ALPHABETS_3));
    }

    @Test
    public void parseIndexList_positiveAndNegativeIntegersOne_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_1));
    }

    @Test
    public void parseIndexList_positiveAndNegativeIntegersTwo_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_2));
    }

    @Test
    public void parseIndexList_positiveAndNegativeIntegersThree_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndexList(INVALID_INDEX_LIST_WITH_MIXED_INTEGERS_3));
    }

    @Test
    public void parseOrderItem_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderItem(null));
    }

    @Test
    public void parseOrderItem_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderItem(INVALID_ORDER_DESC));
    }

    @Test
    public void parseOrderItem_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderItem(OVERFLOW_ORDER_DESCRIPTION));
    }

    @Test
    public void parseOrderItem_validValue_returnsOrderDescription() throws ParseException {
        OrderItem expectedOrderItem = new OrderItem(new Type(VALID_ORDER_DESC_1));
        assertEquals(expectedOrderItem, ParserUtil.parseOrderItem(VALID_ORDER_DESC_1));
    }

    @Test
    public void parseDays_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDays(null));
    }

    @Test
    public void parseDays_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDays(INVALID_DAYS));
        assertThrows(ParseException.class, () -> ParserUtil.parseDays(INVALID_DAYS_MULTIPLE));
    }

    @Test
    public void parseDays_validValue_returnsOrderDescription() throws ParseException {
        int expectedDays = Integer.parseInt(VALID_DAYS);
        assertEquals(expectedDays, ParserUtil.parseDays(VALID_DAYS));
    }
}
