package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Blacklist;
import seedu.address.model.person.Person;

/**
 * Blacklists an existing non-blacklisted person in the address book.
 * Removes the person from blacklist if already blacklisted.
 */
public class BlacklistCommand extends Command {
    public static final String COMMAND_WORD = "blist";
    public static final String MESSAGE_BLACKLIST_SUCCESS = "Blacklisted Person: %1$s";
    public static final String MESSAGE_UNBLACKLIST_SUCCESS = "Removed Person from blacklist: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Blacklists/Un-blacklists the person identified "
            + "by the index number used in the last person listing. "
            + "Current blacklist status will be changed.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private static final Logger logger = Logger.getLogger("BlacklistLogger");
    private final Index index;
    private Blacklist blacklist;

    /**
     * @param index of the person in the filtered person list to edit the blacklist status
     */
    public BlacklistCommand(Index index) {
        requireNonNull(index);

        this.index = index;
        this.blacklist = new Blacklist();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Starting execution of BlacklistCommand");
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size() || index.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        blacklist = new Blacklist(!personToEdit.getBlacklist().isBlacklisted);
        assert(blacklist.isBlacklisted != personToEdit.getBlacklist().isBlacklisted);

        logger.log(Level.INFO, "Going to create replacement person");
        Person editedPerson = null;
        try {
            editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getModeOfContact(),
                    blacklist, personToEdit.getTags());
            assert(editedPerson.getBlacklist().isBlacklisted != personToEdit.getBlacklist().isBlacklisted);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Create person error", ex);
        }
        logger.log(Level.INFO, "Replacement person created");

        logger.log(Level.INFO, "Going to replace person in model");
        try {
            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Edit model error", ex);
        }
        logger.log(Level.INFO, "Person replaced");

        logger.log(Level.INFO, "End execution of BlacklistCommand");
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the blacklist is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = blacklist.isBlacklisted ? MESSAGE_BLACKLIST_SUCCESS : MESSAGE_UNBLACKLIST_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BlacklistCommand)) {
            return false;
        }

        // state check
        BlacklistCommand e = (BlacklistCommand) other;
        return index.equals(e.index);
    }
}
