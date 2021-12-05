package ru.netology;

import static ru.netology.Main.log;

public class Seller {

    static final int TIME_SELLER_PROCESSING = 2000;
    private Shop shop;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized Car sellCar() {
        try{
            while (shop.getCars().isEmpty()) {
                log("Машин нет. Покупателю '" + Thread.currentThread().getName() + "' нужно подождать");
                wait();
            }
            log(Thread.currentThread().getName() + " получил сигнал о наличии машины");
            Thread.sleep(TIME_SELLER_PROCESSING);
        }
        catch (InterruptedException e){
            log("sellCar Error: " + e.getMessage());
        }
        return shop.getCars().remove(0);
    }

    public synchronized void getCar() {
        Car car = new CarToyota();
        shop.getCars().add(car);
        log("Производитель '" + Thread.currentThread().getName() + "' выпустил 1 авто: " + car.getBrand());
        notifyAll();
    }
}
