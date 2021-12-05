package ru.netology;

public class MakerThread extends Thread {

    static final int TIME_MAKERS_GAP = 3000;
    static final int COUNT_MAKERS = 10;

    Shop shop;

    public MakerThread(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i=1; i<= COUNT_MAKERS; i++){
            try {
                Thread.sleep(TIME_MAKERS_GAP);
                new Thread(null, shop::makeCar, "Производитель " + CarBrands.Toyota.name()).start();
            } catch (InterruptedException e) {
                System.out.println("Error while making car: " + e.getMessage());
            }
        }
    }
}
