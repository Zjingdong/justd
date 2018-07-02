package org.thread;

public class SynTest extends Thread {
    private  int num=0;
    public   void run(){
        for (int i=0;i<50;i++){
            num=num+1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"----------"+num);
        }

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        SynTest synTest1 = new SynTest();
        Thread t1 = new Thread(synTest1);
        Thread t2 = new Thread(synTest1);
        t1.start();
        t2.start();
        System.out.println(synTest1.getNum());
    }
}
