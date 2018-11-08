package operator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Future {
    /**
     * A task that sleeps for a second, then returns 1
     *
     * They are objects that promise to hold the result of an operation once that operation completes.
     **/
    public static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return 1;
        }

    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newSingleThreadExecutor();
        java.util.concurrent.Future<Integer> f = exec.submit(new MyCallable());

        System.out.println(f.isDone()); //False

        System.out.println(f.get()); //Waits until the task is done, then prints 1

        System.out.println(f.isDone()); //True

    }
}
