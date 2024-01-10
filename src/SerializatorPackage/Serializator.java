package SerializatorPackage;

import CompositePackage.RegularUser;
import CompositePackage.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {
    public static List<?> readList(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<?>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveList(List<?> list, String filename) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
         oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}