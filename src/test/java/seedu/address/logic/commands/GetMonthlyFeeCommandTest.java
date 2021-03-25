package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalStudents.*;

public class GetMonthlyFeeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_correct_monthly_fee() throws CommandException {
        GetMonthlyFeeCommand getMonthlyFeeCommand = new GetMonthlyFeeCommand(ALICE.getName(),
            new Month(1), new Year(2021));
        CommandResult commandResult = getMonthlyFeeCommand.execute(model);
        assertEquals(String.format("Monthly fee for %s on %s, %s is $%.2f", ALICE.getName(),
            new Month(1).getMonthName(), new Year(2021), 120.90),
            commandResult.getFeedbackToUser());
    }
}
