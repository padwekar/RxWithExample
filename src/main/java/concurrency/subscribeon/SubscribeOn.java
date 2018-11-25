package concurrency.subscribeon;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class SubscribeOn {
    /*
       SubscribeOn : Tells the upstream source to which scheduler is to be used for emissions.
    */

    public static void main(String... args) {
        exSubscribeOnEight();
    }

    //Sample Example
    private static void exSubscribeOnOne() {
        /*
            SubscribeOn : If your source upstream is not already attached to any scheduler it will use
            the scheduler specified by you.
        */

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.computation())
                .subscribe(result -> System.out.println(Thread.currentThread().getName() + " : "+ result));

        Take.sleep(1000);

        /*
            The emissions are taking place on a computation Thread pool.
         */

        /* OUTPUT :
            RxComputationThreadPool-1 : 1
            RxComputationThreadPool-1 : 2
            RxComputationThreadPool-1 : 3
            RxComputationThreadPool-1 : 4
            RxComputationThreadPool-1 : 5
            RxComputationThreadPool-1 : 6
         */
    }

    //Uses Same Thread and Same Scheduler
    private static void exSubscribeOnTwo() {
        /*
            SubscribeOn : The position of subscribe on doesn't matter, but it is recommended to use it just after the
            source observer.

            If there are two different observer for the same observer.
        */

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6)
                                                .subscribeOn(Schedulers.computation())
                                                .publish()
                                                .autoConnect(2);

        integerObservable.subscribe(result -> System.out.println("FIRST "+ Thread.currentThread().getName() + " : "+ result));

        integerObservable.subscribe(result -> System.out.println("SECOND "+ Thread.currentThread().getName() + " : "+ result));


        Take.sleep(10000);


        /*


         If you want all the observer to use the same thread.
         Make it a connectable observer.
         If you see the output you will find,
         Both using the same thread from the same thread pool.

         */

        /* OUTPUT :

        FIRST RxComputationThreadPool-1 : 1
        SECOND RxComputationThreadPool-1 : 1
        FIRST RxComputationThreadPool-1 : 2
        SECOND RxComputationThreadPool-1 : 2
        FIRST RxComputationThreadPool-1 : 3
        SECOND RxComputationThreadPool-1 : 3
        FIRST RxComputationThreadPool-1 : 4
        SECOND RxComputationThreadPool-1 : 4
        FIRST RxComputationThreadPool-1 : 5
        SECOND RxComputationThreadPool-1 : 5
        FIRST RxComputationThreadPool-1 : 6
        SECOND RxComputationThreadPool-1 : 6

         */
    }

    //Uses different thread from same scheduler
    private static void exSubscribeOnThree() {
        /*
            SubscribeOn : If your source upstream is not already attached to any scheduler it will use
            the scheduler specified by you.
        */

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.computation());


        integerObservable.subscribe(result -> System.out.println("1st "+ Thread.currentThread().getName() + " : "+ result));

        integerObservable.subscribe(result -> System.out.println("2nd "+ Thread.currentThread().getName() + " : "+ result));

        Take.sleep(1000);

        /*
            With two observer they are getting two different thread from the same thread pool.
         */

        /* OUTPUT :
           2nd RxComputationThreadPool-2 : 1
            1st RxComputationThreadPool-1 : 1
            2nd RxComputationThreadPool-2 : 2
            1st RxComputationThreadPool-1 : 2
            2nd RxComputationThreadPool-2 : 3
            1st RxComputationThreadPool-1 : 3
            2nd RxComputationThreadPool-2 : 4
            1st RxComputationThreadPool-1 : 4
            2nd RxComputationThreadPool-2 : 5
            1st RxComputationThreadPool-1 : 5
            2nd RxComputationThreadPool-2 : 6
            1st RxComputationThreadPool-1 : 6
         */
    }

    //No effect scheduler
    private static void exSubscribeOnFour() {

        /*
            Some Observable will by default a specific thread scheduler by default and using subscribeOn will have
            no effect on it.
         */

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(result -> System.out.println(Thread.currentThread().getName() + " : " + result));

        Take.sleep(10000);

        /*
            You can see the observable interval by default run on Computation Thread.
            Now switch to next example for next tip.
         */

        /*
            Output :

            RxComputationThreadPool-1 : 0
            RxComputationThreadPool-1 : 1
            RxComputationThreadPool-1 : 2
            RxComputationThreadPool-1 : 3
            RxComputationThreadPool-1 : 4
            RxComputationThreadPool-1 : 5
            RxComputationThreadPool-1 : 6
            RxComputationThreadPool-1 : 7
            RxComputationThreadPool-1 : 8
            RxComputationThreadPool-1 : 9
         */
    }

    private static void exSubscribeOnFive() {

        /*
            If we specify subscribeOn for the 'interval', It has no effect on it.
         */

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(result -> System.out.println(Thread.currentThread().getName() + " : " + result));

        Take.sleep(10000);

        /*
            You can see the observable interval by default run on Computation Thread.
            And it has no effect of subscribeOn on it.
            Switch to example 5 now.
         */

           /*
            Output :

            RxComputationThreadPool-1 : 0
            RxComputationThreadPool-1 : 1
            RxComputationThreadPool-1 : 2
            RxComputationThreadPool-1 : 3
            RxComputationThreadPool-1 : 4
            RxComputationThreadPool-1 : 5
            RxComputationThreadPool-1 : 6
            RxComputationThreadPool-1 : 7
            RxComputationThreadPool-1 : 8
            RxComputationThreadPool-1 : 9
         */
    }

    private static void exSubscribeOnSix() {

        /*
            To force such observable which by default uses some specific scheduler to use the scheduler provided.
            Rx Java provides one more parameter in the constructor of such Observable. See the below example for
            clarification.
         */

        Observable.interval(1, TimeUnit.SECONDS,Schedulers.newThread())
                .subscribe(result -> System.out.println(Thread.currentThread().getName() + " : " + result));

        Take.sleep(10000);

        /*
            Now we have successfully force the 'interval' observer to use Scheduler.newThread() instead of
            its default computation thread.
         */

           /*
            Output :

            RxNewThreadScheduler-1 : 0
            RxNewThreadScheduler-1 : 1
            RxNewThreadScheduler-1 : 2
            RxNewThreadScheduler-1 : 3
            RxNewThreadScheduler-1 : 4
            RxNewThreadScheduler-1 : 5
            RxNewThreadScheduler-1 : 6
            RxNewThreadScheduler-1 : 7
            RxNewThreadScheduler-1 : 8
            RxNewThreadScheduler-1 : 9
         */
    }

    //Multiple Subscribe on
    private static void exSubscribeOnSeven() {

        /*
            What if you have multiple subscribeOn ;). In that case your observable will use the
            top most subscribeOn scheduler and all the rest will be ignored.
         */

        Observable.just(1,2,3,4,5)
                .subscribe(result -> System.out.println(Thread.currentThread().getName() +" : "+ result));


        /*
            The given observable by default run on main thread. Let see what happen when we add subscribeOn to it.
            Ref 'exSubscribeOnSeven()'.
         */
        /*
            Output :
            main : 1
            main : 2
            main : 3
            main : 4
            main : 5
         */
    }

    private static void exSubscribeOnEight() {

        /*
            What if you have multiple subscribeOn ;). In that case your observable will use the
            top most subscribeOn scheduler and all the rest will be ignored.
         */

        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .subscribe(result -> System.out.println(Thread.currentThread().getName() + " : "+ result));


        Take.sleep(10000);
        /*
            Now the above observable is running on Computation Scheduler and New Thread Scheduler has been ignored.
            So the nearest one to the source observer will be picked and rest will be ignored.
         */
        /*
            Output :
            RxComputationThreadPool-1 : 1
            RxComputationThreadPool-1 : 2
            RxComputationThreadPool-1 : 3
            RxComputationThreadPool-1 : 4
            RxComputationThreadPool-1 : 5
            RxComputationThreadPool-1 : 6
         */
    }

}