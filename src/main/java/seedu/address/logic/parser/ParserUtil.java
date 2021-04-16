package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings and models in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses {@code oneBasedIndexes} into a {@code List} of {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed as well as white spaces between indexes.
     * @throws ParseException if a specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndexes(String oneBasedIndexes) throws ParseException {
        String trimmedIndexes = oneBasedIndexes.trim();
        List<Index> indexList = new ArrayList<>();
        for (String indexStr : trimmedIndexes.split("\\s+")) {
            Index index = parseIndex(indexStr.trim());
            indexList.add(index);
        }
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
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();

        return new Remark(remark);
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
     *
     * @throws ParseException if any given {@code tags} is invalid.
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
     * Parses {@code String alias} into a {@code Alias}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code alias} is invalid.
     */
    public static Alias parseAlias(String alias) throws ParseException {
        requireNonNull(alias);
        String trimmedAlias = alias.trim();
        if (!Alias.isValidAlias(trimmedAlias)) {
            throw new ParseException(Alias.MESSAGE_CONSTRAINTS);
        }
        return new Alias(trimmedAlias);
    }

    /**
     * Parses {@code String command} into a {@code Command}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code command} is invalid.
     */
    public static Command parseCommand(String command) throws ParseException {
        requireNonNull(command);
        String trimmedCommandWord = command.trim();
        if (!Command.isValidCommand(trimmedCommandWord)) {
            throw new ParseException(Command.MESSAGE_CONSTRAINTS);
        }
        return new Command(trimmedCommandWord);
    }

    /**
     * Validates {@code Collection<String> tags} except the last tag in tags.
     * If the last tag contains value, the value will still be checked.
     *
     * @throws ParseException if any given {@code tags} is invalid.
     */
    public static void validateAllButLastTag(List<String> tags) throws ParseException {
        requireNonNull(tags);
        for (int i = 0; i < tags.size() - 1; i++) {
            parseTag(tags.get(i));
        }
        if (!tags.get(tags.size() - 1).isEmpty()) {
            parseTag(tags.get(tags.size() - 1));
        }
    }

    /**
     * Validates arguments {@code ArgumentMultiMap} of a Person for command alias. All arguments except the last prefix
     * argument is checked.
     *
     * @param argMultimap Arguments of person.
     * @param lastPrefix Last prefix.
     * @return True if all arguments in {@code ArgumentMultiMap argMultimap} except the last prefix argument are valid,
     *     otherwise false is returned.
     */
    public static boolean validatePersonAliasArgs(ArgumentMultimap argMultimap, Prefix lastPrefix) {
        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()
                    && !(lastPrefix == PREFIX_NAME && argMultimap.getValue(PREFIX_NAME).get().isEmpty())) {
                ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()
                    && !(lastPrefix == PREFIX_PHONE && argMultimap.getValue(PREFIX_PHONE).get().isEmpty())) {
                ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()
                    && !(lastPrefix == PREFIX_EMAIL && argMultimap.getValue(PREFIX_EMAIL).get().isEmpty())) {
                ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            }
            if (argMultimap.getValue(PREFIX_COMPANY).isPresent()
                    && !(lastPrefix == PREFIX_COMPANY && argMultimap.getValue(PREFIX_COMPANY).get().isEmpty())) {
                ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
            }
            if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()
                    && !(lastPrefix == PREFIX_JOB_TITLE && argMultimap.getValue(PREFIX_JOB_TITLE).get().isEmpty())) {
                ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get());
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                    && !(lastPrefix == PREFIX_ADDRESS && argMultimap.getValue(PREFIX_ADDRESS).get().isEmpty())) {
                ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            }
            if (lastPrefix != PREFIX_TAG) {
                ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            } else {
                ParserUtil.validateAllButLastTag(argMultimap.getAllValues(PREFIX_TAG));
            }
            if (argMultimap.getValue(PREFIX_REMARK).isPresent()
                    && !(lastPrefix == PREFIX_REMARK && argMultimap.getValue(PREFIX_REMARK).get().isEmpty())) {
                ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
            }
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * Validates prefix arguments {@code ArgumentMultiMap}. All arguments must be a valid prefix and must be empty.
     *
     * @param prefix Prefix to validate.
     * @param argMultimap Arguments of to validate.
     * @return true if prefix exists and is valid. false if prefix does not exist.
     * @throws ParseException if a prefix contains values.
     */
    public static boolean validatePrefixEmpty(Prefix prefix, ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return false;
        }
        for (String value: argMultimap.getAllValues(prefix)) {
            if (value.isEmpty()) {
                continue;
            }
            throw new ParseException(prefix.getPrefix());
        }
        return true;
    }

    /**
     * Parses a {@code String company} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String jobTitle} into an {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
    }

}
