package concurrency.unsubscribeon;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class UnSubscribeOn {
    /*
       UnSubscribeOn : Can be handy if you want to dispose the observable whenever you no longer needs it and that disposing operation
       you want to perform on another thread. Sometimes disposing can be very expensive depending upon the source of observable you have,
       if your observable is emitting data from JDBC Database calling dispose will require releasing of the other resources
       used by JDBC and that might make the main scheduler busy.
    */

    public static void main(String... args) {
        exUnSubscribeOnTwo();
    }

    private static void exUnSubscribeOnOne() {
        /*
            UnSubscribeOn : To avoid the main thread from getting busy after calling the dispose
            rxjava let's you perform the Disposal on another thread.
        */

        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing "+ Thread.currentThread().getName()))
                .subscribe(data -> System.out.println("Received "+data));

        Take.sleep(3000);

        disposable.dispose();

        Take.sleep(3000);

        //Here we are performing the disposal on the main thread.
        /* OUTPUT :
            Received 0
            Received 1
            Received 2
            Disposing main

         */
    }

    private static Integer heavyMathematicalOperation(Integer num) {
        Take.sleep(1000);
        return num;
    }

    private static void exUnSubscribeOnTwo() {
        /*
            UnSubscribeOn :  Here we will use unSubscribe for disposal out of main thread
        */

        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing "+ Thread.currentThread().getName()))
                .unsubscribeOn(Schedulers.computation())
                .subscribe(data -> System.out.println("Received "+data));

        Take.sleep(3000);

        disposable.dispose();

        Take.sleep(3000);

        /* OUTPUT :

            Received 0
            Received 1
            Received 2
            Disposing RxComputationThreadPool-2

         */
    }

}