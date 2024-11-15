package source;


public class AdminUser extends User implements Viewable, Editable, AdminPrivileges {
    public AdminUser(String username, String email, String password) {
        super(username, email, password, "Admin");
    }

    @Override
    public void viewUserFile() {
        System.out.println("Admin viewing User.csv");
        FileHandler.viewFile("src\\source\\User.csv");
    }

    @Override
    public void addUser(User user) {
        FileHandler.addUserToFile(user, "src\\source\\User.csv");
    }

    @Override
    public void renameFile(String oldName, String newName) {
        FileHandler.renameFile(oldName, newName);
    }

    @Override
    public void updateUserPrivilege(String username, String newPrivilege) {
        FileHandler.updateUserPrivilege(username, newPrivilege);
    }
}