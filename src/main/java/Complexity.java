public class Complexity {

    private static long counter, startTime;

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static long check() {
        return System.currentTimeMillis() - startTime;
    }

    public static long stop() {
        long endTime = System.currentTimeMillis() - startTime;
        startTime = 0;
        return endTime;
    }

    public static void count() {
        counter++;
    }

    public static void count(int times) {
        counter += times;
    }

    public static long getCounter() {
        return counter;
    }

    public static void resetCounter(){
        counter = 0;
    }

    public static void print (String description){

        System.out.printf("--- %s ---\n", description);
        System.out.printf("Czas trwania operacji %d [ms]\n", stop());
        System.out.printf("Ilość wykonanych operacji: %d\n", getCounter());
        resetCounter();

    }
}
