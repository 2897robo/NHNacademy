package com.nhnacademy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Store {
    //매장에는 최대 10개의 물건만 전시할 수 있다.
    //품목마다 최대 갯수가 지정되어 있다.
    private final int MAX_ITEMS = 10;
    //폼목별로 세마포어를 이용해 관리한다.
    private int items = 0;
    private Semaphore itemsSemaphore = new Semaphore(1);
    //매장은 최대 5명까지만 동시 입장 가능하다.
    //private final int MAX_CUSTOMERS = 5;
    private int customers = 0;

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public synchronized void enter() throws InterruptedException {
        //while(customers == MAX_CUSTOMERS) {
        //    wait();
        //}
        customers++;
        logger.info("고객 입장, 현재 고객 수: " + customers);
    }

    public synchronized void exit() {
        customers--;
        logger.info("고객 퇴장, 현재 고객 수: " + customers);
        //notifyAll();
    }

    //매장에서 물건 구매는 동시에 1명만 가능하다.
    public synchronized void buy() throws InterruptedException {
        if(items <= 0) {
            logger.warn("물건이 없어 구매를 기다립니다.");
            if(!itemsSemaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                logger.warn("물건 수량 부족으로 인한 구매 포기");
                return;
            }
        } else {
            itemsSemaphore.acquire();
            items--;
            logger.info("물건 구매, 남은 물건 수: " + items);
        }
        /*
        while(items <= 0) {
            wait();     
        }
        items--;
        logger.info("물건 구매, 남은 물건 수: " + items);
        notifyAll();    //매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.
         */
    }

    //매장에서 물건 납품은 동시에 1명만 가능하다.
    public synchronized void sell() throws InterruptedException {
        if(items >= MAX_ITEMS) {
            logger.warn("물건이 가득 차 납품을 기다립니다.");
            if(!itemsSemaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                logger.warn("물건 수량 최대로 인한 납품 포기");
                return;
            }
        } else {
            itemsSemaphore.release();
            items++;
            logger.info("물건 납품, 남은 물건 수: " + items);
        }
        /*
        while(items == MAX_ITEMS) {
            wait();
        }
        items++;        //매장은 물건을 납품 받아서 판매한다.
        logger.info("물건 납품, 남은 물건 수: " + items);
        notifyAll();    //매장에서 물건이 들어오면 소비자에게 알려 준다.
         */
        
    }
}