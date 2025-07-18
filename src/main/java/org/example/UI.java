package org.example;

import java.util.Scanner;

public class UI {
    private static UI instance;

    private UI() {}

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public static int promptAction(Player p) {
        System.out.println(p.getName() + ": What would you like to do?\n-1: fold\n0: check/call\nother value: raise");
        Scanner myScanner = new Scanner(System.in);

        return myScanner.nextInt();
    }

    public void print(String s) {
        // Log to the CLI
        System.out.println(s);
    }
}
