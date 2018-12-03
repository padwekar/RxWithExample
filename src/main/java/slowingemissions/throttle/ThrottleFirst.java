package slowingemissions.throttle;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class ThrottleFirst extends ThrottleParent{
    /*
       ThrottleFirst : Is almost identical to the throttleLast only difference is it will pick the
       first emission for every fixed time interval.
    */
    public static void main(String... args) {
        exThrottleFirstOne();
    }

    private static void exThrottleFirstOne() {
        /*
            ThrottleFirst : Effectively, the first emission found after each interval starts is the emission that gets pushed through.
        */

        Observable.concat(source1,source2,source3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(4000);

        /* OUTPUT :
                SOURCE 1: 100
                SOURCE 2: 300
                SOURCE 3: 2000
         */
    }

    private static void exThrottleFirstTwo() {
        /*
            ThrottleFirst :
        */


        /* OUTPUT :

         */
    }

}