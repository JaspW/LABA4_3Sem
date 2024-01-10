package CompositePackage;

import FlowerPackage.Flower;
import FlowerPackage.Operationable;
import FlowerPackage.Store;
import ObserverPackage.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegularUser extends User implements Observer {
    private boolean isSubscriber = false;
    private ArrayList<Flower> flowerCart = new ArrayList<>();

    public RegularUser(String firstName, String lastName, int age, int phoneNumber, String userLogin, String userPassword, String email, boolean isBlocked) {
        super(firstName, lastName, age, phoneNumber, userLogin, userPassword, email, isBlocked);
    }

    @Override
    public void printDetails() {
        System.out.println("Пользователь:" +
                "имя: '" + getFirstName() + '\'' +
                ", фамилия '" + getLastName() + '\'' +
                ", возраст: " + getAge() +
                ", номер телефона: " + getPhoneNumber() +
                ", логин: '" + getUserLogin() + '\'' +
                ", пароль: " + getUserPassword() + '\'' +
                ", почта: " + getEmail() + '\'' +
                ", статус блокировки: " + (isBlocked() ? "заблокирован" : "не заблокирован"));
    }

    public void subscribe(Store store) {
        this.isSubscriber = true;
        store.addObserver(this);
    }

    public void unsubscribe(Store store) {
        this.isSubscriber = false;
        store.removeObserver(this);
    }
    public void buyFlowers(List<Flower> flowerArrayList) {
        Operationable operationable = (x) -> {
            int sum = 0;
            sum += x;
            return sum;
        };
        Scanner scanner = new Scanner(System.in);
        boolean flagBuying = true;
        while (flagBuying) {
            int a = 1;
            for (Flower pk : flowerArrayList) {
                System.out.println(a++ + ") " + pk.getName() + " " + pk.getColor() + " " + pk.getPrice() + " рублей, " + "количество: " + pk.getNumber());

                System.out.println();

            }
            System.out.print("Введите номер элемента для выбора: ");
            int chooseFlower = scanner.nextInt();
            System.out.println("Введите количество цветов: ");
            int amountToAdd = scanner.nextInt();
            Flower flowerToModify = flowerArrayList.get(chooseFlower);
            int newNumber = flowerToModify.getNumber() - amountToAdd;
            if (chooseFlower >= 0 && chooseFlower < flowerArrayList.size()) {
                if (amountToAdd > 0) {
                    flowerToModify.setNumber(newNumber);
                    flowerCart.add(flowerToModify);
                }
                System.out.println(flowerToModify.getName() + " добавлена в корзину в количестве " + amountToAdd);
            } else {
                System.out.println("Недопустимый выбор. Попробуйте снова.");
            }
            int sum = 0;
            for (Flower i : flowerCart) {
                sum += operationable.operation(i.getPrice()) * amountToAdd;
            }
            for (Flower flower : flowerCart) {
                System.out.println(a++ + ") " + flower.getName() + " " + flower.getColor() + " " + flower.getPrice() + " рублей, " + "количество: " + amountToAdd);
            }
            System.out.println("Суммарная стоимость корзины: " + sum);
            System.out.println("Хотите продолжить?");
            System.out.println("1.Да, 2.Нет");
            int chooseBuying = scanner.nextInt();
            if (chooseBuying == 2) {
                flagBuying = false;
            }
        }
    }

    public void showCart(List<Flower> flowerCart) {
        int a = 0;
        if (flowerCart.isEmpty()){
            System.out.println("Лист пуст");
            return;
        }
        for (Flower flower : flowerCart) {
            System.out.println(a++ + ") " + flower.getName() + " " + flower.getColor() + " " + flower.getPrice() + " рублей, " + "количество: " + flower.getNumber());
        }
    }
    @Override
    public void update(String message) {
        System.out.println(message);
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }
}