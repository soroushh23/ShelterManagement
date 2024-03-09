package uk.ac.ncl.CSC8014.Pet;

/**
 * This class is set as final as it won't require to be superclassed by another class
 */
final class Cat extends PetAdoption {

    private final String careInstruction;

    /**
     * This constructor creates the same care instruction for each Cat object once they are created
     */
    Cat(PetID petID, String petType) {
        super(petID, petType);
        this.careInstruction = ("feed two times a day.");
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
    public String getCareInstructions() {
        return careInstruction;
    }

    @Override
    public PetID getPetID() {
        return super.getPetID();
    }


    // A string representation of Cat object
    @Override
    public String toString() {
        return super.toString() + " | Care Instruction: " + careInstruction;
    }
}
