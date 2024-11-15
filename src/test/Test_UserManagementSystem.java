package test;
import org.junit.jupiter.api.Test;
import source.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class Test_UserManagementSystem {
    private static final String USER_FILE = "src\\source\\User.csv";
    private static final String ADMIN_FILE = "src\\source\\Admin.csv";
    private static final String BACKUP_FILE = "src\\source\\User_backup.csv";

    @Test
    void testAuthenticationAdminUser() {
        AuthenticationManager authManager = new AuthenticationManager();
        User admin = authManager.authenticateUser("admin_user", "adminpass");

        assertNotNull(admin);
        assertEquals("admin_user", admin.getUsername());
        assertEquals("Admin", admin.userType);
    }


    @Test
    void testPowerUserAddUser() {
        AuthenticationManager authManager = new AuthenticationManager();
        User powerUser = authManager.authenticateUser("jane_doe", "pass456");

        assertNotNull(powerUser);
        assertTrue(powerUser instanceof PowerUser);

        User newUser = new RegularUser("ataur_jashim", "ataur@gmail.com", "pass64736");
        ((PowerUser) powerUser).addUser(newUser);

        String fileContents = assertDoesNotThrow(() -> Files.readString(Paths.get(USER_FILE)));
        assertTrue(fileContents.contains("ataur_jashim"));
    }


    @Test
    void testAdminRenameFile() {
        AuthenticationManager authManager = new AuthenticationManager();
        User admin = authManager.authenticateUser("admin_user", "adminpass");

        assertNotNull(admin);
        assertTrue(admin instanceof AdminUser);

        ((AdminUser) admin).renameFile(USER_FILE, BACKUP_FILE);

        assertTrue(Files.exists(Paths.get(BACKUP_FILE)));
        assertFalse(Files.exists(Paths.get(USER_FILE)));
    }

    @Test
    void testAuthenticationFailure() {
        AuthenticationManager authManager = new AuthenticationManager();
        User user = authManager.authenticateUser("john_bob", "wrongpass");

        assertNull(user);
    }

}