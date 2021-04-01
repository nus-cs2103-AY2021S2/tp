package seedu.address.ui;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


public class EmailWindow extends UiPart<Stage> {
    private static final String FXML = "EmailWindow.fxml";
    private static final String ACCESS_URL = "https://myaccount.google.com/intro/security";
    private static final Logger logger = LogsCenter.getLogger(EmailWindow.class);
    @javafx.fxml.FXML
    private TextField emailToField;
    @javafx.fxml.FXML
    private TextField emailFromField;
    @javafx.fxml.FXML
    private TextArea emailMessageField;
    @javafx.fxml.FXML
    private TextField emailSubjectField;
    @javafx.fxml.FXML
    private PasswordField emailPasswordField;
    @javafx.fxml.FXML
    private Label sentBoolValue;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the EmailWindow.
     */
    public EmailWindow(Stage root) {
        super(FXML, root);
    }

    public EmailWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     * <li>
     *     if this method is called on a thread other than the JavaFX Application Thread.
     * </li>
     * <li>
     *     if this method is called during animation or layout processing.
     * </li>
     * <li>
     *     if this method is called on the primary stage.
     * </li>
     * <li>
     *     if {@code dialogStage} is already showing.
     * </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing email page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the email window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the email window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the email window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     *
     *  Sends an email to the desired address.
     */
    //@@author TheCodingByte
    //Reused from https://github.com/TheCodingByte/SendEmailJFX
    public void sendEmail() {
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    //@@author TheCodingByte

    /**
     * Copies the URL to the Google Security page to the clipboard for user to enable login access from the app.
     */
    @javafx.fxml.FXML
    private void enableAccess() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(ACCESS_URL);
        clipboard.setContent(url);
    }
}
