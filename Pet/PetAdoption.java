package uk.ac.ncl.CSC8014.Pet;

import java.util.HashMap;
import java.util.Map;

abstract class PetAdoption implements Pet {
    //Fields
    private static final String CAT = "cat";
    private static final String DOG = "dog";
    private final PetID petID;
    private final String petType;
    private boolean adopted;
    protected static Map<PetID, Pet> petNumber = new HashMap<>();


    //The constructor that is extended in the concrete classes of Cat and Dog
    PetAdoption(PetID petID, String petType) {
        this.petID = petID;
        this.petType = petType;
        this.adopted = false;
    }

    /**
     * The following factory method takes a string type of either cat or dog (case-insensitive)
     * and constructs a pet type object
     *
     * @param type is a string of cat or dog
     * @return a dog/cat object pointing to the Pet object
     */
    public static Pet getInstance(String type) {
        PetID m = PetID.getInstance();

        Pet p = petNumber.get(m);
        if (p != null) {
            return p;
        }
        if (type.equalsIgnoreCase(DOG)) {
            p = new Dog(m, DOG);
        } else if (type.equalsIgnoreCase(CAT)) {
            p = new Cat(m, CAT);
        } else {
            throw new IllegalArgumentException("Invalid Pet Type: " + type);
        }
        petNumber.put(m, p);
        return p;
    }

    @Override
    public String getPetType() {
        return petType;
    }

    @Override
    public boolean getAdopted() {
        return adopted;
    }

    @Override
    public String getCareInstructions() {
        return null;
    }

    public PetID getPetID() {
        return petID;
    }


    /**
     * The following setter method is used in shelterManager class to change
     * the Adoption status of the Pet object after being adopted
     */
    @Override
    public void setAdopted(boolean b) {
        this.adopted = b;
    }


    // A string representation of concrete objects that will be created as either a Cat or a Dog object
    @Override
    public String toString() {
        return "petID: " + petID + " | petType: " + petType + " | adopted: " + adopted;
    }
}
