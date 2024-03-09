package uk.ac.ncl.CSC8014.Pet;

import uk.ac.ncl.CSC8014.Customer.*;

import java.util.*;


public class ShelterManager {

    // This HashSet data structure stores all the pets' information that are added into the shelter
    private final Set<Pet> listOfPets = new HashSet<>();

    // This  HashSet data structure stores all the customers' information that are added into the shelter
    private final Set<CustomerRecord> listOfCustomers = new HashSet<>();

    //This HashMap data structure links all the pets that are adopted the customers
    private final Map<CustomerRecord, Collection<Pet>> customerPetMap = new HashMap<>();


    /**
     * This method turns true if the passed CustomerRecord object is already in the shelter's database
     * And return false if the customer is not added to the shelter.
     * This method is only public for the purpose of unit testing in the ShelterManagerTest class. Otherwise, it
     * would have been private as it is only used as an internal method in the ShelterManager class.
     */
    public boolean isCustomerInShelter(CustomerRecord customer) {
        for (CustomerRecord c : listOfCustomers) {
            if (c.equals(customer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used in the adoptPet method and upon availability of the type of pet object (either cat or dog)
     * return the pet and changes its default boolean of adopted from false to true
     * It also prints a message informing the shelter management if the requested pet type is not available in the shelter and returns null.
     * This method is only public for the purpose of unit testing in the ShelterManagerTest class. Otherwise, it
     * would have been private as it is only used as an internal method in the ShelterManager class.
     */
    public Pet getPetFromShelterSet(String pet) {
        for (Pet p : listOfPets) {
            if (p.getPetType().equalsIgnoreCase(pet) && !p.getAdopted()) {
                p.setAdopted(true);
                return p;
            }

        }
        System.out.println("No '" + pet + "' is available for adopting! ");
        return null;
    }


    /**
     * This method is used in eligibility18 and eligibility21 methods to extract the date information
     * from a Calendar object and store it as a Date object
     * This method is only public for the purpose of unit testing in the ShelterManagerTest class. Otherwise, it
     * would have been private as it is only used as an internal method in the ShelterManager class.
     */
    public Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * This method take a Date object parameter and compares it to the date 18 years ago from today's date.
     * then returns true if the parameter passed Date object is older than the date 18 years ago from the very current date.
     * This method is only public for the purpose of unit testing in the ShelterManagerTest class. Otherwise, it
     * would have been private as it is only used as an internal method in the ShelterManager class.
     *
     * @return true if the passed date is older than 18 years old.
     */
    public boolean eligibility18(Date dob) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, -18);
        //System.out.println("The person with the" +dob.getTime() +" is older than 18");
        return dob.before(calendarToDate(today));
    }

    /**
     * This method take a Date object parameter and compares it to the date 21 years ago from today's date.
     * then returns true if the parameter passed Date object is older than the date 21 years ago from the very current date.
     * This method is only public for the purpose of unit testing in the ShelterManagerTest class. Otherwise, it
     * would have been private as it is only used as an internal method in the ShelterManager class.
     *
     * @return true if the passed date is older than 21 years old.
     */
    public boolean eligibility21(Date dob) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, -21);
        //System.out.println("The person with the" +dob.getTime() +" is older than 21");
        return dob.before(calendarToDate(today));
    }

    /**
     * This method takes two parameters of CustomerRecord and Pet object types and adds them to the HashMap declared in
     * the field of this class.
     */
    private void addToMap(CustomerRecord customer, Pet pet) {
        // Retrieves the collection associated with the customer
        Collection<Pet> pets = customerPetMap.get(customer);

        // If the collection doesn't exist, creates a new one and puts it in the map
        if (pets == null) {
            pets = new ArrayList<>();
            customerPetMap.put(customer, pets);
        }

        // Adds the pet to the collection
        pets.add(pet);
    }


    //--------------------------------------------------------------------//
    //Task 1.3 methods

    /**
     * This method takes a string of pet type and adds a unique pet to the shelter.
     *
     * @return a Pet object
     */
    public Pet addPet(String petType) {
        //add your code here. Do NOT change the method signature
        Pet newpPet = PetAdoption.getInstance(petType);
        listOfPets.add(newpPet);
        return newpPet;
    }

