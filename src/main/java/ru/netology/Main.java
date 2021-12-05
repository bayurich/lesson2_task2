package ru.netology;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static final Shop shop = new Shop();
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    public static void main(String[] args) {

        new BuyerThread(shop).start();
        new MakerThread(shop).start();
    }

    public static void log(String mes){
        System.out.println(simpleDateFormat.format(new Date()) + mes);
    }
}
