package observable.combining;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Ambiguous {
    /*
       Ambiguous : 'amb' stand for ambiguous will take two observable and considers the one which emits first and disposes the another one.
    */
    public static void main(String... args) {
        exAmbiguousOne();
    }

    private static void exAmbiguousOne() {
        /*
            Here Ambiguous will only pick the emissions from the observable which has emitted first
        */

        Observable<String> longObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(result -> "Via Long Observable :"+(result+1));

        Observable<String> shortObservable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(result -> "Via short Observable :"+(result + 1) * 300);


        Observable.amb(Arrays.asList(longObservable,shortObservable))
                .subscribe(System.out::println);

        Take.sleep(1000);

    }


}