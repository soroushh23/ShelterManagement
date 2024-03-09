package uk.ac.ncl.CSC8014.Customer;

import java.util.*;

/**
 * This class is set as final as it won't be superclassed by another class
 */
public final class CustomerNumber {
    private final char initial;
    private final int serialNumber;
    private final int month;
    private final int year;
    private static int SNumber = 10;
    private static final Map<String, CustomerNumber> CUSTOMERNUMBERS = new HashMap<>();


    /**
     * This constructor is used in the following factory method of the current class
     *
     * @param initial refers to the first letter of first name
     * @param month   refers to the month of issue date
     * @param year    refers to the year of issue date
     */
    private CustomerNumber(char initial, int month, int year) {
        this.initial = initial;
        this.serialNumber = SNumber;
        this.month = month;
        this.year = year;

    }

    /**
     * The following factory method takes a Name object and based on their first letter of their first name and the date of issue
     * (month and year) a unique CustomerNumber gets assigned to the Customer
     *
     * @param c is a Name type object
     * @return a CustomerNumber object that is used in the constructor of the CustomerRecord class
     */
    public static CustomerNumber getInstance(Name c) {

        //To get the initial of first name
        char initial;
        initial = c.getFirstName().toUpperCase().charAt(0);

        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH) + 1; // The plus 1 is because of the month variable in a Date object that start from 0.
        int year = a.get(Calendar.YEAR);

        String strRep = initial + "" + SNumber + "." + month + year;
        //The following ensures that each customer only has one unique CustomerNumber assigned to them.
        CustomerNumber cNumber = CUSTOMERNUMBERS.get(strRep);
        if (cNumber == null) {
            cNumber = new CustomerNumber(initial, month, year);
            CUSTOMERNUMBERS.put(strRep, cNumber);
        }
        SNumber++;
        return cNumber;
    }

    /**
     * The following overrides the equals method in the Object class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomerNumber))
            return false;
        CustomerNumber cn = (CustomerNumber) o;
        return (Objects.equals(initial, cn.initial)) && (Objects.equals(serialNumber, cn.serialNumber)) && (Objects.equals(month, cn.month)) && (Objects.equals(year, cn.year));
        //return initial == cn.initial && serialNumber == cn.serialNumber && month == cn.month && year == cn.year;
    }

    /**
     * The following overrides the hashcode method in the Object class because the equals method is overridden.
     */
    @Override
    public int hashCode() {
        int hc = 17;
        int multiplier = 37;
        hc = multiplier * hc + (int) initial;
        hc = multiplier * hc + serialNumber;
        hc = multiplier * hc + month;
        hc = multiplier * hc + year;
        return hc;
    }

    // A string representation of CustomerNumber object
    @Override
    public String toString() {
        return initial + "" + serialNumber + "." + month + year;
    }
}
