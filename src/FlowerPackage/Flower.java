package FlowerPackage;

import java.io.Serializable;

public class Flower implements Serializable {
    private String name;
    private String color;
    private int number;

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    public Flower(String name, String color, int number, int price) {
        this.name = name;
        this.color = color;
        this.number = number;
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public String getColor(){
        return color;
    }
    public int getNumber() {
        return number;
    }
    public int getPrice() {
        return price;
    }
}
