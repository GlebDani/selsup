package org.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)  {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        CrptApi api = new CrptApi(TimeUnit.MINUTES,10);
        CrptApi.Document doc = new CrptApi.Document();
        api.getConnection(doc);
//        api.getConnection();
//        api.getConnection();
//        api.getConnection();api.getConnection();
//        api.getConnection();
//        api.getConnection();
//        api.getConnection();
//        api.getConnection();
//        api.getConnection();
//        api.getConnection();
////        Thread.sleep(1000*60);
//        api.getConnection();
//        api.getConnection();
//
//        Thread thread1= new Thread(() -> {
//            api.getConnection();
//            System.out.println("1.1");
//            api.getConnection();
//            System.out.println("1.2");
//            api.getConnection();
//            System.out.println("1.1");
//        });
//        thread1.start();



    }
}