    /**
     * This method first checks if a Dog type Pet is available in shelter and if the passed Boolean is true, it changes
     * the default trained status from false to true otherwise it doesn't chane anything and returns false
     *
     * @return true if the update has been successful
     */
    public Boolean updatePetRecord(PetID petID, Boolean trained) {
        //add your code here. Do NOT change the method signature
        for (Pet pets : listOfPets) {
            if (pets.getPetID().equals(petID) && pets instanceof Dog && trained) {
                ((Dog) pets).setTrained(true);
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns an int corresponding to the number of petType Pets that are not adopted in the shelter
     */
    public int noOfAvailablePets(String petType) {
        //add your code here. Do NOT change the method signature
        int count = 0;
        for (Pet pets : listOfPets) {
            if (pets.getPetType().equalsIgnoreCase(petType) && !pets.getAdopted()) {
                count++;
            }
        }
        return count;
    }


    //--------------------------------------------------------------------//
    //Task 2.2 methods

    /**
     * This method first checks if the customer is already existent in the shelter, so it can throw an error informing
     * that the entered details are already added in shelter. If the customer details are not in shelter then the
     * CustomerRecord object will be added to the shelter
     *
     * @return the CustomerRecord object
     */
    public CustomerRecord addCustomerRecord(String firstName, String lastName, Date dob, Boolean hasGarden) {
        //add your code here. Do NOT change the method signature
        CustomerRecord cr = new CustomerRecord(firstName, lastName, dob, hasGarden);
        for (CustomerRecord c : listOfCustomers) {
            if (c.equals(cr)) {
                throw new IllegalArgumentException("The Customer: \"" + c.getName() + "\" with [" + c.getCustomerNumber() + "] is already in the the record!");
            }
        }
        listOfCustomers.add(cr);
        return cr;
    }


    /**
     * This method first checks if the customer is above 18 years old. Only if so, it proceeds to the next checkpoints and
     * check if there is any suitable dog available to adopt based on both the age of the customer and owing a garden status.
     * If the person is above 21 years old a random dog is given to the customer, if the 21 years old check is not met,
     * then the customer has to be between 18-21 (as it was already checked not to have anyone under the age of 18), then
     * if there is a trained dog available in shelter, it would be assigned to the customer.
     * Finally, if the pet type (the string parameter in the method) is a cat and there is any cats available in the shelter
     * a random cat type pet is given to the customer.
     *
     * @return true if adopting pet has been successful
     */

    public Boolean adoptPet(CustomerRecord customerRecord, String petType) {
        //add your code here. Do NOT change the method signature

        //For further information please check the javadocs of eligibility21 method
        if (!isCustomerInShelter(customerRecord)) {
            System.out.println("The Customer: " + customerRecord.getName() + " not found in Shelter Manager Records!");
            return false;
        }

        //For further information please check the javadocs of eligibility18 method
        if (!eligibility18(customerRecord.getDateOfBirth())) {
            System.out.println("To adopt a pet the customer must be at least 18 year old! " + customerRecord.getName() + " is below 18 years old! ");
            return false;
        }


        /* The following 4 lines  avoid getting the error of "nullpointerexception". That would occur when the return
         value of Map.get(Object) is null. So it sets an empty ArrayList for the customer. Retrieve the collection
         associated with the customer.
         */
        Collection<Pet> pets = customerPetMap.get(customerRecord);
        if (pets == null) {
            pets = new ArrayList<>();
            customerPetMap.put(customerRecord, pets);
        }

        /*The following initially retrieves the collection of pets associated with the customer. In case of having three
          Pets by the customer it returns false and prints further information
         */
        int max_pet = 3;
        if (customerPetMap.get(customerRecord).size() >= max_pet) {
            System.out.println("The Customer: [" + customerRecord.getName() + "] with Customer ID: [" + customerRecord.getCustomerNumber() + "] has maximum of 3 pets adopted!");
            return false;
        }
        /*The following first ensures that in order to adopt a dog the customer is in the shelter database and owns a garden.
          Then, if they are older than 21 years old, any dog pet would be given too them and the method returns true.
          If they are not older than 21 years old, (they must be between 18-21) then it is checked to see if there is
          any trained dog in the shelter to be given to the customer ot not
         */
        if (petType.equalsIgnoreCase("dog")) {
            if (customerRecord.hasGarden()) {
                if (eligibility21(customerRecord.getDateOfBirth())) {
                    addToMap(customerRecord, getPetFromShelterSet(petType));
                    return true;
                } else {
                    for (Pet p : listOfPets) {
                        if (p instanceof Dog) {
                            Dog d = (Dog) p;
                            if (d.isTrained() && !d.getAdopted()) {
                                p.setAdopted(true);
                                addToMap(customerRecord, p);
                                return true;
                            }
                        }
                    }
                }
            } else {
                System.out.println("The customer: [" + customerRecord.getName() + "] with Customer ID:[" + customerRecord.getCustomerNumber() + "] needs to have a Garden to adopt a Dog! ");
                return false;
            }
        }

        /* If this method is reached it means that the customer is above 18 and is in the database, so it is only checked
         to see if there is any cat to be given the customer ot not.
         */
        if (petType.equalsIgnoreCase("cat")) {
            addToMap(customerRecord, getPetFromShelterSet(petType));
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method returns an unmodifiable collection of all pets currently adopted by the customer based on the passed
     * customerNumber. If the customer doesn't have any pets adopted, the return value is null
     */

    public Collection<Pet> adoptedPetsByCustomer(CustomerNumber customerNumber) {
        //add your code here. Do NOT change the method signature
        for (CustomerRecord cr : customerPetMap.keySet()) {
            if (cr.getCustomerNumber().equals(customerNumber)) {
                return Collections.unmodifiableCollection(customerPetMap.get(cr));
            }
        }
        return null;
    }
}
