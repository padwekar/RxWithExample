package concurrency.scheduler;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaExecutorService {

    /*
       JavaExecutorService : Is used if you want to build your own scheduler off the Java's executor Service.
    */

    public static void main(String... args) {
        exExecutorServiceFive();
    }

    private static void exExecutorServiceOne() {

        //Custom scheduler with 1 threads
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Scheduler executorScheduler = Schedulers.from(executorService);

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(11,12,13,14,15,16)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(21,22,23,24,25,26)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();


        /*
            The output is predictable as we are using only one thread.
            So all the three operation will take place in a single thread sequentially
        */

        /* OUTPUT :
        pool-1-thread-1 : 1
        pool-1-thread-1 : 2
        pool-1-thread-1 : 3
        pool-1-thread-1 : 4
        pool-1-thread-1 : 5
        pool-1-thread-1 : 6
        pool-1-thread-1 : 11
        pool-1-thread-1 : 12
        pool-1-thread-1 : 13
        pool-1-thread-1 : 14
        pool-1-thread-1 : 15
        pool-1-thread-1 : 16
        pool-1-thread-1 : 21
        pool-1-thread-1 : 22
        pool-1-thread-1 : 23
        pool-1-thread-1 : 24
        pool-1-thread-1 : 25
        pool-1-thread-1 : 26
         */
    }

    private static void exExecutorServiceTwo() {

        //Custom scheduler with 2 threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Scheduler executorScheduler = Schedulers.from(executorService);

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(11,12,13,14,15,16)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(21,22,23,24,25,26)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();


        /*
            Pool one is using two threads for three different task.
            If you see the output the two different threads will be allotted two first to task.
            Whichever threads completes its task first will start executing the third task.
        */

        /* OUTPUT :
        pool-1-thread-1 : 1
        pool-1-thread-2 : 11
        pool-1-thread-2 : 12
        pool-1-thread-2 : 13
        pool-1-thread-2 : 14
        pool-1-thread-2 : 15
        pool-1-thread-2 : 16
        pool-1-thread-1 : 2
        pool-1-thread-1 : 3
        pool-1-thread-1 : 4
        pool-1-thread-1 : 5
        pool-1-thread-1 : 6
        pool-1-thread-1 : 21
        pool-1-thread-1 : 22
        pool-1-thread-1 : 23
        pool-1-thread-1 : 24
        pool-1-thread-1 : 25
        pool-1-thread-1 : 26

         */
    }

    private static void exExecutorServiceThree() {
        //Custom scheduler with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Scheduler executorScheduler = Schedulers.from(executorService);

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(11,12,13,14,15,16)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(21,22,23,24,25,26)
                .subscribeOn(executorScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();


        //Pool one is using three threads for three different task.

        /* OUTPUT :
        pool-1-thread-1 : 1
        pool-1-thread-3 : 21
        pool-1-thread-3 : 22
        pool-1-thread-3 : 23
        pool-1-thread-3 : 24
        pool-1-thread-3 : 25
        pool-1-thread-3 : 26
        pool-1-thread-2 : 11
        pool-1-thread-1 : 2
        pool-1-thread-2 : 12
        pool-1-thread-1 : 3
        pool-1-thread-2 : 13
        pool-1-thread-2 : 14
        pool-1-thread-1 : 4
        pool-1-thread-2 : 15
        pool-1-thread-1 : 5
        pool-1-thread-1 : 6
        pool-1-thread-2 : 16

         */
    }

    private static void exExecutorServiceFour() {

        //Multiple Custom Scheduler with same executor

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Scheduler firstScheduler = Schedulers.from(executorService);
        Scheduler secondScheduler = Schedulers.from(executorService);

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(firstScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(11,12,13,14,15,16)
                .subscribeOn(secondScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        /*
            Since both the scheduler are created from the same executor obviously both will share the same
            thread pool.
        */

        /* OUTPUT :
        pool-1-thread-1 : 1
        pool-1-thread-1 : 2
        pool-1-thread-1 : 3
        pool-1-thread-1 : 4
        pool-1-thread-1 : 5
        pool-1-thread-1 : 6
        pool-1-thread-1 : 11
        pool-1-thread-1 : 12
        pool-1-thread-1 : 13
        pool-1-thread-1 : 14
        pool-1-thread-1 : 15
        pool-1-thread-1 : 16

         */
    }

    private static void exExecutorServiceFive() {

        //Multiple Custom Scheduler with different executor

        ExecutorService firstExecutorService = Executors.newFixedThreadPool(1);
        ExecutorService secondExecutorService = Executors.newFixedThreadPool(1);

        Scheduler firstScheduler = Schedulers.from(firstExecutorService);
        Scheduler secondScheduler = Schedulers.from(secondExecutorService);

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(firstScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        Observable.just(11,12,13,14,15,16)
                .subscribeOn(secondScheduler)
                .doOnNext(result -> System.out.println(Thread.currentThread().getName()+ " : " + result))
                .subscribe();

        /*
            Since both the scheduler are created from two different executor obviously both will share
            different thread pool. You can see the output pool name changes.
        */

        /* OUTPUT :
        pool-2-thread-1 : 11
        pool-1-thread-1 : 1
        pool-2-thread-1 : 12
        pool-1-thread-1 : 2
        pool-2-thread-1 : 13
        pool-1-thread-1 : 3
        pool-2-thread-1 : 14
        pool-1-thread-1 : 4
        pool-2-thread-1 : 15
        pool-1-thread-1 : 5
        pool-2-thread-1 : 16
        pool-1-thread-1 : 6
         */
    }

}