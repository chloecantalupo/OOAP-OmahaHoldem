package org.example;

import java.util.Scanner;

public class UI {
    public static int promptAction(Player p) {
        System.out.println(p.getName() + ": What would you like to do?\n-1: fold\n0: check/call\nother value: raise");
        Scanner myScanner = new Scanner(System.in);

        return myScanner.nextInt();
    }
}
