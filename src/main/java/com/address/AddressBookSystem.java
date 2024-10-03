package com.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
}


public class AddressBookSystem {

    private List<Contact> contactList;

    public AddressBookSystem() {
        this.contactList = new ArrayList<>();
    }

    // Method to add a new contact to the address book
    public void addContact(Contact contact) {
        contactList.add(contact);
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

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program in AddressBookMain class ");

        AddressBookSystem  addressBook = new AddressBookSystem();

        Scanner sc = new Scanner(System.in);

        // Get contact details from the user
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

        // Create a new contact object with the entered details
        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

        // Add the contact to the address book
        addressBook.addContact(newContact);

        // Confirm addition
        System.out.println("Contact added successfully!");

        // Display all contacts in the address book
        System.out.println("\nContacts in Address Book:");
        addressBook.displayContacts();

    }

}