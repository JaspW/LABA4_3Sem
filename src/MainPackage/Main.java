package MainPackage;

import CompositePackage.Admin;
import CompositePackage.RegularUser;
import CompositePackage.UsersList;
import FlowerPackage.Flower;
import FlowerPackage.Store;
import SortTaskPackage.AscendingSortTask;
import SortTaskPackage.DescendingSortTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main extends Thread {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsersList usersList = new UsersList();
        Store store = Store.getStore();

        int minNumber = 0;
        int maxNumber = 100;
        int minPrice = 0;
        int maxPrice = 1000;

        ArrayList<Flower> flowerCart = new ArrayList<>();

        boolean mainFlag = true;

        while (mainFlag) {
            System.out.println("Приветствую!");
            System.out.println("Регистрация - 1");
            System.out.println("Авторизация - 2");
            System.out.println("Выход - 0");
            boolean flag = true;
            int chooseFunctional = scanner.nextInt();
            switch (chooseFunctional) {
                case 1 -> usersList.registerUserAccount();
                case 2 -> {
                    int userInd = usersList.authorization();

                        if (usersList.getUsers().get(userInd) instanceof RegularUser &&
                                !usersList.getUsers().get(userInd).isBlocked()) {
                            if(((RegularUser) usersList.getUsers().get(userInd)).isSubscriber())
                                ((RegularUser) usersList.getUsers().get(userInd)).update("Произошло изменение в списке товаров");
                            while (flag) {
                                System.out.println("Просмотреть объекты в магазине - 1");
                                System.out.println("Фильтр по количеству - 2");
                                System.out.println("Фильтр по цене - 3");
                                System.out.println("Посмотреть отфильтрованные - 4");
                                System.out.println("Убрать фильтр по объему - 5");
                                System.out.println("Убрать фильтр по цене - 6");
                                System.out.println("Купить - 7");
                                System.out.println("Сортировка по убыванию количества цветов - 8");
                                System.out.println("Сортировка по возрастанию количества цветов - 9");
                                System.out.println("Просмотреть корзину - 10");
                                System.out.println("Выход  - 0");
                                chooseFunctional = checkInput();
                                switch (chooseFunctional) {
                                    case 1 -> {
                                        store.viewOriginalFlowerArrayList();
                                        System.out.println();
                                    }
                                    case 2 -> {
                                        // Фильтрация по количеству цветов
                                        System.out.print("Минимальное количество цветов: ");
                                        minNumber = scanner.nextInt();
                                        System.out.print("Максимальное количество цветов: ");
                                        maxNumber = scanner.nextInt();

                                        store.filterByNumber(minNumber, maxNumber);

                                        // Выводим отфильтрованные предметы
                                        store.viewFlowersArrayList();
                                    }

                                    case 3 -> {
                                        // Фильтрация по цене цветка
                                        System.out.print("Минимальная цена цветка: ");
                                        minPrice = scanner.nextInt();
                                        System.out.print("Максимальная цена цветка: ");
                                        maxPrice = scanner.nextInt();

                                        store.filterByPrice(minPrice, maxPrice);

                                        // Выводим отфильтрованные предметы
                                        store.viewFlowersArrayList();
                                    }

                                    case 4 -> store.viewFlowersArrayList();

                                    case 5 -> store.clearNumberFilter(minPrice, maxPrice);

                                    case 6 -> store.clearPriceFilter(minNumber, maxNumber);

                                    case 7 -> store.buyFlowers((RegularUser) usersList.getUsers().get(userInd));

                                    case 8 -> {
                                        DescendingSortTask descendingSortTask = new DescendingSortTask(store);
                                        descendingSortTask.start();
                                    }

                                    case 9 -> {
                                        AscendingSortTask ascendingSortTask = new AscendingSortTask(store);
                                        Thread ascendingSortThread = new Thread(ascendingSortTask);
                                        ascendingSortThread.start();
                                    }

                                    case 10 -> store.showCart(flowerCart);

                                    case 0 -> flag = false;
                                }
                            }
                        } else if (usersList.getUsers().get(userInd) instanceof Admin) {
                            while (flag) {
                                System.out.println("Просмотреть объекты в магазине - 1");
                                System.out.println("Добавить цветок - 2");
                                System.out.println("Удалить цветок - 3");
                                System.out.println("Изменить цветок - 4");
                                System.out.println("Просмотреть пользователей - 5");
                                System.out.println("Заблокировать пользователя - 6");
                                System.out.println("Разблокировать пользователя - 7");
                                System.out.println("Выход - 0");
                                chooseFunctional = checkInput();
                                switch (chooseFunctional) {
                                    case 1 -> {
                                        store.viewOriginalFlowerArrayList();
                                        System.out.println();
                                    }
                                    case 2 -> store.addFlower();
                                    case 3 -> store.removeFlower();
                                    case 4 -> store.editFlower();
                                    case 5 -> usersList.printDetails();
                                    case 6 -> usersList.blockUser();
                                    case 7 -> usersList.unblockUser();
                                    case 0 -> flag = false;
                                }
                            }
                        }
                        else if(usersList.getUsers().get(userInd).isBlocked()){
                            System.out.println("Вы были заблокированы");
                        }
                }
                case 0 -> mainFlag = false;
            }
        }
    }
    public static int checkInput() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод.Введите еще раз!");
            }
        }
        return number;
    }

    public static int check (List<?> list){
        int a;
        a = checkInput();
        while (a > list.size() || a <= 0) {
            System.out.println("Выход за границы списка.Введите еще раз!");
            a = checkInput();
        }
        return a;
    }
}
/*
Сохранение корзины для каждого пользователя
*/