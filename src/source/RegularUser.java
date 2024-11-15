package source;

public class RegularUser extends User implements Viewable {
    public RegularUser(String username, String email, String password) {
        super(username, email, password, "Regular");
    }

    @Override
    public void viewUserFile() {
        System.out.println("Viewing User.csv (read-only)");
        FileHandler.viewFile("src\\source\\User.csv");
    }
}
