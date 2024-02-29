package com.example.project_p3;

public class UserTests {
    public static void main(String[] args) {
        testUser();
    }

    public static void testUser() {
        String Username = "TestUser";
        String Password = "TestPassword";
        User user = new User(Username, Password);
        System.out.println("Testing User Constructor:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Test passed!");
    }
}
