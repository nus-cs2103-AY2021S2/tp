package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views the full specified entry in a separate window.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the full entry identified by the index number used in the displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "%1$s";

    private final Index targetIndex;

    /**
     * Creates an ViewCommand to view the specified {@code Entry}
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(targetIndex.getZeroBased());
        HashMap<String, String> personDetails = new HashMap<>();
        personDetails.put("name", person.getName().fullName);
        personDetails.put("rating", person.getRating().value);
        personDetails.put("address", person.getAddress().value);
        personDetails.put("review", person.getReview().value);
        String tags = person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagCategory))
                .map(tag -> tag.tagCategory.titleCase() + ";")
                .collect(Collectors.joining());
        personDetails.put("tags", tags);
        return new CommandResult(personDetails, String.format(MESSAGE_VIEW_PERSON_SUCCESS, person),
                false , true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
