package uk.ac.ncl.CSC8014.Test;

import uk.ac.ncl.CSC8014.Customer.CustomerRecord;
import uk.ac.ncl.CSC8014.Pet.Pet;
import uk.ac.ncl.CSC8014.Pet.PetID;
import uk.ac.ncl.CSC8014.Pet.ShelterManager;

import java.util.*;

public class ShelterManagerTest {
    public static void main(String[] args) {
        ShelterManagerTest shelterManagerTest = new ShelterManagerTest();

        System.out.println("Test Adding a Pet ");
        shelterManagerTest.addingPet();

        System.out.println("Test Updating Pet Record ");
        shelterManagerTest.updatePetRecord();

        System.out.println("Test Number of available pets ");
        shelterManagerTest.numberOfAvailablePets();

        System.out.println("Test Adding a Customer ");
        shelterManagerTest.addCustomerRecord();

        System.out.println("Test Adopting a Pet ");
        shelterManagerTest.adoptPet();

        System.out.println("Test Adopted Pets by Customer ");
        shelterManagerTest.adoptedPetsByCustomer();

        System.out.println("---------------- Tests of the Internal Methods in the ShelterManager Class ----------------");

        System.out.println("Test the internal method of isCustomerInShelter in the ShelterManager class ");
        shelterManagerTest.isCustomerInShelter();

        System.out.println("Test the internal method of getPetFromShelterSet in the ShelterManager class ");
        shelterManagerTest.getPetFromShelter();

        System.out.println("Test the internal method of calendarToDate in the ShelterManager class ");
        shelterManagerTest.calendarToDate();

        System.out.println("Test the internal method of eligibility18 in the ShelterManager class ");
        shelterManagerTest.eligibility18();

        System.out.println("Test the internal method of eligibility21 in the ShelterManager class ");
        shelterManagerTest.eligibility21();

    }

