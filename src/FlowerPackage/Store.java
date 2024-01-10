package FlowerPackage;

import CompositePackage.RegularUser;
import MainPackage.Main;
import ObserverPackage.Observable;
import ObserverPackage.Observer;
import SerializatorPackage.Serializator;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Store implements Observable {
    private String name;
    private static Store instance;
    Scanner scanner = new Scanner(System.in);

    Operationable operationable = (x) -> {
        int sum = 0;
        sum += x;
        return sum;
    };
    private ArrayList<Flower> flowerArrayList;
    private List<Observer> observers = new ArrayList<>();
    private final ArrayList<Flower> originalFlowersArrayList;
    private final ArrayList<Flower> flowerArrayListPrice;
    private final ArrayList<Flower> flowerArrayListNumber;
    private final ArrayList<Flower> flowerCart;
    private boolean isChanged = false;

    private Store(String name) {
        this.name = name;
        this.flowerArrayList = (ArrayList<Flower>) Serializator.readList("Flowers.bin");
        this.originalFlowersArrayList = new ArrayList<>(flowerArrayList);
        this.flowerArrayListPrice = new ArrayList<>();
        this.flowerArrayListNumber = new ArrayList<>();
        this.flowerCart = new ArrayList<>();
    }
    public static Store getStore(){
        if(instance == null){
            instance = new Store("FlowerShop");
        }
        return instance;
    }
    public void addFlower() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название цветка: ");
        String name = scanner.nextLine();
        System.out.print("Введите цвет: ");
        String color = scanner.nextLine();
        System.out.print("Введите количество: ");
        int number = scanner.nextInt();
        System.out.print("Введите цену: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        // создаем объект класса Subject
        Flower flower = new Flower(name, color, number, price);
        // добавляем объект в массив
        flowerArrayList.add(flower);
        originalFlowersArrayList.add(flower);
        viewFlowersArrayList();
        isChanged = true;
        new Serializator().saveList(originalFlowersArrayList,"Flowers.bin");
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

    public void viewOriginalFlowerArrayList() {
        if (originalFlowersArrayList.isEmpty()) {
            System.out.println("Нет предметов.");
            return;
        }
        int a = 1;
        for (Flower pk : originalFlowersArrayList) {
            System.out.println(a++ + ") " + pk.getName() + " " + pk.getColor() + " " + pk.getPrice() + " рублей, " + "количество: " + pk.getNumber());

            System.out.println();

        }
    }

    public void filterByNumber(int minNumber, int maxNumber) {
        // Фильтр по количеству
        flowerArrayList = flowerArrayList.stream()
                .filter(flower -> flower.getNumber() >= minNumber && flower.getNumber() <= maxNumber)
                .collect(Collectors.toCollection(ArrayList::new));
        flowerArrayListNumber.clear();
        flowerArrayListNumber.addAll(flowerArrayList);

    }

    public void filterByPrice(int minPrice, int maxPrice) {
        // Фильтр по цене
        flowerArrayList = flowerArrayList.stream()
                .filter(flower -> flower.getPrice() >= minPrice && flower.getPrice() <= maxPrice)
                .collect(Collectors.toCollection(ArrayList::new));
        flowerArrayListPrice.clear();
        flowerArrayListPrice.addAll(flowerArrayList);
    }

    public void clearNumberFilter(int minPrice, int maxPrice) {
        if (flowerArrayList.containsAll(flowerArrayListNumber)) {
            flowerArrayList.clear();
            flowerArrayList.addAll(originalFlowersArrayList);
        } else {
            flowerArrayList.clear();
            flowerArrayList = originalFlowersArrayList.stream().filter(flower -> flower.getPrice() >= minPrice && flower.getPrice() <= maxPrice)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        viewFlowersArrayList();
    }

    public void clearPriceFilter(int minNumber, int maxNumber) {
        if (flowerArrayList.containsAll(flowerArrayListPrice)) {
            flowerArrayList.clear();
            flowerArrayList.addAll(originalFlowersArrayList);
        } else {
            flowerArrayList.clear();
            flowerArrayList = originalFlowersArrayList.stream().filter(flower -> flower.getNumber() >= minNumber && flower.getNumber() <= maxNumber)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        viewFlowersArrayList();
    }
    public void editFlower(){
        viewFlowersArrayList();
        System.out.println("Выберите цветок, который хотите изменить:");
        int choice = Main.check(flowerArrayList);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название цветка: ");
        String name = scanner.nextLine();
        System.out.print("Введите цвет: ");
        String color = scanner.nextLine();
        System.out.print("Введите количество: ");
        int number = scanner.nextInt();
        System.out.print("Введите цену: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        flowerArrayList.get(choice-1).setName(name);
        originalFlowersArrayList.get(choice-1).setName(name);
        flowerArrayList.get(choice-1).setColor(color);
        originalFlowersArrayList.get(choice-1).setColor(color);
        flowerArrayList.get(choice-1).setNumber(number);
        originalFlowersArrayList.get(choice-1).setNumber(number);
        flowerArrayList.get(choice-1).setPrice(price);
        originalFlowersArrayList.get(choice-1).setPrice(price);
        viewFlowersArrayList();
        isChanged = true;
        new Serializator().saveList(originalFlowersArrayList,"Flowers.bin");
    }
    public void removeFlower(){
        viewFlowersArrayList();
        System.out.println("Выберите цветок, который хотите удалить:");
        int choice = Main.check(flowerArrayList);
        flowerArrayList.remove(choice-1);
        originalFlowersArrayList.remove(choice-1);
        isChanged = true;
        new Serializator().saveList(originalFlowersArrayList,"Flowers.bin");
    }
    public void buyFlowers(RegularUser user) {
        user.buyFlowers(flowerArrayList);
    }

    public void showCart(List<Flower> flowerCart) {
        int a = 0;
        if (flowerCart.isEmpty()){
            System.out.println("Лист пуст");
            return;
        }
        for (Flower flower : flowerCart) {
            System.out.println(a++ + ") " + flower.getName() + " " + flower.getColor() + " " + flower.getPrice()
                    + " рублей, " + "количество: " + flower.getNumber());
        }
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return flowerArrayList;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(String message) {
        for(Observer o:observers){
            o.update(message);
        }
    }
}