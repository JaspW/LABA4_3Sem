package SortTaskPackage;

import FlowerPackage.Flower;
import FlowerPackage.Store;

import java.util.Comparator;

public class AscendingSortTask implements Runnable {
    private final Store store;

    public AscendingSortTask(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.getFlowerArrayList().sort(Comparator.comparingDouble(Flower::getNumber)); // Сортировка по возрастанию количества цветов
        store.viewFlowersArrayList();
    }
}