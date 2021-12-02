package com.VendingMachine.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserIOConsoleImpl implements UserIO {
    Scanner sc = new Scanner(System.in);


    public void print(String msg) {
        System.out.println(msg);
    }


    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }


    public double readDouble(String prompt) {
        System.out.println(prompt);
        double returnValue =  sc.nextDouble();
        sc.nextLine();
        return returnValue;
    }


    public double readDouble(String prompt, double min, double max) {
        do {
            System.out.println(prompt);
            double returnValue = sc.nextDouble();
            sc.nextLine();
            if(returnValue >= min && returnValue <= max) {
                return returnValue;
            }
        } while(true);
    }


    public float readFloat(String prompt) {
        System.out.println(prompt);
        float returnValue =  sc.nextFloat();
        sc.nextLine();
        return returnValue;
    }


    public float readFloat(String prompt, float min, float max) {
        do {
            System.out.println(prompt);
            float returnValue = sc.nextFloat();
            sc.nextLine();
            if(returnValue >= min && returnValue <= max) {
                return returnValue;
            }
        } while(true);
    }


    public int readInt(String prompt) {
        System.out.println(prompt);
        int returnValue =  sc.nextInt();
        sc.nextLine();
        return returnValue;
    }


    public int readInt(String prompt, int min, int max) {
        String input = readString(prompt);
        return Integer.parseInt(input);
    }

    public long readLong(String prompt) {
        System.out.println(prompt);
        long returnValue =  sc.nextLong();
        sc.nextLine();
        return returnValue;
    }


    public long readLong(String prompt, long min, long max) {
        do {
            System.out.println(prompt);
            long returnValue = sc.nextLong();
            sc.nextLine();
            if(returnValue >= min && returnValue <= max) {
                return returnValue;
            }
        } while(true);
    }




}
