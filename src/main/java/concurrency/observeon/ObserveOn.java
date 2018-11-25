package concurrency.observeon;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.List;

public class ObserveOn {
    /*
       ObserveOn : Using observeOn you can interpret emission and switch them to another scheduler.
    */
    public static void main(String... args) {
        exObserveOnOne();
    }

    private static void exObserveOnOne() {
        /*
            ObserveOn : When you don't use observeOn all the emission happens on
            the default or specified scheduler till the end of the observable chain.
            If you want to distribute operation to different thread scheduler, this can
            be achieved using ObserveOn.
        */

        Observable.just(1,2,3)
                .subscribeOn(Schedulers.computation())
                .map(num -> heavyMathematicalOperation(num))
                .doOnNext(result -> System.out.println("Operation 1st "+ Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .toList()
                .map(list -> save(list))
                .doOnSuccess(result -> System.out.println("Operation 2nd "+ Thread.currentThread().getName()))
                .observeOn(Schedulers.newThread())
                .doOnSuccess(result -> System.out.println("Operation 3rd "+ Thread.currentThread().getName()))
                .subscribe(r ->System.out.println(r));

        Take.sleep(5000);

        /* OUTPUT :
            Operation 1st RxComputationThreadPool-1
            Operation 1st RxComputationThreadPool-1
            Operation 1st RxComputationThreadPool-1
            Operation 2nd RxCachedThreadScheduler-1
            Operation 3rd RxNewThreadScheduler-1
            true
         */
    }

    private static Boolean save(List<Integer> list) {
        return true;
    }


    private static Integer heavyMathematicalOperation(Integer num) {
        Take.sleep(1000);
        return num;
    }

}