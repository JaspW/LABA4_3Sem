package CompositePackage;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String firstName, String lastName, int age, int phoneNumber, String userLogin, String userPassword, String email, boolean isBlocked) {
        super(firstName, lastName, age, phoneNumber, userLogin, userPassword, email, isBlocked);
    }

    @Override
    public void printDetails() {
        System.out.println("Администратор: " + getUserLogin());
    }
}
