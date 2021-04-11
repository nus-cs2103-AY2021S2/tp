package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;
import seedu.address.model.garment.Garment;

/**
 * Finds and lists all garments in wardrobe whose colour matches that of a specified garment.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all garments whose colour "
            + "matches that of the specified\n"
            + "garment and displays them as a list with index numbers.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    /**
     * @param index of the garment in the filtered garment list to match
     */
    public MatchCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * @return {@code index} of Match Command object
     */
    public Index getIndex() {
        return this.index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Garment> lastShownList = model.getFilteredGarmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
        }

        Garment garmentToMatch = lastShownList.get(index.getZeroBased());
        List<String> keywords = new ArrayList<>();


        String keywordArgs = " c/";
        keywords.addAll(garmentToMatch.getColour().getMatches());
        for (String keyword : keywords) {
            keywordArgs = keywordArgs + keyword + " ";
        }

        keywordArgs = keywordArgs + "r/";
        keywords.clear();
        keywords.addAll(garmentToMatch.getDressCode().getMatches());
        for (String keyword : keywords) {
            keywordArgs = keywordArgs + keyword + " ";
        }

        keywordArgs = keywordArgs + "t/";
        keywords.clear();
        keywords.addAll(garmentToMatch.getType().getMatches());
        for (String keyword : keywords) {
            keywordArgs = keywordArgs + keyword + " ";
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(keywordArgs, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);

        FindCommand findMatches = new FindCommand(new AttributesContainsKeywordsPredicate(argMultimap));
        findMatches.execute(model);
        return new CommandResult("Matching results for garment\nname: " + garmentToMatch.getName()
                + "\nsize: " + garmentToMatch.getSize() + "\ncolour: " + garmentToMatch.getColour() + "\ndresscode: "
                + garmentToMatch.getDressCode() + "\ntype: " + garmentToMatch.getType());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchCommand)) {
            return false;
        }

        // state check
        MatchCommand m = (MatchCommand) other;

        return getIndex().equals(m.getIndex());
    }
}
