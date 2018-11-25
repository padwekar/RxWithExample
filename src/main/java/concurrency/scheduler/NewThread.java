package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

public class NewThread {
    /*
       NewThread : Should be used when you want to create a new thread for a task , which will destroy itself when the task
       gets over. NewThread scheduler does not maintain it any separate thread pool.

      It returns a new thread whenever any observer is subscribed using Scheduler.newThread().
    */

    public static void main(String... args) {
        exNewThreadOne();
    }

    private static void exNewThreadOne() {
        /*
            NewThread : Assigns a new thread for each observable.
        */

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.newThread())
                .subscribe(System.out::println);


        Take.sleep(1000);
        /* OUTPUT :
                1
                2
                3
                4
                5
                6
         */
    }

    private static void exNewThreadTwo() {
        /*
            NewThread : When you want to run task sequentially on a single Thread.
        */


        /* OUTPUT :

         */
    }

}