package seedu.address.testutil.issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.issue.Issue;

public class TypicalIssues {
    public static final Issue ISSUE_10_100 = new IssueBuilder()
            .withRoomNumber("10-100")
            .withDescription("Broken table")
            .withTimestamp("2021/01/01 12:00pm")
            .withStatus("pending")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_20_109 = new IssueBuilder()
            .withRoomNumber("20-109")
            .withDescription("Flickering light")
            .withTimestamp("2021/03/20 8:35am")
            .withStatus("closed")
            .withCategory("Electrical")
            .build();
    public static final Issue ISSUE_15_112 = new IssueBuilder()
            .withRoomNumber("15-112")
            .withDescription("Chair cylinder exploded")
            .withTimestamp("2020/12/11 6:53pm")
            .withStatus("pending")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_04_119 = new IssueBuilder()
            .withRoomNumber("04-119")
            .withDescription("Bed soaked from rain due to open window")
            .withTimestamp("2020/11/29 11:23pm")
            .withStatus("closed")
            .withCategory("Furniture")
            .build();
    public static final Issue ISSUE_08_104 = new IssueBuilder()
            .withRoomNumber("08-104")
            .withDescription("Cockroach everywhere")
            .withTimestamp("2020/08/10 8:48am")
            .withStatus("pending")
            .withCategory("Others")
            .build();

    private TypicalIssues() {
    } // prevents instantiation

    public static List<Issue> getTypicalIssues() {
        return new ArrayList<>(Arrays.asList(ISSUE_10_100, ISSUE_20_109, ISSUE_15_112, ISSUE_04_119, ISSUE_08_104));
    }

}
