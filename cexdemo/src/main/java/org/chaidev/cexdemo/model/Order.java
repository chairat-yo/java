package org.chaidev.cexdemo.model;

public class Order {
    private double price;
    private double amount;
    public Order(double price, double amount) {
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return price + ":" + amount;
    }
}
