package org.encrypt;

public class Test {

    private static class Counter {

        public synchronized void count() {
            for (int i = 0; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "， i = " + i);
            }
        }

    }

    private static class MyThread extends Thread {

        private Counter mCounter;

        public MyThread(Counter counter) {
            mCounter = counter;
        }

        @Override
        public void run() {
            super.run();
            mCounter.count();
        }
    }

    public static void main(String[] var0) {

        int a = 10;
        int b = ~a;
        System.out.println(b);
        //        Counter counter = new Counter();
//        // 注：myThread1 和 myThread2 是调用同一个对象 counter
//        MyThread myThread1 = new MyThread(counter);
//        MyThread myThread2 = new MyThread(counter);
//        myThread1.start();
//        myThread2.start();
    }

}
