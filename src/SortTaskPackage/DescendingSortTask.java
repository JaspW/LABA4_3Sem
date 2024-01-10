package SortTaskPackage;

import FlowerPackage.Store;

public class DescendingSortTask extends Thread {
    private final Store store;

    public DescendingSortTask(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.getFlowerArrayList().sort((s1, s2) -> Double.compare(s2.getNumber(), s1.getNumber())); // Сортировка по убыванию количества
        store.viewFlowersArrayList();
    }
}