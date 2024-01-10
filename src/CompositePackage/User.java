package CompositePackage;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private int phoneNumber;
    private String userLogin;
    private String userPassword;
    private String email;
    private boolean isBlocked;

    public String getFirstName() {
        return firstName;
    }
    public User(){}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public User(String firstName, String lastName, int age, int phoneNumber, String userLogin,
                String userPassword, String email, boolean isBlocked) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.age = age;
       this.phoneNumber = phoneNumber;
       this.userLogin = userLogin;
       this.userPassword = userPassword;
       this.email = email;
       this.isBlocked = isBlocked;
   }
   public abstract void printDetails();
}