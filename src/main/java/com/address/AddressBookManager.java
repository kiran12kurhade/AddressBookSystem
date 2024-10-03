package com.address;

import java.util.*;

class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    // Constructor
    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Override toString() for printing contact details
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contact)) return false;
        Contact contact = (Contact) obj;
        return firstName.equalsIgnoreCase(contact.firstName) && lastName.equalsIgnoreCase(contact.lastName);
    }

    @Override
    public int hashCode() {
        return 31 * firstName.toLowerCase().hashCode() + lastName.toLowerCase().hashCode();
    }
}


class AddressBookSystem {

    private List<Contact> contactList;

    public AddressBookSystem() {
        this.contactList = new ArrayList<>();
    }

    // Method to add a new contact to the address book
    public boolean addContact(Contact contact) {
        // Check for duplicate using Java Streams
        boolean isDuplicate = contactList.stream()
                .anyMatch(existingContact -> existingContact.equals(contact));

        if (isDuplicate) {
            System.out.println("Duplicate contact found: " + contact);
            return false; // Indicate that the contact was not added
        }

        contactList.add(contact);
        return true; // Indicate that the contact was added successfully
    }

    // Method to display all contacts in the address book
    public void displayContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found in the Address Book.");
        } else {
            for (Contact contact : contactList) {
                System.out.println(contact);
            }
        }
    }

    // Method to find a contact by their first or last name
    public Contact findContactByName(String name) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name) || contact.getLastName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null; // Return null if contact not found
    }

    // Method to edit an existing contact
    public void editContact(Contact contact, Scanner sc) {
        System.out.println("Editing contact: " + contact);
        System.out.println("Enter new details (leave blank to keep the existing value)");

        // Editing First Name
        System.out.println("First Name (" + contact.getFirstName() + "): ");
        String firstName = sc.nextLine();
        if (!firstName.trim().isEmpty()) {
            contact.setFirstName(firstName);
        }

        // Editing Last Name
        System.out.println("Last Name (" + contact.getLastName() + "): ");
        String lastName = sc.nextLine();
        if (!lastName.trim().isEmpty()) {
            contact.setLastName(lastName);
        }

        // Editing Address
        System.out.println("Address (" + contact.getAddress() + "): ");
        String address = sc.nextLine();
        if (!address.trim().isEmpty()) {
            contact.setAddress(address);
        }

        // Editing City
        System.out.println("City (" + contact.getCity() + "): ");
        String city = sc.nextLine();
        if (!city.trim().isEmpty()) {
            contact.setCity(city);
        }

        // Editing State
        System.out.println("State (" + contact.getState() + "): ");
        String state = sc.nextLine();
        if (!state.trim().isEmpty()) {
            contact.setState(state);
        }

        // Editing Zip
        System.out.println("Zip (" + contact.getZip() + "): ");
        String zip = sc.nextLine();
        if (!zip.trim().isEmpty()) {
            contact.setZip(zip);
        }

        // Editing Phone Number
        System.out.println("Phone Number (" + contact.getPhoneNumber() + "): ");
        String phoneNumber = sc.nextLine();
        if (!phoneNumber.trim().isEmpty()) {
            contact.setPhoneNumber(phoneNumber);
        }

        // Editing Email
        System.out.println("Email (" + contact.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.trim().isEmpty()) {
            contact.setEmail(email);
        }

        System.out.println("Contact updated successfully!");
    }

    // Method to delete a contact from the address book using first and last name
    public boolean deleteContact(String firstName, String lastName) {
        Iterator<Contact> iterator = contactList.iterator();

        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                iterator.remove(); // Remove the contact from the list
                return true; // Contact found and deleted
            }
        }
        return false; // Contact not found
    }
}

public class AddressBookManager {
    private Map<String, AddressBookSystem> addressBooks;

    public AddressBookManager() {
        addressBooks = new HashMap<>();
    }

