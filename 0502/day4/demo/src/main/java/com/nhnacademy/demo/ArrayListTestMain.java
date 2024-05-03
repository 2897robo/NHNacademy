package com.nhnacademy.demo;

public class ArrayListTestMain {
    public static void main(String[] args) {
        ArrayListTest arrayListTest = new ArrayListTest();
        ArrayListTestProxy arrayListTestProxy1 = new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy1.test();
    }
}