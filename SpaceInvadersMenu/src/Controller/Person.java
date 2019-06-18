package Controller;

/**
 * This abstract class is the abstraction of a person.<p>
 * User has Person as a superclass.
 * @author InvadersTeam
 * @since March 2019.
 * @see User.
 */
public abstract class Person {
    private final String name,lastName;

    /**
     * This constructor receives and assigns the new person information.
     * @param name Person's name.
     * @param lastName Person's lastname.
     */
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     *
     * @return Person's name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Person's lastname.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     *
     * @return Welcome message.
     */
    public abstract String showWelcome();
}
