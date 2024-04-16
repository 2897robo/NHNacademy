package com.nhnacademy.app;

import java.util.Random;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomExample {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;  // 1부터 100까지의 난수 생성
        System.out.println("Random number (java.util.Random): " + randomNumber);

        RandomDataGenerator randomData = new RandomDataGenerator();
        int randomNumber2 = randomData.nextInt(1, 100);  // 1부터 100까지의 난수 생성
        System.out.println("Random number (Apache Commons Math): " + randomNumber2);
    }
}