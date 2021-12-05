package ru.netology;

public class BuyerThread extends Thread{

    static final int TIME_BUYERS_GAP = 2000;
    static final int COUNT_BUYERS = 10;

    Shop shop;

    public BuyerThread(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i=1; i<= COUNT_BUYERS; i++){
            new Thread(null, shop::buyCar, "Покупатель " + i).start();
            try {
                Thread.sleep(TIME_BUYERS_GAP);
            } catch (InterruptedException e) {
                System.out.println("Error while buying car: " + e.getMessage());
            }
        }
    }
}
