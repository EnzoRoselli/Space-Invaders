package MVC.Interface;

/**
 * This interface contains the messages used for the menu user-machine interaction
 * @author InvadersTeam
 * @since March 2019
 */
public interface ImessageForUser {

    /**
     * 
     * Shows minimum number of characters for the password.
     */
    public final String MINIMUM_SIZE = "The password must have at least 6 characters";

    /**
     * Shows maximum number of characters for the password.
     */
    public final String MAXIMUM_SIZE = "The password must have a maximum of 12 characters";

    /**
     *
     */
    public final String NO = "f";

    /**
     * Form filling instructions
     */
    public final String TEXT_AREAS_COMPLETED = "Complete all the text areas";

    /**
     *
     */
    public final String THIS = "this ";

    /**
     * Form filling instructions. Nickname already used.
     */
    public final String CHOOSE_ANOTHER = "is used, choose another";

    /**
     * Form filling instructions. Invalid password comparison
     */
    public final String DIFFERENT_PASS = "It doesn't match the chosen password"; //The passwords are not equals";

    /**
     * Form filling instructions. Email address misspelled.
     */
    public final String INVALID_EMAIL = "Invalid email, check your grammar";

    /**
     * Confirmation code message.
     */
    public final String CONFIRMATION_CODE = "This is the confirmation code spaceman";

    /**
     * Form filling instructions. Valid gmail account required.
     */
    public final String INSERT_VALID_GMAIL = "Insert a valid gmail account";

    /**
     * Connection failure.
     */
    public final String CONNECTION_FAILED = "Error with the connection";

    /**
     * Form filling instructions. 
     */
    public final String INVALID_USR_PASS = "Invalid user or password, try again";

    /**
     * File not found message.
     */
    public final String FILE_NOT_FOUND = "File not found";

    /**
     * Icon route.
     */
    public final String ICON_ROUTE = "../View/Images/icon.jpg";

}
