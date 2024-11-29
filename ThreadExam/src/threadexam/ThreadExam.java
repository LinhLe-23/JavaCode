package threadexam;

public class ThreadExam {

    public static void main(String[] args) throws InterruptedException {

        myThread thread1 = new myThread("Thread1");
        myThread thread2 = new myThread("Thread2");

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        try {

            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

    public static class myThread extends Thread {

        public myThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println(getName() + ": " + i);
            }
        }
    }
}
