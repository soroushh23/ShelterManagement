package uk.ac.ncl.CSC8014.Pet;


public final class PetID {

    //Fields
    private final char letter;
    private final int number;
    private static int SERIALID = 10;
    private static char SERIALLETTER = 'A';


    //Constructor is private as it is only used in the factory method of this class
    private PetID() {
        this.letter = SERIALLETTER;
        this.number = SERIALID;
    }


    /**
     * The following factory method ensures uniqueness of the PetID objects created upon Creating a Pet
     * This method is primarily used in the factory method of the abstract class of petAdoption to assign the Pet object
     * a unique petID
     *
     * @return a unique PetID object
     */
    public static PetID getInstance() {

        // The following ensures that IDs only have a 2-digit numbers as specified in the coursework specification
        // i.e., once A99 is assigned to a pet the next pet will have B10 as the PetID
        if (SERIALID == 100) {
            SERIALLETTER++;
            SERIALID = 10;
        }

        // The following makes sure that Z99 is the highest possible PetID assignable
        if (SERIALLETTER > 'Z') {
            throw new IndexOutOfBoundsException("The shelter has reached to its Maximum capacity of Pets!");
        }

        PetID id = new PetID();
        SERIALID++;

        return id;
    }

    /**
     * The following overrides the equals method in the Object class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetID petID = (PetID) o;
        return letter == petID.letter && number == petID.number;
    }

    /**
     * The following overrides the hashcode method in the Object class because the equals method is overridden.
     */
    @Override
    public int hashCode() {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + (int) letter;
        hc = multiplier * hc + number;
        return hc;
    }


    // A string representation of PetID object
    public String toString() {
        return letter + "" + number;
    }

}
