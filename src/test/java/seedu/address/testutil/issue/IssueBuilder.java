package seedu.address.testutil.issue;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Issue objects.
 */
public class IssueBuilder {
    public static final String DEFAULT_ROOM_NUMBER = "10-100";
    public static final String DEFAULT_DESCRIPTION = "Table shaky";
    public static final String DEFAULT_TIMESTAMP = "2021/01/01 12:30pm";
    public static final String DEFAULT_STATUS = "pending";
    public static final String DEFAULT_CATEGORY = "Furniture";

    private RoomNumber roomNumber;
    private Description description;
    private Timestamp timestamp;
    private Status status;
    private Category category;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Creates a {@code IssueBuilder} with the default details.
     */
    public IssueBuilder() {
        this.roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.timestamp = new Timestamp(DEFAULT_TIMESTAMP);
        this.status = new Status(DEFAULT_STATUS);
        this.category = new Category(DEFAULT_CATEGORY);
    }

    /**
     * Initializes the IssueBuilder with the data of {@code issueToCopy}.
     */
    public IssueBuilder(Issue issueToCopy) {
        this.roomNumber = issueToCopy.getRoomNumber();
        this.description = issueToCopy.getDescription();
        this.timestamp = issueToCopy.getTimestamp();
        this.status = issueToCopy.getStatus();
        this.category = issueToCopy.getCategory();
        this.tags = new HashSet<>();
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Issue} that we are building.
     *
     * @param roomNumber The room number to build the issue with.
     * @return The IssueBuilder itself.
     */
    public IssueBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = new RoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Issue} that we are building.
     *
     * @param description The description to build the issue with.
     * @return The IssueBuilder itself.
     */
    public IssueBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Timestamp} of the {@code Issue} that we are building.
     *
     * @param timestamp The timestamp to build the issue with.
     * @return The IssueBuilder itself.
     */
    public IssueBuilder withTimestamp(String timestamp) {
        this.timestamp = new Timestamp(timestamp);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Issue} that we are building.
     *
     * @param status The status to build the issue with.
     * @return The IssueBuilder itself.
     */
    public IssueBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Issue} that we are building.
     *
     * @param category The category to build the issue with.
     * @return The IssueBuilder itself.
     */
    public IssueBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Builds the issue based on the provided parameters
     *
     * @return Issue with properties issued through the various methods of the {@code IssueBuilder} class
     */
    public Issue build() {
        return new Issue(roomNumber, description, timestamp, status, category, tags);
    }

}
