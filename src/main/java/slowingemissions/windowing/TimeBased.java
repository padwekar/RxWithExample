package slowingemissions.windowing;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class TimeBased {
    /*
       TimeBased : You can cut off windowed observable based on time interval.
       Here we have observable emitting every 300ms and we will be slicing the window at every
       1 seconds.
    */
    public static void main(String... args) {
        exTimeBasedOne();
    }

    private static void exTimeBasedOne() {
        /*
            TimeBased :
        */

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(res -> ((res + 1) * 300))
                .window(1,TimeUnit.SECONDS)
                .flatMapSingle(ob -> ob.reduce("",(total,next) -> total + ("|") + next))
                .subscribe(System.out::println);


        Take.sleep(5000);

        /* OUTPUT :
            |300|600|900
            |1200|1500|1800
            |2100|2400|2700
            |3000|3300|3600|3900
            |4200|4500|4800
         */
    }


}