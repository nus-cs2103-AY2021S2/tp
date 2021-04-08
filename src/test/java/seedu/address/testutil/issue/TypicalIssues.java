package seedu.address.testutil.issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.issue.Issue;

public class TypicalIssues {

    public static final Issue ISSUE_09_100 = new IssueBuilder()
            .withRoomNumber("09-100")
            .withDescription("Chair cylinder exploded")
            .withTimestamp("2020/12/11 06:53pm")
            .withStatus("pending")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_09_110 = new IssueBuilder()
            .withRoomNumber("09-110")
            .withDescription("Bed soaked from rain due to open window")
            .withTimestamp("2020/11/29 11:23pm")
            .withStatus("closed")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_10_100 = new IssueBuilder()
            .withRoomNumber("10-100")
            .withDescription("Broken table")
            .withTimestamp("2021/01/01 12:00pm")
            .withStatus("pending")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_11_110 = new IssueBuilder()
            .withRoomNumber("11-110")
            .withDescription("Flickering light")
            .withTimestamp("2021/03/20 08:35am")
            .withStatus("closed")
            .withCategory("Electrical")
            .build();
    public static final Issue ISSUE_12_110 = new IssueBuilder()
            .withRoomNumber("12-110")
            .withDescription("Cockroach everywhere")
            .withTimestamp("2020/08/10 08:48am")
            .withStatus("pending")
            .withCategory("Others")
            .build();

    private TypicalIssues() {
    } // prevents instantiation

    public static List<Issue> getTypicalIssues() {
        return new ArrayList<>(Arrays.asList(ISSUE_10_100, ISSUE_11_110, ISSUE_09_100, ISSUE_09_110, ISSUE_12_110));
    }

}
