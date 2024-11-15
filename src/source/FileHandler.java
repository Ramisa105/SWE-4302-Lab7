package source;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileHandler {
    public static void viewFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void addUserToFile(User user, String fileName) {

        if (AuthenticationManager.isUsernameUnique(user.getUsername()) && AuthenticationManager.isEmailUnique(user.getEmail())) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(String.join(",", AuthenticationManager.generateUniqueId(), user.getUsername(), user.getEmail(), user.getPassword(), user.userType));
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.err.println("Error: Duplicate username or email detected.");
        }
    }

    public static void renameFile(String oldName, String newName) {
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed to " + newName);
        } else {
            System.err.println("File renaming failed.");
        }
    }

    public static void updateUserPrivilege(String username, String newPrivilege) {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\source\\User.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(username)) {
                    details[4] = newPrivilege;
                }
                users.add(details);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\source\\User.csv"))) {
            for (String[] userDetails : users) {
                writer.write(String.join(",", userDetails));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
