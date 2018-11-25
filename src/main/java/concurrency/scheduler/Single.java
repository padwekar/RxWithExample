package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class Single {
    /*
       Single : When you want to perform sequential operation in a single Thread use single thread use Scheduler.single()
    */
    public static void main(String... args) {
        exSingleOne();
    }

    private static void exSingleOne() {
        /*
            Single : Backed by single Threaded implementation appropriate for event looping.
            It will return the same thread every-time you call Schedulers.single() .
        */


        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.single())
                .subscribe(result -> System.out.println("First Observer "+result));

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.single())
                .subscribe(result -> System.out.println("Second Observer "+result));

        Take.sleep(5000);

        /* OUTPUT :
        Second Observer 0
        First Observer 0
        Second Observer 1
        First Observer 1
        First Observer 2
        Second Observer 2
        First Observer 3
        Second Observer 3
        Second Observer 4
        First Observer 4
         */
    }
}