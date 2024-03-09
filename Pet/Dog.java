package uk.ac.ncl.CSC8014.Pet;

/**
 * This class is set as final as it won't require to be superclassed by another class
 */
final class Dog extends PetAdoption {

    //This attribute is exclusive to the Dog objects
    private boolean trained;
    private final String careInstructions;

    /**
     * This constructor creates the same care instruction for each Dog object once they are created
     */
    Dog(PetID petID, String petType) {
        super(petID, petType);
        this.trained = false;
        this.careInstructions = ("feed three times a day and go on walks once a day.");
    }

    @Override
    public String getPetType() {
        return super.getPetType();
    }

    @Override
    public boolean getAdopted() {
        return super.getAdopted();
    }

    @Override
    public PetID getPetID() {
        return super.getPetID();
    }

    @Override
    public String getCareInstructions() {
        return careInstructions;
    }

    public boolean isTrained() {
        return trained;
    }

    /**
     * The reason for having this setter is to be able to change the "false" default state of trained attribute
     */
    public void setTrained(boolean trained) {
        this.trained = trained;
    }


    /**
     * The following setter method is used in shelterManager class to change
     * the Adoption status of the Pet object after being adopted
     */
    @Override
    public void setAdopted(boolean b) {
        super.setAdopted(b);
    }


    // A string representation of Dog object
    @Override
    public String toString() {
        return super.toString() + " | Care Instruction: " + getCareInstructions();
    }

}
