package CompositePackage;

import FlowerPackage.Flower;
import FlowerPackage.Operationable;
import MainPackage.Main;
import SerializatorPackage.Serializator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersList extends User{
    Scanner scanner = new Scanner(System.in);
    public List<User> getUsers() {
        return users;
    }
    private List<User> users = new ArrayList<>();
    private ArrayList<Flower> flowerArrayList;

    public UsersList(){
        users = (List<User>) Serializator.readList("Users.bin");
    }

    public int authorization() {
        int userInd = -1;
        String log, pass;
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин:");
        log = scanner.next();
        System.out.println("Введите пароль:");
        pass = scanner.next();
        do {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserLogin().equals(log) && users.get(i).getUserPassword().equals(pass)) {
                    userInd = i;
                    check = true;
                    break;
                }
            }
            if (userInd == -1) {
                System.out.println("Ваши данные не верны.Введите ваши данные еще раз");
                System.out.println("Введите логин:");
                log = scanner.next();
                System.out.println("Введите пароль:");
                pass = scanner.next();
            }
        } while (!check);
        return userInd;
    }
    public void registerUserAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите имя: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите Ваш возраст: ");
        int age = scanner.nextInt();
        System.out.print("Введите Ваш номер телефона: ");
        int phoneNumber = scanner.nextInt();
        System.out.print("Введите Ваш логин: ");
        scanner.nextLine();
        String userLogin = scanner.nextLine();
        System.out.print("Введите Ваш пароль: ");
        String userPassword = scanner.nextLine();
        System.out.print("Введите Вашу электронную почту: ");
        scanner.nextLine();
        String email = scanner.nextLine();
        boolean isBlocked = false;
        RegularUser user = new RegularUser(firstName, lastName, age, phoneNumber, userLogin,
                userPassword, email, isBlocked);
        users.add(user);
        new Serializator().saveList(users,"Users.bin");
    }
    public void blockUser() {
        int choice;
        printDetails();
        System.out.println("Выберите пользователя, которого хотите заблокировать:");
        choice = correctChoice();
        ((RegularUser) users.get(choice - 1)).setBlocked(true);
        new Serializator().saveList(users,"Users.bin");
    }
    public void unblockUser(){
        int choice;
        printDetails();
        System.out.println("Выберите пользователя, которого хотите разблокировать:");
        choice = Main.check(users);
        while (!((RegularUser)users.get(choice-1)).isBlocked() || users.get(choice-1) instanceof Admin){
            System.out.println("Пользователь не заблокирован");
            choice = correctChoice();
        }
        ((RegularUser) users.get(choice - 1)).setBlocked(false);
        new Serializator().saveList(users,"Users.bin");
    }

    public void deleteUser() {
        printDetails();
        System.out.println("Выберите пользователя, которого хотите удалить:");
        int choice = correctChoice();
        users.remove(choice - 1);
        new Serializator().saveList(users,"Users.bin");
    }

    private int correctChoice() {
        int choice = Main.check(users);
        while (users.get(choice - 1) instanceof Admin || ((RegularUser) users.get(choice - 1)).isBlocked()) {
            System.out.println("Некорректный выбор.Выберите еще раз");
            choice = Main.check(users);
        }
        return choice;
    }

    @Override
    public void printDetails() {
        System.out.println("Список пользователей:");
        users.forEach(User::printDetails);
    }



    public void viewFlowersArrayList() {
        if (flowerArrayList.isEmpty()) {
            System.out.println("Нет предметов.");
            return;
        }
        int a = 1;
        for (Flower pk : flowerArrayList) {
            System.out.println(a++ + ") " + pk.getName() + " " + pk.getColor() + " " + pk.getPrice() + " рублей, " + "количество: " + pk.getNumber());

            System.out.println();

        }
    }
}