    private void addingPet() {
        //To test for normal case1 of creating a cat object
        ShelterManager shelterManager = new ShelterManager();
        Pet p1 = shelterManager.addPet("Cat");
        Assertions.assertNotNull(p1);

        //To test for normal case2 of creating a dog object
        Pet p2 = shelterManager.addPet("dog");
        Assertions.assertNotNull(p2);

        //To test for null case
        try {
            shelterManager.addPet(null);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(NullPointerException.class, t);
        }

        //To test for neither dog nor cat object
        try {
            shelterManager.addPet("Elephant100");
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, t);
        }

    }

    private void updatePetRecord() {
        ShelterManager shelterManager = new ShelterManager();
        Pet pet = shelterManager.addPet("dog");
        PetID petID = pet.getPetID();
        //To test for normal case1 of getting an untrained dog and train it
        boolean b1 = shelterManager.updatePetRecord(petID, true);
        Assertions.assertTrue(b1);
        //To test for normal case1 of getting an untrained dog and train it
        boolean b2 = shelterManager.updatePetRecord(petID, false);
        Assertions.assertFalse(b2);
    }

    private void numberOfAvailablePets() {
        //To test for normal case
        ShelterManager shelterManager = new ShelterManager();
        for (int i = 0; i < 10; i++) {
            shelterManager.addPet("CAT");
        }
        int numberOfCats = shelterManager.noOfAvailablePets("CAT");
        Assertions.assertEquals(10, numberOfCats);

        /*To test for the error case1. Due to the primitive data type of int when any string other cat or dog is passed
         the expected result would be 0
         */
        int j = shelterManager.noOfAvailablePets("Hi");
        Assertions.assertEquals(0, j);

        //To test for the error case2. Due to the primitive data type of int when a null is passed the expected result would be 0
        int i = shelterManager.noOfAvailablePets(null);
        Assertions.assertEquals(0, i);
    }


    private void addCustomerRecord() {
        ShelterManager shelterManager = new ShelterManager();
        //To test normal case1
        Calendar c1 = Calendar.getInstance();
        c1.set(1995, 10, 20);
        Date dob = c1.getTime();
        CustomerRecord cr1 = shelterManager.addCustomerRecord("Lucy", "O'Sullivan", dob, true);
        CustomerRecord cr2 = new CustomerRecord("LUCY", "O'Sullivan", dob, true);
        Assertions.assertEquals(cr1, cr2);

        //To test error case when a customer with the same name and dob is passed
        try {
            shelterManager.addCustomerRecord("Lucy", "O'Sullivan", dob, true);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }
    }

    private void adoptPet() {
        ShelterManager shelterManager = new ShelterManager();
        //To test for the normal case
        Calendar c1 = Calendar.getInstance();
        c1.set(1995, 10, 20);
        Date dob = c1.getTime();
        //The following creates a Customer object
        CustomerRecord cr1 = shelterManager.addCustomerRecord("Lucy", "O'Sullivan", dob, true);
        //The following creates a Pet object
        Pet p1 = shelterManager.addPet("CAT");
        Boolean b1 = shelterManager.adoptPet(cr1, "cat");
        Assertions.assertTrue(b1);

        //To test for an error case when the type of pet is neither cat nor dog
        boolean b2 = shelterManager.adoptPet(cr1, "Car");
        Assertions.assertFalse(b2);


    }


    private void adoptedPetsByCustomer() {
        ShelterManager shelterManager = new ShelterManager();

        //To test the normal case

        // Creates a Customer
        Calendar c1 = Calendar.getInstance();
        c1.set(1995, 10, 20);
        Date dob = c1.getTime();
        CustomerRecord cr1 = shelterManager.addCustomerRecord("Lucy", "O'Sullivan", dob, true);


        //Adding Pets to the Shelter
        for (int i = 0; i < 5; i++) {
            shelterManager.addPet("CAT");
        }
        for (int i = 0; i < 5; i++) {
            shelterManager.addPet("DOG");
        }

        // Adding Pets to the Customer
        shelterManager.adoptPet(cr1, "cat");
        shelterManager.adoptPet(cr1, "dog");
        //Creating an unmodifiable Collection of all Pets adopted by the Customer
        Collection<Pet> adoptedPetsByCustomer = shelterManager.adoptedPetsByCustomer(cr1.getCustomerNumber());

        // Checking number of pets available in the collection by comparing it to the expected value
        Assertions.assertEquals(2, adoptedPetsByCustomer.size());

        //To test for error case when customer has zero pets adopted so the expected return value is null
        Calendar c2 = Calendar.getInstance();
        c1.set(1995, 10, 20);
        Date dob2 = c1.getTime();
        CustomerRecord cr2 = shelterManager.addCustomerRecord("Lucy", "James", dob, true);
        Collection<Pet> adoptedPetsByCustomer2 = shelterManager.adoptedPetsByCustomer(cr2.getCustomerNumber());

        Assertions.assertNull(adoptedPetsByCustomer2);

    }


    //-----------------------------Test of Internal Methods in the ShelterManager Class


    private void isCustomerInShelter() {
        ShelterManager shelterManager = new ShelterManager();

        // Creates a Customer to test normal cases
        Calendar c1 = Calendar.getInstance();
        c1.set(1995, 10, 20);
        Date dob = c1.getTime();
        CustomerRecord cr1 = shelterManager.addCustomerRecord("Lucy", "O'Sullivan", dob, true);
        CustomerRecord cr2 = new CustomerRecord("James", "O'Sullivan", dob, true);

        boolean b1 = shelterManager.isCustomerInShelter(cr1);
        boolean b2 = shelterManager.isCustomerInShelter(cr2);

        Assertions.assertTrue(b1);
        Assertions.assertNotNull(b1);
        Assertions.assertFalse(b2);

    }


    private void getPetFromShelter() {
        ShelterManager shelterManager = new ShelterManager();

        // Creates a Pet object and checks for it in the shelter
        Pet p1 = shelterManager.addPet("CAT");
        Pet p2 = shelterManager.getPetFromShelterSet("cat");

        Assertions.assertEquals(p1, p2);
        Assertions.assertNotNull(p1);
        Assertions.assertNotNull(p2);
    }


    private void calendarToDate() {
        ShelterManager shelterManager = new ShelterManager();

        Calendar c1 = Calendar.getInstance();
        c1.set(2020, 5, 10);
        Date date1 = c1.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.set(2020, 5, 10);
        Date date2 = shelterManager.calendarToDate(c2);

        Assertions.assertEquals(date1, date2);
        Assertions.assertNotNull(date1);
    }

    private void eligibility18() {
        ShelterManager shelterManager = new ShelterManager();

        //This dob is 24 years old -> above 18 years old
        Calendar c1 = Calendar.getInstance();
        c1.set(2000, 2, 10);
        Date date1 = c1.getTime();
        boolean b1 = shelterManager.eligibility18(date1);

        //This dob is 10 years old -> less than 18 years old.
        Calendar c2 = Calendar.getInstance();
        c2.set(2013, 5, 10);
        Date date2 = c2.getTime();
        boolean b2 = shelterManager.eligibility18(date2);

        //This dob is 18 years old -> less than 18 years old (The edge case).
        //This dob is an edge case as this Assertion method was tested on 20/02/2024.
        Calendar c3 = Calendar.getInstance();
        c3.set(2006, 1, 19);
        Date date3 = c3.getTime();
        boolean b3 = shelterManager.eligibility18(date3);

        Assertions.assertTrue(b1);
        Assertions.assertFalse(b2);
        Assertions.assertTrue(b3);

    }


    private void eligibility21() {
        ShelterManager shelterManager = new ShelterManager();

        //This dob is 24 years old -> above 18 years old
        Calendar c1 = Calendar.getInstance();
        c1.set(2000, 2, 10);
        Date date1 = c1.getTime();
        boolean b1 = shelterManager.eligibility21(date1);

        //This dob is 10 years old -> less than 18 years old.
        Calendar c2 = Calendar.getInstance();
        c2.set(2013, 5, 10);
        Date date2 = c2.getTime();
        boolean b2 = shelterManager.eligibility21(date2);

        //This dob is 21 years old -> less than 21 years old (The edge case).
        //This dob is an edge case as this Assertion method was tested on 20/02/2024.
        Calendar c3 = Calendar.getInstance();
        c3.set(2003, 1, 19);
        Date date3 = c3.getTime();
        boolean b3 = shelterManager.eligibility21(date3);

        Assertions.assertTrue(b1);
        Assertions.assertFalse(b2);
        Assertions.assertTrue(b3);

    }

}
