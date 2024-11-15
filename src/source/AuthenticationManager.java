package source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class AuthenticationManager {
    private static List<User> users = new ArrayList<>();
    private static List<AdminUser> admins = new ArrayList<>();
  
    public AuthenticationManager() {
        loadUsers();
        loadAdmins();
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isEmailUnique(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\source\\User.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length > 4 && userDetails[2].equalsIgnoreCase(email)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
        }
        return true;
    }


    public static boolean isUsernameUnique(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\source\\User.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length > 4 && userDetails[1].equalsIgnoreCase(username)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
        }
        return true;
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\source\\User.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                User user;
                switch (details[4]) {
                    case "Regular":
                        user = new RegularUser(details[1], details[2], details[3]);
                        break;
                    case "Power":
                        user = new PowerUser(details[1], details[2], details[3]); 
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown user type");
                }
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    private void loadAdmins() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\source\\Admin.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                AdminUser admin = new AdminUser(details[1], details[2], details[3]); 
                admins.add(admin);
            }
        } catch (IOException e) {
            System.err.println("Error loading admins: " + e.getMessage());
        }
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Authentication successful for " + username);
                return user;
            }
        }

        for (AdminUser admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Admin authentication successful for " + username);
                return admin;
            }
        }

        System.out.println("Authentication failed.");
        return null;
    }
}
