package concurrency;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class KeepingAppAlive {
    /*
       KeepingAppAlive : If you are not dealing with the framework which has their own daemon thread which keeps
       the program alive such as Android,JavaFx etc. Check the following examples to achieve the same.
    */
    public static void main(String... args) {
        exKeepingAppAliveTwo();
    }

    private static void exKeepingAppAliveOne() {
        /*
            KeepingAppAlive : The first way. Calling Thread.sleep(Long.MAX_VALUE)
        */

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(Long.MAX_VALUE); //Calls Thread.sleep(long milli)

        /* OUTPUT :

         */
    }

    private static void exKeepingAppAliveTwo() {
        /*
            KeepingAppAlive : Use blockingSubscribe in place of subscribe to keep the application alive till
            the onComplete is called.
        */

        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .blockingSubscribe(System.out::println);



        /* OUTPUT :
            0
            1
            2
         */
    }

}