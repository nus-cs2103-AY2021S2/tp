package seedu.address.commons.util;




import javafx.scene.image.Image;


public class ImageRequestUtil {

    public static final String BASIC_IMAGE_REQUEST_URL = "https://www.gravatar.com/avatar/%s";
    public static final String SIZE_REQUEST = "?s=104";
    public static final String DEFAULT_IMAGE_REQUEST = "d=robohash";
    public static final String FILE_EXTENSION = ".jpg";


    /**
     * generates the image request URL from an email. The image request URL
     * will be used to get the image from Gravatar account associated to the email.
     *
     * @param email the email of the gravatar account.
     * @return the URL string of image request
     * @throws Exception when it is unable to get the hash correctly.
     */

    public static String generateImageRequest(String email) throws Exception {
        String hash = MD5Util.md5Hex(email.toLowerCase());
        String urlString = String.format(BASIC_IMAGE_REQUEST_URL
                + FILE_EXTENSION
                + SIZE_REQUEST
                + "&"
                + DEFAULT_IMAGE_REQUEST, hash
        );
        return urlString;
    }


    /**
     * Fetches Gravatar Image from Gravatar server given an email of the Gravatar avatar.
     *
     * @param email email of the Gravatar avatar
     * @return the javafx Image of the avatar
     * @throws Exception when unable to fetch image.
     */

    public static Image getGravatarImage(String email) throws Exception {
        String urlString = generateImageRequest(email);
        Image image = new Image(urlString);
        if (image.isError()) {
            throw new IllegalArgumentException("failed to load image");
        }
        return image;
    }

}

