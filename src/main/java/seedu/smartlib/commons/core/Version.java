package seedu.smartlib.commons.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a version with major, minor and patch number.
 */
public class Version implements Comparable<Version> {

    public static final String VERSION_REGEX = "V(\\d+)\\.(\\d+)\\.(\\d+)(ea)?";

    private static final String EXCEPTION_STRING_NOT_VERSION = "String is not a valid Version. %s";

    private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REGEX);

    private final int major;
    private final int minor;
    private final int patch;
    private final boolean isEarlyAccess;

    /**
     * Constructs a {@code Version} with the given version details.
     */
    public Version(int major, int minor, int patch, boolean isEarlyAccess) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.isEarlyAccess = isEarlyAccess;
    }

    /**
     * Returns the major component of the Version.
     *
     * @return the major component of the Version
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns the minor component of the Version.
     *
     * @return the minor component of the Version
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Returns the patch number of the Version.
     *
     * @return the patch number of the Version
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Indicates whether the current Version is an early access Version.
     *
     * @return true if the current Version is an early access Version, and false otherwise
     */
    public boolean isEarlyAccess() {
        return isEarlyAccess;
    }

    /**
     * Parses a version number string in the format V1.2.3.
     * @param versionString version number string
     * @return a Version object
     */
    @JsonCreator
    public static Version fromString(String versionString) throws IllegalArgumentException {
        Matcher versionMatcher = VERSION_PATTERN.matcher(versionString);

        if (!versionMatcher.find()) {
            throw new IllegalArgumentException(String.format(EXCEPTION_STRING_NOT_VERSION, versionString));
        }

        return new Version(Integer.parseInt(versionMatcher.group(1)),
                Integer.parseInt(versionMatcher.group(2)),
                Integer.parseInt(versionMatcher.group(3)),
                versionMatcher.group(4) == null ? false : true);
    }

    /**
     * Returns this Version object properly formatted in String format.
     *
     * @return this Version object properly formatted in String format
     */
    @JsonValue
    public String toString() {
        return String.format("V%d.%d.%d%s", major, minor, patch, isEarlyAccess ? "ea" : "");
    }

    /**
     * Compares this Version object to another Version object.
     *
     * @param other the other Version object to be compared
     * @return a positive value if this Version is newer than the other Version, and a negative value otherwise
     */
    @Override
    public int compareTo(Version other) {
        if (major != other.major) {
            return major - other.major;
        }
        if (minor != other.minor) {
            return minor - other.minor;
        }
        if (patch != other.patch) {
            return patch - other.patch;
        }
        if (isEarlyAccess == other.isEarlyAccess()) {
            return 0;
        }
        if (isEarlyAccess) {
            return -1;
        }
        return 1;
    }

    /**
     * Checks if this Version object is equal to another Version object.
     *
     * @param obj the other Version object to be compared
     * @return true if this Version object is equal to the other Version object, and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Version)) {
            return false;
        }
        final Version other = (Version) obj;

        return compareTo(other) == 0;
    }

    /**
     * Generates a hashcode for this Version object.
     *
     * @return the hashcode for this Version object
     */
    @Override
    public int hashCode() {
        String hash = String.format("%03d%03d%03d", major, minor, patch);
        if (!isEarlyAccess) {
            hash = "1" + hash;
        }
        return Integer.parseInt(hash);
    }

}
