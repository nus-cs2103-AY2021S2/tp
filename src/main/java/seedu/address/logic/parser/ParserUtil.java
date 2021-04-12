package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.EditCommand.EditPolicyMode;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.shortcut.Shortcut;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index needs to be an integer more than or equals to 1, "
            + "and within the range of list indices displayed on the screen.";
    public static final String MESSAGE_INDEX_IS_WORD = "Index is missing or invalid!\nIf you meant to pass an "
            + "input, please remember to put the correct identifier and the slash e.g. \"n/\" for name.";
    public static final String MESSAGE_INVALID_BATCH_INDICES = "Multiple indices are only allowed the batch command.\n"
            + "If you entered a batch command, please separate your indices with commas.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] splitBySpace = trimmedIndex.split(" ");

        for (int i = 0; i < splitBySpace.length; i++) {
            if (!StringUtil.isNumbersOnly(splitBySpace[i])) {
                throw new ParseException(MESSAGE_INDEX_IS_WORD);
            }

            if (!StringUtil.isNonZeroUnsignedInteger(splitBySpace[i])) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
        }

        if (splitBySpace.length > 1) {
            throw new ParseException(MESSAGE_INVALID_BATCH_INDICES);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code edit policy mode} into a {@code an EditPolicyMode enumeration} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified mode does not match the required constraints).
     */
    public static EditPolicyMode parseEditPolicyMode(String editPolicyMode) throws ParseException {
        String trimmedEditPolicyMode = editPolicyMode.trim();
        switch (trimmedEditPolicyMode) {
        case "-insert":
            return EditPolicyMode.APPEND;
        case "-remove":
            return EditPolicyMode.REMOVE;
        case "-modify":
            return EditPolicyMode.MODIFY;
        default:
            throw new ParseException(EditPolicyMode.MESSAGE_EDIT_POLICY_MODE_CONSTRAINTS);
        }
    }
    /**
     * Parses {@code oneBasedIndices} and adds to a {@code List<Index>}. Leading and trailing whitespaces
     * will be trimmed. If there are duplicate inputs, a {@code ParseException} will be thrown.
     *
     * @param oneBasedIndices comma separated indices input by the user
     * @return {@code List<Index>}.
     * @throws ParseException if any of the specified index is invalid (not non-zero unsigned integer), or if there are
     * duplicate input indices.
     */
    public static List<Index> parseIndices(String oneBasedIndices) throws ParseException {
        String[] splitByComma = oneBasedIndices.split(",");
        for (int i = 0; i < splitByComma.length; i++) {
            splitByComma[i] = splitByComma[i].trim();
        }
        List<Index> listOfIndices = new ArrayList<>();
        Set<Index> setOfIndices = new HashSet<>();
        for (String index : splitByComma) {
            Index parsedIndex;

            try {
                parsedIndex = parseIndex(index);
            } catch (ParseException e) {
                throw new ParseException(e.getLocalizedMessage());
            }

            if (setOfIndices.contains(parsedIndex)) {
                throw new ParseException(BatchCommandParser.REPEATED_INDICES);
            }

            listOfIndices.add(parsedIndex);
            setOfIndices.add(parsedIndex);
        }
        return listOfIndices;
    }

    /**
     * Parses {@code attributes list in string form} into a {@code list of Attributes} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Set<Attribute> parseAttributes(List<String> attributes) throws ParseException {
        final Set<Attribute> parsedAttributesSet = new HashSet<>();
        boolean isAttributeUnique = true;
        for (String attribute : attributes) {
            switch (attribute) {
            case "-i":
                isAttributeUnique = parsedAttributesSet.add(Attribute.POLICY_ID);
                break;
            case "-p":
                isAttributeUnique = parsedAttributesSet.add(Attribute.PHONE);
                break;
            case "-e":
                isAttributeUnique = parsedAttributesSet.add(Attribute.EMAIL);
                break;
            case "-a":
                isAttributeUnique = parsedAttributesSet.add(Attribute.ADDRESS);
                break;
            case "-m":
                isAttributeUnique = parsedAttributesSet.add(Attribute.MEETING);
                break;
            default:
                throw new ParseException(Attribute.MESSAGE_ATTRIBUTE_CONSTRAINTS);
            }
            if (!isAttributeUnique) {
                throw new ParseException(Attribute.MESSAGE_ATTRIBUTE_UNIQUE_CONSTRAINTS);
            }
        }
        return parsedAttributesSet;
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
     * Parses a {@code String policy} into a {@code InsurancePolicy}.
     * Leading and trailing whitespaces will be trimmed from the initial input.
     * Leading and trailing whitespaces will also be trimmed from the input meant as URL.
     */
    private static InsurancePolicy parsePolicy(String policy) {
        requireNonNull(policy);
        String trimmedPolicy = policy.trim();
        String[] idAndUrl = trimmedPolicy.split(">", 2);

        if (!InsurancePolicy.hasPolicyUrl(idAndUrl)) {
            return new InsurancePolicy(idAndUrl[0]);
        }

        // Else contains URL too
        String policyId = idAndUrl[0];
        String policyUrl = idAndUrl[1].trim();

        return new InsurancePolicy(policyId, policyUrl);
    }

    /**
     * Parses {@code Collection<String> policies} into a {@code Set<InsurancePolicy>}.
     */
    public static Set<InsurancePolicy> parsePolicies(Collection<String> policies) {
        requireNonNull(policies);
        final Set<InsurancePolicy> policySet = new HashSet<>();
        for (String policy : policies) {
            requireNonNull(policy);
            InsurancePolicy parsedPolicy = parsePolicy(policy);
            policySet.add(parsedPolicy);
        }
        return policySet;
    }

    /**
     * Parses a {@code String meeting} into a {@code meeting}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meeting} is invalid.
     */
    public static Meeting parseMeeting(String meeting) throws ParseException {
        requireNonNull(meeting);
        try {
            String trimmedMeeting = meeting.trim();
            String[] arguments = trimmedMeeting.split("\\s+", 4);
            if (Meeting.isValidMeeting(arguments[0], arguments[1], arguments[2], arguments[3])) {
                return new Meeting(arguments[0], arguments[1], arguments[2], arguments[3]);
            } else {
                throw new ParseException(Meeting.MESSAGE_CONSTRAINTS);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParseException(Meeting.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code Collection<String> meeting} into a {@code List<Meeting>}.
     */
    public static List<Meeting> parseMeetings(Collection<String> meeting) throws ParseException {
        requireNonNull(meeting);
        final List<Meeting> meetingList = new ArrayList<>();
        for (String meet : meeting) {
            requireNonNull(meet);
            meetingList.add(parseMeeting(meet));
        }
        return meetingList;
    }

    /**
     * Parses a {@code String shortcutName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code shortcutName} is invalid.
     */
    public static String formatShortcutName(String shortcutName) throws ParseException {
        requireNonNull(shortcutName);
        String trimmedShortcutName = shortcutName.trim();
        if (!Shortcut.isValidShortcutName(trimmedShortcutName)) {
            throw new ParseException(Shortcut.MESSAGE_NAME_CONSTRAINTS);
        }
        return trimmedShortcutName;
    }

    /**
     * Parses a {@code String shortcutCommand}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code shortcutCommand} is invalid.
     */
    public static String formatShortcutCommand(String shortcutCommand) throws ParseException {
        requireNonNull(shortcutCommand);
        String trimmedShortcutCommand = shortcutCommand.trim();
        if (!Shortcut.isValidShortcutCommand(trimmedShortcutCommand)) {
            throw new ParseException(Shortcut.MESSAGE_COMMAND_CONSTRAINTS);
        }
        return trimmedShortcutCommand;
    }
}
