package com.tomas.demo;

public class PaymentUsers {
    public static void main(String[] args) {
        String username = "BankCard";
        String password = "BankSafe";

        String userInputUsername = "BankCart";
        String userInputPassword = "BankSafe";

        // Checking the validity of the input
        if (username.equals(userInputUsername) && password.equals(userInputPassword)) {
            System.out.println("Authentication Successful");
        } else {
            System.out.println("Username/Password not matching");
        }
    }
}
