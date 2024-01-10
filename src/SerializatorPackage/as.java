package SerializatorPackage;

import CompositePackage.Admin;
import CompositePackage.RegularUser;
import CompositePackage.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class as {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new Admin("admin","admin",13,12345,"admin","admin","admin",false));
        list.add(new RegularUser("Артем","Кушнер",19,1488,"sanych","12345","1488@mail.ru",false));
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Users.bin"))) {
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
