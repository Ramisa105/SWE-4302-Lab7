package source;

public class PowerUser extends User implements Viewable, Editable {
    public PowerUser(String username, String email, String password) {
        super(username, email, password, "Power");
    }

    @Override
    public void viewUserFile() {
        System.out.println("Viewing User.csv with read/write access");
        FileHandler.viewFile("src\\source\\User.csv");
    }

    @Override
    public void addUser(User user) {
        FileHandler.addUserToFile(user, "src\\source\\User.csv");
    }
}