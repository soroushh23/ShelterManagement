package uk.ac.ncl.CSC8014.Customer;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * This class is set as final as it won't be superclassed by another class
 */
public final class CustomerRecord {

    private final Name name;
    private final Date dateOfBirth;
    private final boolean hasGarden;
    private final CustomerNumber customerNumber;
    private final Date issueDate;


    /**
     * This constructor creates a CustomerRecord object that gets a unique customerNumber assigned to it once created
     *
     * @param firstName and @param lastName is used to create a Name object for each Customer
     */
    public CustomerRecord(String firstName, String lastName, Date dateOfBirth, boolean hasGarden) {
        this.name = new Name(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.hasGarden = hasGarden;
        this.customerNumber = CustomerNumber.getInstance(this.name);
        Calendar dateOfIssue = Calendar.getInstance();
        this.issueDate = dateOfIssue.getTime();
    }


    //The getter methods to have access to different attributes of the CustomerRecord object
    public Name getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public CustomerNumber getCustomerNumber() {
        return customerNumber;
    }

    public boolean hasGarden() {
        return hasGarden;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * The following overrides the equals method in the Object class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomerRecord)) {
            return false;
        }
        CustomerRecord cr = (CustomerRecord) o;
        return (Objects.equals(name, cr.name)) && (Objects.equals(dateOfBirth, cr.dateOfBirth));
        //return (name==null ? cr.name == null:name.equals(cr.name)) && (dateOfBirth == null ? cr.dateOfBirth==null: dateOfBirth.equals(cr.dateOfBirth)) ;
    }

    /**
     * The following overrides the hashcode method in the Object class because the equals method is overridden.
     */
    @Override
    public int hashCode() {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + (name == null ? 0 : name.hashCode());
        hc = multiplier * hc + (dateOfBirth == null ? 0 : dateOfBirth.hashCode());
        return hc;
    }

    // A string representation of CustomerRecord object
    @Override
    public String toString() {
        return "Name: " + name +
                " | Date Of Birth: " + dateOfBirth +
                " | Has Garden: " + hasGarden +
                " | Customer Number: " + customerNumber +
                " | Issue Date: " + issueDate +
                '.';
    }
}
