package concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

public class Concurrency {
    /*
       Concurrency Example: To illustrate concurrency, we will stimulate a long running task in the method called 'intenseTask',
       This task will make the thread sleep for few seconds.
    */
    public static void main(String... args) {
        exConcurrencyOne();
    }

    private static void exConcurrencyOne() {
        /*
            Here both the observer are running on the main thread. Once first observable
            completes it emission another observable starts its emission.
        */


        Observable.just("Alpha","Beta","Gamma","Sudama","Mama")
                .map(Concurrency::intenseCalculation)
                .subscribe(System.out::println);


        Observable.range(1,10)
                .map(Concurrency::intenseCalculation)
                .subscribe(System.out::println);

        Take.sleep(20000);

        /* OUTPUT :
            Alpha
            Beta
            Gamma
            Sudama
            Mama
            1
            2
            3
            4
            5
            6
            7
            8
            9
            10
         */
    }

    private static void exConcurrencyTwo() {
        /*
            Since both the observable are independent of each other we can start both simultaneously will make
            the program more efficient.
        */

        Observable.just("Alpha","Beta","Gamma","Sudama","Mama")
                .map(Concurrency::intenseCalculation)
                .subscribeOn(Schedulers.computation())
                .subscribe(System.out::println);


        Observable.range(1,10)
                .map(Concurrency::intenseCalculation)
                .subscribeOn(Schedulers.computation())
                .subscribe(System.out::println);

        Take.sleep(20000);


        /* OUTPUT :
            Alpha
            1
            Beta
            2
            Gamma
            3
            Sudama
            4
            Mama
            5
            6
         */
    }

    private static void exConcurrencyThree() {
        /*
            Even the operators works so well in concurrency that you can observer multiple observable operating on different
            Thread into one.
         */
    }

        private static <T> T intenseCalculation(T value){
        Take.sleep(3000);
        return value;
    }

}