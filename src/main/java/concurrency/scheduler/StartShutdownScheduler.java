package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class StartShutdownScheduler {

    /*
       StartShutdownScheduler : Each scheduler is lazily initialised when you invoke its usage.
                                You can call dispose on computation,io,newThread,single & Trampoline any time by
                                calling shutdown on it. Ref example 1.

                                This will stop all their threads and task which were executing.

                                And it throw error if you again try to use the scheduler unless you call Schedulers.start()
                                to reinitialize is. Lets see in example one.
    */

    public static void main(String... args) {
        exStartShutdownSchedulerOne();
    }

    private static void exStartShutdownSchedulerOne() {
        /*
            StartShutdownScheduler : Shutting down a scheduler.
        */

        Scheduler singleScheduler = Schedulers.single();

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(singleScheduler)
                .subscribe(res -> System.out.println("First Observer "+ res));

        singleScheduler.shutdown();


        // You will get rejected execution exception if you try to use a scheduler which is shutdown already
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(singleScheduler)
                .subscribe(res -> System.out.println("Second Observer "+ res),Throwable::printStackTrace);

        Take.sleep(10000);

        /* OUTPUT :
            Caused by: java.util.concurrent.RejectedExecutionException:
            Task java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask@167fdd33
         */
    }

    // Reusing a disposed scheduler
    private static void exStartShutdownSchedulerTwo() {
        /*
            If you try to use a scheduler which was disposed you will immediately get an RejectedExecutionException.
            Inorder to properly reuse it call Scheduler.start() to reinitialise it.
        */


        Scheduler singleScheduler = Schedulers.single();

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(singleScheduler)
                .subscribe(res -> System.out.println("First Observer "+ res));

        singleScheduler.shutdown();


        // You will get rejected execution exception if you try to use a scheduler which is shutdown already
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(singleScheduler)
                .subscribe(res -> System.out.println("Second Observer "+ res),Throwable::printStackTrace);

        Take.sleep(10000);

        /* OUTPUT :
            Caused by: java.util.concurrent.RejectedExecutionException:
            Task java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask@167fdd33
         */
    }

}