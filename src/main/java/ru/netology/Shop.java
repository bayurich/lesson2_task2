package ru.netology;

import java.util.ArrayList;
import java.util.List;

import static ru.netology.Main.log;

public class Shop {
    Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>(10);


    public Car buyCar(){
        log("Покупатель '" + Thread.currentThread().getName() + "' зашел в автосалон");
        Car car = sellCar();
        log("Покупатель '" + Thread.currentThread().getName() + "' уехал на новеньком авто ");

        return car;
    }

    public Car sellCar(){
        return seller.sellCar();
    }

    public void makeCar(){
        seller.getCar();
    }

    public List<Car> getCars() {
        return cars;
    }
}
