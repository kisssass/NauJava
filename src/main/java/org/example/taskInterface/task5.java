package org.example.taskInterface;

public class task5 implements Task{
    public static void main(String[] args) {
        task5 timer = new task5(15);
        timer.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
    }

    private int countdownTime;
    private volatile boolean running;

    public task5(int seconds) {
        this.countdownTime = seconds;
    }

    @Override
    public void start() {
        running = true;
        new Thread(() -> {
            while (countdownTime > 0 && running) {
                System.out.println("left: " + countdownTime + " seconds");
                countdownTime--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("timer interrupted");
                }
            }

            if (countdownTime <= 0) {
                System.out.println("time is up!");
            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("timer stopped.");
    }
}
