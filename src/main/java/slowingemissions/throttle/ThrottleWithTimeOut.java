package slowingemissions.throttle;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class ThrottleWithTimeOut extends ThrottleParent{
    /*
       ThrottleWithTimeOut : Doesn't emit whenever emissions are going on wait for it to silence for specific
       period and emits.
    */

    public static void main(String... args) {
        exThrottleWithTimeOutOne();
    }

    private static void exThrottleWithTimeOutOne() {
        /*
             ThrottleWithTimeOut : The 900 emission from source2 was the last emission as soon as source3 started,
             since source3 will not push its first emission for 2 seconds,
             which gave more than the needed 1- second period of silence for the 900 emission to be fired.
             The 2000 emission then emitted next and 1 second later no further emissions occurred, so it was pushed
             forward by throttleWithTimeout().Another second later, the 4000 emission was pushed and the 2- second
             silence (before the program exited) allowed it to fire as well.
        */

        Observable.concat(source1,source2,source3)
                .throttleWithTimeout(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(6000);

        /* OUTPUT :
            SOURCE 2: 900
            SOURCE 3: 2000
            SOURCE 3: 4000
         */
    }



}