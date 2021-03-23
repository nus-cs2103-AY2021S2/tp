package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.splitToKeywordsList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;
import seedu.address.model.cheese.predicates.CheeseAssignmentStatusPredicate;
import seedu.address.model.cheese.predicates.CheeseCheeseTypePredicate;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;
import seedu.address.model.order.predicates.OrderCheeseTypePredicate;
import seedu.address.model.order.predicates.OrderCompletionStatusPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
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
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
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
     * Parses a {@code String cheeseType} into a {@code CheeseType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cheeseType} is invalid.
     */
    public static CheeseType parseCheeseType(String cheeseType) throws ParseException {
        requireNonNull(cheeseType);
        String trimmedCheeseType = StringUtil.convertToTitleCase(cheeseType.trim());
        if (!CheeseType.isValidType(trimmedCheeseType)) {
            throw new ParseException(CheeseType.MESSAGE_CONSTRAINTS);
        }
        return CheeseType.getCheeseType(trimmedCheeseType);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        int quantityInt = Integer.parseInt(trimmedQuantity);
        if (!Quantity.isValidQuantity(quantityInt)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(quantityInt);
    }

    /**
     * Parses a {@code String} status into a {@code CheeseAssignmentStatusPredicate}.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static CheeseAssignmentStatusPredicate parseCheeseAssignmentStatusKeyword(String status)
            throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!CheeseAssignmentStatusPredicate.isValidStatus(trimmedStatus)) {
            throw new ParseException(CheeseAssignmentStatusPredicate.MESSAGE_CONSTRAINTS);
        }
        return new CheeseAssignmentStatusPredicate(trimmedStatus);
    }

    /**
     * Parses a string of cheese type keywords into a {@code CheeseCheeseTypePredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    public static CheeseCheeseTypePredicate parseCheeseCheeseTypeKeywords(String keywords)
            throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(CheeseCheeseTypePredicate.MESSAGE_CONSTRAINTS);
        }
        return new CheeseCheeseTypePredicate(splitToKeywordsList(keywords));
    }

    /**
     * Parses a {@code String} status into a {@code OrderAssignmentStatusPredicate}.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static OrderCompletionStatusPredicate parseOrderCompletionStatusKeyword(String status)
            throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!OrderCompletionStatusPredicate.isValidStatus(trimmedStatus)) {
            throw new ParseException(OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS);
        }
        return new OrderCompletionStatusPredicate(trimmedStatus);
    }

    /**
     * Parses a string of cheese type keywords into a {@code OrderCheeseTypePredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    public static OrderCheeseTypePredicate parseOrderCheeseTypeKeywords(String keywords)
            throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(OrderCheeseTypePredicate.MESSAGE_CONSTRAINTS);
        }
        return new OrderCheeseTypePredicate(splitToKeywordsList(keywords));
    }

    /**
     * Parses a {@code String date} into a {@code OrderDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static OrderDate parseOrderDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!OrderDate.isValidDate(trimmedDate)) {
            throw new ParseException(OrderDate.MESSAGE_CONSTRAINTS);
        }
        return new OrderDate(trimmedDate);
    }

    /**
     * Parses a {@code String quantity} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static int parseInteger(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        int quantityInt = Integer.parseInt(trimmedQuantity);
        if (!Quantity.isValidQuantity(quantityInt)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return quantityInt;
    }

    /**
     * Parses a {@code String date} into a {@code ManufactureDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static ManufactureDate parseManufactureDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!ManufactureDate.isValidDate(trimmedDate)) {
            throw new ParseException(ManufactureDate.MESSAGE_CONSTRAINTS);
        }
        return new ManufactureDate(trimmedDate);
    }

    /**
     * Parses a {@code String date} into a {@code MaturityDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static MaturityDate parseMaturityDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!MaturityDate.isValidDate(trimmedDate)) {
            throw new ParseException(MaturityDate.MESSAGE_CONSTRAINTS);
        }
        return new MaturityDate(trimmedDate);
    }

    /**
     * Parses a {@code String date} into a {@code ExpiryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static ExpiryDate parseExpiryDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!ExpiryDate.isValidDate(trimmedDate)) {
            throw new ParseException(ExpiryDate.MESSAGE_CONSTRAINTS);
        }
        return new ExpiryDate(trimmedDate);
    }

    /**
     * Parses a {@code string} into a {@code keyword}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keyword} is invalid.
     */
    public static List<String> parseKeywordsList(String keywords, String fieldName) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(fieldName + " keyword is empty.");
        }
        return splitToKeywordsList(trimmedKeywords);
    }

}
