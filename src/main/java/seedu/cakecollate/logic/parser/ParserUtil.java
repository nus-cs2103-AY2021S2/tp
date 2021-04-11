package seedu.cakecollate.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.commands.AddOrderItemCommand;
import seedu.cakecollate.logic.commands.RemindCommand;
import seedu.cakecollate.logic.parser.exceptions.IndexOutOfBoundsException;
import seedu.cakecollate.logic.parser.exceptions.NegativeIndexException;
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

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is invalid.";
    public static final String MESSAGE_NO_INDEX_PROVIDED = "No index provided";
    public static final int PHONE_LENGTH = 20;
    public static final int TAG_LENGTH = 30;
    public static final int INTEGER_LENGTH = 10;
    public static final int NAME_LENGTH = 70;
    public static final int ORDER_DESCRIPTION_LENGTH = 70;


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        boolean allDigits = oneBasedIndex.chars().allMatch(Character::isDigit);
        boolean lengthMoreThanTen = oneBasedIndex.length() > INTEGER_LENGTH;
        boolean allDigitsAndLengthMoreThanTen = allDigits && lengthMoreThanTen;
        if (allDigitsAndLengthMoreThanTen) {
            throw new IndexOutOfBoundsException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }
        if (StringUtil.isNegativeInteger(trimmedIndex)) {
            throw new NegativeIndexException(Messages.MESSAGE_NEGATIVE_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }


    /**
     * Parses {@code oneBasedIndexList} from String into an {@code IndexList} and returns it.
     * @param oneBasedIndexList one based indices input by the user.
     * @return list of indices sorted in descending order.
     * @throws ParseException if one of the specified indices is invalid.
     */
    public static IndexList parseIndexList(String oneBasedIndexList) throws ParseException {
        String[] indexListSplit = oneBasedIndexList.trim().split("\\s+");
        IndexList indexList = new IndexList(new ArrayList<>());
        for (String index: indexListSplit) {
            if ((!index.equals(" ")) && (!index.equals(""))) {
                Index parsedIndex = parseIndex(index.trim());
                indexList.add(parsedIndex);
            }
        }
        if (indexList.getIndexList().size() == 0) {
            throw new ParseException(MESSAGE_NO_INDEX_PROVIDED);
        }
        indexList.sortList();
        return indexList;
    }


    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new ParseException(Name.MESSAGE_EMPTY);
        }
        if (trimmedName.length() > NAME_LENGTH) {
            throw new ParseException(Name.MESSAGE_OVERFLOW);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (trimmedPhone.isEmpty()) {
            throw new ParseException(Phone.MESSAGE_EMPTY);
        }
        if (trimmedPhone.length() > PHONE_LENGTH) {
            throw new ParseException(Phone.MESSAGE_OVERFLOW);
        }
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (trimmedAddress.isEmpty()) {
            throw new ParseException(Address.MESSAGE_EMPTY);
        }
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (trimmedEmail.isEmpty()) {
            throw new ParseException(Email.MESSAGE_EMPTY);
        }
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String orderDescription} into a {@code OrderDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderDescription} is invalid.
     */
    public static OrderDescription parseOrderDescription(String orderDescription) throws ParseException {
        requireNonNull(orderDescription);
        String trimmedOrderDescription = orderDescription.trim();
        if (trimmedOrderDescription.length() > ORDER_DESCRIPTION_LENGTH) {
            throw new ParseException(OrderDescription.MESSAGE_OVERFLOW);
        }
        if (!OrderDescription.isValidOrderDescription(trimmedOrderDescription)) {
            throw new ParseException(OrderDescription.MESSAGE_CONSTRAINTS);
        }

        return new OrderDescription(trimmedOrderDescription);
    }

    /**
     * Parses {@code Collection<String> orderDescriptions} into a {@code Map<OrderDescription, Integer>}.
     */
    public static Map<OrderDescription, Integer> parseOrderDescriptions(Collection<String> orderDescriptions)
            throws ParseException {
        requireNonNull(orderDescriptions);
        final Map<OrderDescription, Integer> orderDescriptionMap = new HashMap<>();
        for (String str : orderDescriptions) {
            OrderDescription od = parseOrderDescription(str);
            int quantity = orderDescriptionMap.getOrDefault(od, 0);
            orderDescriptionMap.put(od, quantity + 1);
        }
        return orderDescriptionMap;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (trimmedTag.length() > TAG_LENGTH) {
            throw new ParseException(Tag.MESSAGE_OVERFLOW);
        }
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String deliveryDate} into an {@code DeliveryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deliveryDate} is invalid.
     */
    public static DeliveryDate parseDeliveryDate(String deliveryDate) throws ParseException {
        requireNonNull(deliveryDate);
        String trimmedDeliveryDate = deliveryDate.trim();
        if (trimmedDeliveryDate.isEmpty()) {
            throw new ParseException(DeliveryDate.MESSAGE_EMPTY);
        }
        if (!DeliveryDate.isValidFormat(trimmedDeliveryDate)) {
            throw new ParseException(DeliveryDate.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!DeliveryDate.isXDaysLater(trimmedDeliveryDate, 0L)) {
            throw new ParseException(String.format(DeliveryDate.MESSAGE_CONSTRAINTS_VALUE, trimmedDeliveryDate));
        }
        return new DeliveryDate(trimmedDeliveryDate);
    }

    /**
     * Parses a {@code String orderItemType} into an {@code OrderItem}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param orderItemType Type of the order item as entered by the user.
     *
     * @throws ParseException if the given {@code orderItemType} is invalid.
     */
    public static OrderItem parseOrderItem(String orderItemType) throws ParseException {
        requireNonNull(orderItemType);
        String trimmedOrderItemDescription = orderItemType.trim();
        if (trimmedOrderItemDescription.isEmpty()) {
            throw new ParseException(OrderDescription.MESSAGE_EMPTY);
        }
        if (trimmedOrderItemDescription.length() > ORDER_DESCRIPTION_LENGTH) {
            throw new ParseException(OrderDescription.MESSAGE_OVERFLOW);
        }
        if (!Type.isValidType(trimmedOrderItemDescription)) {
            throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderItemCommand.MESSAGE_USAGE));
        }
        return new OrderItem(new Type(trimmedOrderItemDescription));
    }

    /**
     * Parses a {@code String days} into a valid day}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code daus} is invalid.
     */

    public static int parseDays(String days) throws ParseException {
        requireNonNull(days);
        String[] inputs = days.replaceFirst("^\\s", "").split("\\s+");
        boolean isMoreThanOneInput = inputs.length > 1;
        String trimmedDays = days.trim();
        if (isMoreThanOneInput) {
            throw new ParseException(RemindCommand.MESSAGE_MULTIPLE_INPUTS);
        }
        if (trimmedDays.isEmpty()) {
            throw new ParseException(RemindCommand.MESSAGE_EMPTY);
        }
        if (!StringUtil.isUnsignedInteger(trimmedDays)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        }
        return Integer.parseInt(trimmedDays);
    }
}

