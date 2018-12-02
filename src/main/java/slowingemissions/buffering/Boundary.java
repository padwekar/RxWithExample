package slowingemissions.buffering;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class Boundary {

    /*
       Boundary : Most powerful variance of Buffer is the ability to take another observable's emission
       as buffer time slice. Arbitrary occurrence of emission will decide when to slice the buffer.
    */

    public static void main(String... args) {
        exBoundaryBasedOne();
    }

    private static void exBoundaryBasedOne() {
        /*
            Boundary : Here we can have one observable which will decide the time to slice the buffer for another observable.
            1st Observable will emit every 1 second. 2nd observer will emit every 300ms. We need to slice the emission of 2nd
            observable based every time the 1st observable emits.
        */

       Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS);

       Observable.interval(300,TimeUnit.MILLISECONDS)
               .map(i -> (i + 1) * 300)
               .buffer(longObservable)
               .subscribe(System.out::println);

        Take.sleep(3000);


        /* OUTPUT :
            [300, 600, 900]
            [1200, 1500, 1800]
            [2100, 2400, 2700]
         */
    }



}