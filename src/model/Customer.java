package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String emailRegEx = "^(.+)@(.+).com$";
    private final Pattern pattern = Pattern.compile(emailRegEx);

    public Customer(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, Invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public final String getName() {
        return firstName + " " + lastName;
    }

    public final String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "{ Name of the Customer: " + firstName + " " + lastName + ", Email: " + email + "}";
    }
}
