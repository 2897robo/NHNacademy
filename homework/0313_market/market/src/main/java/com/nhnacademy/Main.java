package com.nhnacademy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.print("생성할 매장 수 : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        
        // N개의 Store 객체 생성 및 초기화
        Store[] store = new Store[n];
        for(int i = 0; i < n; i++) {
            store[i] = new Store(); // Store 인스턴스 생성
        }
        
        // Producer 객체 생성 및 쓰레드 시작
        ExecutorService producersPool = Executors.newFixedThreadPool(n);
        for(int i = 0; i < n; i++) {
            producersPool.submit(new Producer(store[i]));
        }
        
        // Consumer 객체 생성 및 쓰레드 시작
        ExecutorService consumersPool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 100; i++) {
            int storeIndex = i % n; // 매장 배열의 길이를 고려하여 인덱스 접근
            consumersPool.submit(new Consumer(store[storeIndex]));
        }
        
        // 모든 작업 완료 후 ExecutorService 리소스 해제
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}
