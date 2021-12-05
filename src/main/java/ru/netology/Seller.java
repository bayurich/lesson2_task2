package ru.netology;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static ru.netology.Main.log;

public class Seller {

    static final int TIME_SELLER_PROCESSING = 2000;
    private final Shop shop;
    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public Car sellCar() {
        try{
            lock.lock();
            while (shop.getCars().isEmpty()) {
                log("Машин нет. Покупателю '" + Thread.currentThread().getName() + "' нужно подождать");
                condition.await();
            }
            log(Thread.currentThread().getName() + " получил сигнал о наличии машины");
            Thread.sleep(TIME_SELLER_PROCESSING);
        }
        catch (InterruptedException e){
            log("sellCar Error: " + e.getMessage());
        }
        finally {
            lock.unlock();
        }
        return shop.getCars().remove(0);
    }

    public void getCar() {
        try{
            lock.lock();
            Car car = new CarToyota();
            shop.getCars().add(car);
            log("Производитель '" + Thread.currentThread().getName() + "' выпустил 1 авто: " + car.getBrand());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
