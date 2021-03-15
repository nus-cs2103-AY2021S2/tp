package seedu.address.testutil;

import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.Residence;

/**
 * A utility class to help with building ResidenceTracker objects.
 * Example usage: <br>
 *     {@code ResidenceTracker rt = new ResidenceTrackerBuilder().withResidence(new Residence(...)).build();}
 */
public class ResidenceTrackerBuilder {

    private ResidenceTracker residenceTracker;

    public ResidenceTrackerBuilder() {
        residenceTracker = new ResidenceTracker();
    }

    public ResidenceTrackerBuilder(ResidenceTracker residenceTracker) {
        this.residenceTracker = residenceTracker;
    }

    /**
     * Adds a new {@code Residence} to the {@code ResidenceTracker} that we are building.
     */
    public ResidenceTrackerBuilder withResidence(Residence residence) {
        residenceTracker.addResidence(residence);
        return this;
    }

    public ResidenceTracker build() {
        return residenceTracker;
    }
}
