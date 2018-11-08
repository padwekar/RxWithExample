package operator;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Interval {

    public static void main(String...args){
        intervalWithConnectableObserver();
    }

    /*
      Interval is a cold observer.
   */
    private static void intervalWithObserver(){
        Observable<Long> integerObservable = Observable.interval(1, TimeUnit.SECONDS);

        integerObservable.subscribe(lon -> System.out.println("First observer "+lon));

        sleep(5000);

        integerObservable.subscribe(lon -> System.out.println("Second observer "+lon));

        sleep(5000);
    }

    private static void intervalWithConnectableObserver(){
        ConnectableObservable<Long> integerObservable = Observable.interval(1, TimeUnit.SECONDS).publish();

        integerObservable.subscribe(lon -> System.out.println("First observer "+lon));

        integerObservable.connect();

        sleep(5000);

        integerObservable.subscribe(lon -> System.out.println("Second observer "+lon));

        sleep(5000);
    }

    private static void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
