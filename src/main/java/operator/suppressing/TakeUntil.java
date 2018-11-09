package operator.suppressing;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class TakeUntil {
    /*
       TakeUntil : it will take the emission from one observable until another observable emits.
    */
    public static void main(String... args) {
        TakeUntilOne();
    }

    private static void TakeUntilOne() {
        /*
            Here TakeUntil
        */

        Observable<Long> longObservable = Observable.interval(3, TimeUnit.SECONDS);

        Observable.interval(1,TimeUnit.SECONDS).takeUntil(longObservable).subscribe( x -> System.out.println("Recieved "+ x),Throwable::printStackTrace,()-> System.out.println("Done"));

        Take.sleep(5000);
    }

}