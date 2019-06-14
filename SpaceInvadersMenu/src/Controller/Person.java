package Controller;

/**
 *
 * @author InvadersTeam
 */
public abstract class Person {
    private final String name,lastName;

    /**
     *
     * @param name
     * @param lastName
     */
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     *
     * @return
     */
    public abstract String showWelcome();
}