    // Method to add a new Address Book
    public void addAddressBook(String name) {
        if (!addressBooks.containsKey(name)) {
            addressBooks.put(name, new AddressBookSystem());
            System.out.println("Address Book '" + name + "' created successfully.");
        } else {
            System.out.println("Address Book '" + name + "' already exists.");
        }
    }

    // Method to get an Address Book by name
    public AddressBookSystem getAddressBook(String name) {
        return addressBooks.get(name);
    }

    // Method to display all Address Books
    public void displayAddressBooks() {
        if (addressBooks.isEmpty()) {
            System.out.println("No Address Books found.");
        } else {
            System.out.println("Available Address Books:");
            for (String name : addressBooks.keySet()) {
                System.out.println("- " + name);
            }
        }
    }

    public static void main(String[] args) {
        AddressBookManager manager = new AddressBookManager();
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\nAddress Book Manager Menu:");
            System.out.println("1. Create New Address Book");
            System.out.println("2. Select Address Book");
            System.out.println("3. Display All Address Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // Create new address book
                    System.out.println("Enter Address Book Name: ");
                    String bookName = sc.nextLine();
                    manager.addAddressBook(bookName);
                    break;

                case 2: // Select address book to manage
                    System.out.println("Enter Address Book Name to manage: ");
                    String selectedBookName = sc.nextLine();
                    AddressBookSystem selectedAddressBook = manager.getAddressBook(selectedBookName);

                    if (selectedAddressBook != null) {
                        manageAddressBook(selectedAddressBook, sc);
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case 3: // Display all address books
                    manager.displayAddressBooks();
                    break;

                case 4: // Exit
                    running = false;
                    System.out.println("Exiting Address Book Manager...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        sc.close();
    }

    // Manage contacts in the selected Address Book
    private static void manageAddressBook(AddressBookSystem addressBook, Scanner sc) {
        boolean managing = true;

        while (managing) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add New Contact");
            System.out.println("2. Edit Existing Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // Add new contact
                    boolean addingContacts = true;
                    while (addingContacts) {
                        System.out.println("Enter First Name: ");
                        String firstName = sc.nextLine();
                        System.out.println("Enter Last Name: ");
                        String lastName = sc.nextLine();
                        System.out.println("Enter Address: ");
                        String address = sc.nextLine();
                        System.out.println("Enter City: ");
                        String city = sc.nextLine();
                        System.out.println("Enter State: ");
                        String state = sc.nextLine();
                        System.out.println("Enter Zip: ");
                        String zip = sc.nextLine();
                        System.out.println("Enter Phone Number: ");
                        String phoneNumber = sc.nextLine();
                        System.out.println("Enter Email: ");
                        String email = sc.nextLine();

                        // Create new contact and add to address book
                        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                        addressBook.addContact(newContact);
                        System.out.println("Contact added successfully!");

                        // Ask if the user wants to add another contact
                        System.out.println("Do you want to add another contact? (yes/no): ");
                        String response = sc.nextLine();
                        if (!response.equalsIgnoreCase("yes")) {
                            addingContacts = false;
                        }
                    }
                    break;

                case 2: // Edit existing contact
                    System.out.println("Enter the First Name of the contact to edit: ");
                    String editFirstName = sc.nextLine();
                    System.out.println("Enter the Last Name of the contact to edit: ");
                    String editLastName = sc.nextLine();

                    Contact contactToEdit = addressBook.findContactByName(editFirstName);
                    if (contactToEdit != null) {
                        addressBook.editContact(contactToEdit, sc);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3: // Display all contacts
                    addressBook.displayContacts();
                    break;

                case 4: // Delete a contact
                    System.out.println("Enter the First Name of the contact to delete: ");
                    String deleteFirstName = sc.nextLine();
                    System.out.println("Enter the Last Name of the contact to delete: ");
                    String deleteLastName = sc.nextLine();

                    boolean isDeleted = addressBook.deleteContact(deleteFirstName, deleteLastName);
                    if (isDeleted) {
                        System.out.println("Contact deleted successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 5: // Back to Main Menu
                    managing = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}