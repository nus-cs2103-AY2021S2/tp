package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Represents a Garment's colour in the wardrobe.
 */
public class Colour {
    public static final String MESSAGE_CONSTRAINTS =
            "Garment's Colour field must be non-empty and be either"
            + " red, orange, yellow, green, blue, pink"
            + " purple, brown, or black.";

    public static final String[] COLOURARRAY = {
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "pink",
        "purple",
        "brown",
        "black",
        "white",
        "beige",
        "grey"
    };

    public static final HashSet<String> COLOURS = new HashSet<>(Arrays.asList(COLOURARRAY));

    public static final HashMap<String, BufferedImage> SAMPLES = new HashMap<>();

    public static final HashMap<String, List<String>> MATCHES = new HashMap<>();

    public static final List<String> RED_MATCHES = Arrays.asList(
        "red",
        "blue",
        "grey",
        "white",
        "black",
        "pink",
        "beige"
    );

    public static final List<String> ORANGE_MATCHES = Arrays.asList(
        "orange",
        "green",
        "blue",
        "white",
        "black",
        "brown",
        "beige"
    );

    public static final List<String> YELLOW_MATCHES = Arrays.asList(
        "yellow",
        "green",
        "blue",
        "white",
        "black",
        "beige"
    );

    public static final List<String> GREEN_MATCHES = Arrays.asList(
        "green",
        "orange",
        "purple",
        "white",
        "black",
        "yellow",
        "blue"
    );

    public static final List<String> BLUE_MATCHES = Arrays.asList(
        "blue",
        "pink",
        "red",
        "yellow",
        "grey",
        "orange",
        "white",
        "black",
        "purple"
    );

    public static final List<String> PINK_MATCHES = Arrays.asList(
        "pink",
        "blue",
        "grey",
        "white",
        "black",
        "red",
        "beige"
    );

    public static final List<String> PURPLE_MATCHES = Arrays.asList(
        "purple",
        "orange",
        "grey",
        "green",
        "white",
        "black",
        "blue"
    );

    public static final List<String> BROWN_MATCHES = Arrays.asList(
        "brown",
        "beige",
        "white",
        "black",
        "orange"
    );

    public static final List<String> BLACK_MATCHES = Arrays.asList(
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "pink",
        "purple",
        "brown",
        "black",
        "white",
        "beige",
        "grey"
    );

    public static final List<String> WHITE_MATCHES = Arrays.asList(
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "pink",
        "purple",
        "brown",
        "black",
        "white",
        "beige",
        "grey"
    );

    public static final List<String> GREY_MATCHES = Arrays.asList(
        "pink",
        "red",
        "blue",
        "purple",
        "white",
        "black",
        "grey"
    );

    public static final List<String> BEIGE_MATCHES = Arrays.asList(
        "beige",
        "blue",
        "purple",
        "brown",
        "white",
        "black",
        "yellow",
        "orange"
    );

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String colour;

    /**
     * Constructs a {@code Colour}.
     *
     * @param colour A valid colour.
     */
    public Colour(String colour) {
        if (!COLOURS.contains(colour.toLowerCase())) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        requireNonNull(colour);
        this.colour = colour;
    }

    /**
     * Returns true if a given string is a valid colour.
     */
    public static boolean isValidColour(String test) {
        return test.matches(VALIDATION_REGEX) && COLOURS.contains(test.toLowerCase());
    }

    /**
     * Initialises the HashMap of colours and their respective matching colours.
     */
    public static void initialiseMatches() {
        MATCHES.put("red", RED_MATCHES);
        MATCHES.put("orange", ORANGE_MATCHES);
        MATCHES.put("yellow", YELLOW_MATCHES);
        MATCHES.put("green", GREEN_MATCHES);
        MATCHES.put("blue", BLUE_MATCHES);
        MATCHES.put("pink", PINK_MATCHES);
        MATCHES.put("purple", PURPLE_MATCHES);
        MATCHES.put("brown", BROWN_MATCHES);
        MATCHES.put("black", BLACK_MATCHES);
        MATCHES.put("white", WHITE_MATCHES);
        MATCHES.put("grey", GREY_MATCHES);
        MATCHES.put("beige", BEIGE_MATCHES);
    }

    /**
     * Initialises the HashMap of colours and their respective sample images.
     */
    public static void initialiseSamples() throws IOException {
        try {
            BufferedImage redSample = ImageIO.read(
                new File("./src/main/resources/images/redSample.jpg")
            );
            BufferedImage orangeSample = ImageIO.read(
                new File("./src/main/resources/images/orangeSample.jpg")
            );
            BufferedImage yellowSample = ImageIO.read(
                new File("./src/main/resources/images/yellowSample.jpg")
            );
            BufferedImage greenSample = ImageIO.read(
                new File("./src/main/resources/images/greenSample.jpg")
            );
            BufferedImage blueSample = ImageIO.read(
                new File("./src/main/resources/images/blueSample.jpg")
            );
            BufferedImage pinkSample = ImageIO.read(
                new File("./src/main/resources/images/pinkSample.jpg")
            );
            BufferedImage purpleSample = ImageIO.read(
                new File("./src/main/resources/images/purpleSample.jpg")
            );
            BufferedImage brownSample = ImageIO.read(
                new File("./src/main/resources/images/brownSample.jpg")
            );
            BufferedImage blackSample = ImageIO.read(
                new File("./src/main/resources/images/blackSample.jpg")
            );
            BufferedImage whiteSample = ImageIO.read(
                new File("./src/main/resources/images/whiteSample.jpg")
            );
            BufferedImage greySample = ImageIO.read(
                new File("./src/main/resources/images/greySample.jpg")
            );
            BufferedImage beigeSample = ImageIO.read(
                new File("./src/main/resources/images/beigeSample.jpg")
            );
            SAMPLES.put("red", redSample);
            SAMPLES.put("orange", orangeSample);
            SAMPLES.put("yellow", yellowSample);
            SAMPLES.put("green", greenSample);
            SAMPLES.put("blue", blueSample);
            SAMPLES.put("pink", pinkSample);
            SAMPLES.put("purple", purpleSample);
            SAMPLES.put("brown", brownSample);
            SAMPLES.put("black", blackSample);
            SAMPLES.put("white", whiteSample);
            SAMPLES.put("grey", greySample);
            SAMPLES.put("beige", beigeSample);
        } catch (IOException e) {
            e.printStackTrace(); //MAY NEED TO EDIT
        }
    }

    public List<String> getMatches() {
        return MATCHES.get(colour);
    }

    @Override
    public String toString() {
        return colour;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Colour // instanceof handles nulls
                && colour.equals(((Colour) other).colour)); // state check
    }

    @Override
    public int hashCode() {
        return colour.hashCode();
    }

}
