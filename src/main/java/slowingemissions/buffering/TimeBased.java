package slowingemissions.buffering;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class TimeBased {
    /*
       TimeBased : Allows us to buffer the emission for every specified time interval.
    */
    public static void main(String... args) {
        exTimeBasedBufferingTwo();
    }

    private static void exTimeBasedBufferingOne() {
        /*
            TimeBased : If you use the Long and TimeUnit Argument Buffer overload that will buffer
            every time the time interval happens.
        */

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(result -> (result+1) * 300)
                .buffer(1,TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(4000);
        /* OUTPUT :
            [300, 600, 900]
            [1200, 1500, 1800]
            [2100, 2400, 2700]
            [3000, 3300, 3600, 3900]
         */
    }

    //BufferCount
    private static void exTimeBasedBufferingTwo() {
        /*
            TimeBased : it also accepts buffercount parameter which says, Batch the elements
            if the buffercount has reached or the time interval has passed. Whichever happens before.

        */


        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(result -> (result+1) * 300)
                .buffer(1,TimeUnit.SECONDS,2)
                .subscribe(System.out::println);

        Take.sleep(4000);


        /* OUTPUT :
            [300, 600]
            [900]
            [1200, 1500]
            [1800]
            [2100, 2400]
            [2700]
            [3000, 3300]
            [3600, 3900]
            []
         */
    }

}