package seedu.partyplanet.logic.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.tag.Tag;

/**
 * Finds all tags or tags that contain the given keywords.
 */
public class TagsCommand extends Command {

    public static final String COMMAND_WORD = "tags";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tags and the count of "
            + "person tagged in PartyPlanet.\n"
            + "If keyword is given, list all tags containing given keyword.\n"
            + "Parameters: [-f TAG]\n"
            + "Example: tags -f class";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " [-f KEYWORD]";

    private final Predicate<Tag> predicate;

    public TagsCommand(Predicate<Tag> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        Map<Tag, Integer> tags = new HashMap<>();
        model.getAddressBook()
            .getPersonList()
            .forEach(p -> p.getTags()
                    .forEach(t ->tags.compute(t, (k, v) -> v == null ? 1 : v + 1)));

        String output = tags.entrySet().stream()
            .filter(t -> this.predicate.test(t.getKey()))
            .sorted((x, y) -> x.getKey().tagName.compareTo(y.getKey().tagName))
            .map(t -> String.format("%s (%d)", t.getKey(), t.getValue()))
            .reduce("", (x, y) -> x + y + ", ");

        output = output.substring(0, output.length() - 2);

        return new CommandResult("List of Tags: "
            + output.replace("[", "").replace("]", ""), false);
    }
}
