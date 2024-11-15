package source;

public abstract class User {
   
    protected String username;
    protected String email;
    protected String password;
    public String userType;

   
    public User(String username, String email, String password, String userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public abstract void viewUserFile();

       public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
