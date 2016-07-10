package lesson;

public class IntroduceLambda_Thread {

    public static void main(String[] args) {

        Runnable a = ()->{
            for(;;) {
                sleep(100);
                System.out.println("a");
            }
        };

        new Thread(a).start();
    }

    private static
    void
    sleep(
        int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
