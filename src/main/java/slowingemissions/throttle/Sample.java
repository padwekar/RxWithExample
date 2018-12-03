package slowingemissions.throttle;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

//ALIAS : throttleLast()
public class Sample extends ThrottleParent {



    public static void main(String... args) { exSimpleTwo(); }

    private static void exSimpleOne() {
        /*
            Sample/throttle last: Will only emit the last emission in a fixed time interval from the provided source.
            If you study the output, you can see that the last emission at every 1-second interval was all that got through.
        */

        Observable.concat(source1,source2,source3)
                .throttleLast(1,TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(4000);


        /* OUTPUT :
            SOURCE 1: 900
            SOURCE 2: 900
            SOURCE 3: 2000
         */
    }

    //Throttling at large interval time
    private static void exSimpleTwo() {
        /*
            Sample : Here it will throttle emission every 2 seconds
        */

        Observable.concat(source1,source2,source3)
                .throttleLast(2,TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Take.sleep(4000);

        /* OUTPUT :
            SOURCE 2: 900
            SOURCE 3: 2000
         */
    }

}