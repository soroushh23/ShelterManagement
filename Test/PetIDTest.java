package uk.ac.ncl.CSC8014.Test;


import uk.ac.ncl.CSC8014.Pet.PetID;

public class PetIDTest {
    public static void main(String[] args) {
        PetIDTest petIDTest = new PetIDTest();
        System.out.println("Test create PetID");
        petIDTest.createPetID();

        System.out.println("Test hash code");
        petIDTest.testHashCode();

        System.out.println("Test equals");
        petIDTest.testEquals();

        System.out.println("Test toString");
        petIDTest.testToString();

    }

    /**
     * This method tests the factory method of PetID class
     */
    private void createPetID() {
        // The following generates a unique PetID
        PetID petID1 = PetID.getInstance();
        PetID petID2 = PetID.getInstance();

        Assertions.assertNotNull(petID1);
        Assertions.assertNotEquals(petID1, petID2);

    }

    /**
     * This method tests hashcode of PetID class
     */
    private void testHashCode() {
        PetID petID3 = PetID.getInstance();
        PetID petID4 = PetID.getInstance();

        Assertions.assertNotEquals(petID3.hashCode(), petID4.hashCode());

    }

    /**
     * This method tests the equals method of PetID class
     */
    private void testEquals() {
        PetID petID5 = PetID.getInstance();
        PetID petID6 = PetID.getInstance();

        Assertions.assertNotEquals(petID5.hashCode(), petID6.hashCode());

        Assertions.assertFalse(petID5.equals(null));
        Assertions.assertFalse(petID5.equals(petID6));

    }

    /**
     * This method tests the toString method of PetID class
     */
    private void testToString() {
        /*The reason for having A16 is that I know my design for PetID starts from A10 and increments by 1. Hence, the
         petID7 object is the seventh object and the expected ID for that would be A16.
         */
        String toString = "A16";

        PetID petID7 = PetID.getInstance();

        Assertions.assertEquals(toString, petID7.toString());
    }

}
