package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEPARATOR;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Collects the specified details of all contacts in the visible list.
 */
public class CollectCommand extends Command {

    public static final String COMMAND_WORD = "collect";
    public static final String DEFAULT_SEPARATOR = ";";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Collects the specified details of all "
            + "contacts in the last contact listing. "
            + "Exactly one type of detail must be specified. "
            + "If no separator is provided, the default separator is a semicolon.\n"
            + "Parameters: ["
            + PREFIX_NAME + "] or ["
            + PREFIX_PHONE + "] or ["
            + PREFIX_EMAIL + "] or ["
            + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_SEPARATOR + "SEPARATOR]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + " "
            + PREFIX_SEPARATOR + ",";
    public static final String MESSAGE_EMPTY_DETAILS =
            "No details to collect.";
    public static final String MESSAGE_EMPTY_LIST =
            "No contacts to collect details from.";

    private final Prefix detailType;
    private final String separator;
    private String collectedDetails;

    /**
     * @param detailType the type of detail to collect per person
     * @param separator used to separate every detail
     */
    public CollectCommand(Prefix detailType, String separator) {
        requireAllNonNull(detailType, separator);
        this.detailType = detailType;
        this.separator = separator;
        this.collectedDetails = "";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }

        if (detailType.equals(PREFIX_ADDRESS)) {
            collectAddress(lastShownList);
        } else if (detailType.equals(PREFIX_EMAIL)) {
            collectEmail(lastShownList);
        } else if (detailType.equals(PREFIX_NAME)) {
            collectName(lastShownList);
        } else if (detailType.equals(PREFIX_PHONE)) {
            collectPhone(lastShownList);
        } else {
            throw new CommandException(MESSAGE_USAGE);
        }

        if (collectedDetails.equals("")) {
            throw new CommandException(MESSAGE_EMPTY_DETAILS);
        }

        return new CommandResult(collectedDetails);
    }

    private void collectAddress(List<Person> lastShownList) {
        for (Person person : lastShownList) {
            assert (person.getAddress() != null);
            String address = person.getAddress().toString();
            if (address.equals("")) {
                continue;
            }
            collectedDetails += address + separator;
        }
    }

    private void collectEmail(List<Person> lastShownList) {
        for (Person person : lastShownList) {
            assert (person.getEmail() != null);
            String email = person.getEmail().toString();
            if (email.equals("")) {
                continue;
            }
            collectedDetails += email + separator;
        }
    }

    private void collectName(List<Person> lastShownList) {
        for (Person person : lastShownList) {
            assert (person.getName() != null);
            String name = person.getName().toString();
            if (name.equals("")) {
                continue;
            }
            collectedDetails += name + separator;
        }
    }

    private void collectPhone(List<Person> lastShownList) {
        for (Person person : lastShownList) {
            assert (person.getPhone() != null);
            String phone = person.getPhone().toString();
            if (phone.equals("")) {
                continue;
            }
            collectedDetails += phone + separator;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CollectCommand)) {
            return false;
        }

        // state check
        CollectCommand e = (CollectCommand) other;
        return detailType.equals(e.detailType)
                && separator.equals(e.separator);
    }
}
