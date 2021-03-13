package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.human.driver.Driver;

/**
 * Associates a Driver with the selected Persons
 */
public class DriveCommand extends Command {
    public static final String COMMAND_WORD = "drive";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Associates a Driver with the selected Commuters. "
            + "Parameters: "
            + PREFIX_NAME + "DRIVER NAME "
            + PREFIX_PHONE + "DRIVER PHONE "
            + PREFIX_COMMUTER + "COMMUTER "
            + "[" + PREFIX_COMMUTER + "COMMUTER]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_COMMUTER + "1 "
            + PREFIX_COMMUTER + "4 ";


    private final Driver driver;
    private final Set<Index> persons;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public DriveCommand(Driver driver, Set<Index> persons) {
        requireNonNull(driver);
        requireNonNull(persons);
        this.driver = driver;
        this.persons = persons;
    }

    @Override
    public CommandResult execute(Model model) {
        StringBuilder testOutput = new StringBuilder();
        testOutput.append(driver.toString());
        testOutput.append("\n");
        for (Index idx : persons) {
            testOutput.append(idx.getZeroBased());
        }
        return new CommandResult(testOutput.toString());
    }
}
