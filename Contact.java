package sh.harold.contactsapp;

public class Contact {
    private String firstName;  // the first name of the contact
    private String lastName;  // the last name of the contact
    private String email;  // the email address of the contact
    private String phone;  // the phone number of the contact
    private String address;  // the street address of the contact
    private String city;  // the city of the contact's address
    private String province;  // the province of the contact's address


    /**
     * Constructs a Contact object with the given details.
     *
     * @param firstName the first name of the contact
     * @param lastName the last name of the contact
     * @param email the email address of the contact
     * @param phone the phone number of the contact
     * @param address the street address of the contact
     * @param city the city of the contact's address
     * @param province the province of the contact's address
     */
    public Contact(String firstName, String lastName, String email, String phone, String address, String city, String province) {
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);  // set the email address of the contact, with input validation
        setPhone(phone);  // set the phone number of the contact, with input validation
        this.address = address;
        this.city = city;
        this.province = province;
    }

    /**
     * Returns the first name of the contact.
     *
     * @return the first name of the contact
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the contact.
     *
     * @param firstName the first name of the contact
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the contact.
     *
     * @return the last name of the contact
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the contact.
     *
     * @param lastName the last name of the contact
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the contact.
     *
     * @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact, with input validation.
     * Throws an IllegalArgumentException if the email address is invalid.
     *
     * @param email the email address of the contact
     * @throws IllegalArgumentException if the email address is invalid
     */
    public void setEmail(String email) {
        // regular expression pattern for validating email addresses
        String emailRegex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email.matches(emailRegex)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    /**
     * Returns the phone number of the contact.
     *
     * @return the phone number of the contact
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of this contact with input validation.
     *
     * @param phone The new phone number of this contact.
     * @throws IllegalArgumentException If the phone number is invalid.
     */
    public void setPhone(String phone) {
        String phoneRegex = "^\\d{10}$";
        if (phone.matches(phoneRegex)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    /**
     * Gets the address of this contact.
     *
     * @return The address of this contact.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this contact.
     *
     * @param address The new address of this contact.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city of this contact.
     *
     * @return The city of this contact.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of this contact.
     *
     * @param city The new city of this contact.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the province of this contact.
     *
     * @return The province of this contact.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province of this contact.
     *
     * @param province The new province of this contact.
     */
    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
