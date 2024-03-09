package uk.ac.ncl.CSC8014.Customer;

/**
 * This class is set as final as it won't be superclassed by another class
 */
public final class Name {
    //Fields
    private final String firstName;
    private final String lastName;

    //Constructor
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    /**
     * The following overrides the equals method in the Object class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return (firstName.equalsIgnoreCase(name.firstName)) && (lastName.equalsIgnoreCase(name.lastName));
        //        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    /**
     * The following overrides the hashcode method in the Object class because the equals method is overridden.
     */
    @Override
    public int hashCode() {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + (firstName == null ? 0 : firstName.hashCode());
        hc = multiplier * hc + (lastName == null ? 0 : lastName.hashCode());
        return hc;
    }


    // A string representation of CustomerRecord object
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

