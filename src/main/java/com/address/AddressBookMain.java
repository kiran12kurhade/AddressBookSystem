package com.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class Contact {
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String email;
    private long phoneNumber;
    private String pinCode;

    public Contact(String firstName, String lastName, String city, String state, String email, long phoneNumber, String pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contact)) return false;
        Contact contact = (Contact) obj;
        return firstName.equalsIgnoreCase(contact.firstName) &&
                lastName.equalsIgnoreCase(contact.lastName);
    }

    @Override
    public int hashCode() {
        return (firstName.toLowerCase() + lastName.toLowerCase()).hashCode();
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (!isDuplicate(contact)) {
            this.contacts.add(contact);
            System.out.println("Contact added successfully!");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }
    }

    public boolean isDuplicate(Contact contact) {
        return contacts.stream().anyMatch(existingContact -> existingContact.equals(contact));
    }

    public Contact findContactByName(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public boolean deleteContactByName(String firstName, String lastName) {
        Contact contact = findContactByName(firstName, lastName);
        if (contact != null) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Contact> searchByCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "contacts=" + contacts +
                '}';
    }
}

public class AddressBookMain {
    private static Map<String, AddressBook> addressBookMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Address Book System Menu:");
            System.out.println("1. Create New Address Book");
            System.out.println("2. Select Address Book");
            System.out.println("3. Search Across Address Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createNewAddressBook(scanner);
                    break;
                case 2:
                    selectAddressBook(scanner);
                    break;
                case 3:
                    searchAcrossAddressBooks(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createNewAddressBook(Scanner scanner) {
        System.out.print("Enter a unique name for the Address Book: ");
        String addressBookName = scanner.nextLine();

        if (addressBookMap.containsKey(addressBookName)) {
            System.out.println("An Address Book with this name already exists.");
        } else {
            AddressBook addressBook = new AddressBook();
            addressBookMap.put(addressBookName, addressBook);
            System.out.println("Address Book '" + addressBookName + "' created successfully!");
        }
    }

    private static void selectAddressBook(Scanner scanner) {
        System.out.print("Enter the name of the Address Book to select: ");
        String addressBookName = scanner.nextLine();

        if (addressBookMap.containsKey(addressBookName)) {
            AddressBook addressBook = addressBookMap.get(addressBookName);
            manageAddressBook(scanner, addressBook);
        } else {
            System.out.println("Address Book not found.");
        }
    }

    private static void manageAddressBook(Scanner scanner, AddressBook addressBook) {
        boolean manageMore = true;

        while (manageMore) {
            System.out.println("Managing Address Book:");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addMultipleContacts(scanner, addressBook);
                    break;
                case 2:
                    viewAllContacts(addressBook);
                    break;
                case 3:
                    editContact(scanner, addressBook);
                    break;
                case 4:
                    deleteContact(scanner, addressBook);
                    break;
                case 5:
                    manageMore = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMultipleContacts(Scanner scanner, AddressBook addressBook) {
        boolean addMore = true;
        while (addMore) {
            addNewContact(scanner, addressBook);
            System.out.print("Do you want to add another contact? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                addMore = false;
            }
        }
    }

    private static void addNewContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        System.out.print("Enter State: ");
        String state = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Pin Code: ");
        String pinCode = scanner.nextLine();

        Contact contact = new Contact(firstName, lastName, city, state, email, phoneNumber, pinCode);
        addressBook.addContact(contact);
    }

    private static void viewAllContacts(AddressBook addressBook) {
        System.out.println(addressBook);
    }

    private static void editContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the First Name of the contact to edit: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the Last Name of the contact to edit: ");
        String lastName = scanner.nextLine();

        Contact contact = addressBook.findContactByName(firstName, lastName);

        if (contact != null) {
            System.out.print("Enter new City (leave blank to keep current): ");
            String city = scanner.nextLine();
            if (!city.isBlank()) {
                contact.setCity(city);
            }

            System.out.print("Enter new State (leave blank to keep current): ");
            String state = scanner.nextLine();
            if (!state.isBlank()) {
                contact.setState(state);
            }

            System.out.print("Enter new Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) {
                contact.setEmail(email);
            }

            System.out.print("Enter new Phone Number (leave blank to keep current): ");
            String phoneNumberInput = scanner.nextLine();
            if (!phoneNumberInput.isBlank()) {
                contact.setPhoneNumber(Long.parseLong(phoneNumberInput));
            }

            System.out.print("Enter new Pin Code (leave blank to keep current): ");
            String pinCode = scanner.nextLine();
            if (!pinCode.isBlank()) {
                contact.setPinCode(pinCode);
            }

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void deleteContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the First Name of the contact to delete: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the Last Name of the contact to delete: ");
        String lastName = scanner.nextLine();

        boolean isDeleted = addressBook.deleteContactByName(firstName, lastName);

        if (isDeleted) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void searchAcrossAddressBooks(Scanner scanner) {
        System.out.print("Enter city to search: ");
        String city = scanner.nextLine();
        List<Contact> results = new ArrayList<>();

        for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
            List<Contact> contactsInCity = entry.getValue().searchByCity(city);
            results.addAll(contactsInCity);
        }

        if (!results.isEmpty()) {
            System.out.println("Contacts found in city '" + city + "':");
            results.forEach(System.out::println);
        } else {
            System.out.println("No contacts found in city '" + city + "'.");
        }

        System.out.print("Enter state to search: ");
        String state = scanner.nextLine();
        results.clear();

        for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
            List<Contact> contactsInState = entry.getValue().searchByState(state);
            results.addAll(contactsInState);
        }

        if (!results.isEmpty()) {
            System.out.println("Contacts found in state '" + state + "':");
            results.forEach(System.out::println);
        } else {
            System.out.println("No contacts found in state '" + state + "'.");
        }
    }
}