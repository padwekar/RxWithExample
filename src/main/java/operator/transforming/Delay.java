package operator.transforming;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class Delay {

    /*
       Delay : it is used to delay the emission to the specified amount of time.
       It will hold the result and delay it for the specified amount of time.
    */

    public static void main(String... args) {
        exDelayOne();
    }

    private static void exDelayOne() {
        /*
            Here if you want to delay the emission to 2 seconds.
        */

        Observable.fromIterable(SwitchIfEmpty.activeBugs()).delay(4, TimeUnit.SECONDS)
                .subscribe(bug -> System.out.println(bug.title));

        Take.sleep(5000);

        /*
        Because delay operates on a different scheduler we need to call sleep on the current Thread.
        This will prevent current thread from terminating.
         */
    }

    private static void exDelayTwo() {
        /*
            Here Delay
        */
    }

}