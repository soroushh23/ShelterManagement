package uk.ac.ncl.CSC8014.Test;

import uk.ac.ncl.CSC8014.Customer.Name;


public class NameTest {
    public static void main(String[] args) {
        NameTest nameTest = new NameTest();

        System.out.println("Test create Name");
        nameTest.createName();

        System.out.println("Test get Name information");
        nameTest.getNameInfo();

        System.out.println("Test hash code");
        nameTest.testHashCode();

        System.out.println("Test equals");
        nameTest.testEquals();

        System.out.println("Test toString");
        nameTest.testToString();
    }

    /**
     * This method tests the constructor in the Name class
     */
    private void createName() {
        //Test Normal Case1
        Name name = new Name("John", "Smith");
        Assertions.assertNotNull(name);

        //Test Normal Case1
        Name name2 = new Name("John", "Smith");
        Assertions.assertEquals(name, name2);
    }

    /**
     * This method tests the getters in the Name class
     */
    private void getNameInfo() {
        String firstName = "John";
        String lastName = "Smith";

        Name author = new Name("John", "Smith");
        Assertions.assertEquals(firstName, author.getFirstName());
        Assertions.assertEquals(lastName, author.getLastName());

    }

    /**
     * This method tests the hashcode method in the Name class
     */
    private void testHashCode() {
        Name Name = new Name("Chris", "Smith");
        Name Name2 = new Name("Jane", "Jones");
        Name NameCopy = new Name("Chris", "Smith");
        // Test the normal case
        Assertions.assertEquals(Name.hashCode(), NameCopy.hashCode());
        // Test the error case
        Assertions.assertNotEquals(Name.hashCode(), Name2.hashCode());


    }

    /**
     * This method tests the equals method in the Name class
     */
    private void testEquals() {
        Name author = new Name("Jane", "Jones");
        // Test the normal case1
        Assertions.assertTrue(author.equals(author));
        // Test the error case1
        Assertions.assertFalse(author.equals(null));
        // Test the error case2
        Assertions.assertFalse(author.equals("Jennifer"));
        // Test the normal case2
        Assertions.assertTrue(author.equals(new Name("Jane", "Jones")));
        // Test the error case3
        Assertions.assertFalse(author.equals(new Name("Jennifer", "Jones")));
        // Test the error case4
        Assertions.assertFalse(author.equals(new Name("Helen", "Lara")));
    }

    /**
     * This method tests the toString method in the Name class
     */
    private void testToString() {
        String toString1 = "John Smith";
        String toString2 = "john smith";

        Name name = new Name("John", "Smith");

        // Test the normal case
        Assertions.assertEquals(toString1, name.toString());

        // Test the error case
        Assertions.assertNotEquals(toString2, name.toString());
    }

}
