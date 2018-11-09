package operator.suppressing;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SkipUntil {
    /*
       SkipUntil : This operator will skip the emission until a specific emission occur.
    */
    public static void main(String... args) {
        SkipUntilOne();
    }

    private static void SkipUntilOne() {
        /*
            Here SkipUntil : Here the the .//2 observable's emission will be skipped until the first observable starts emitting
        */

        //1
        Observable<Long> firstObservable = Observable.interval(4, TimeUnit.SECONDS).doOnSubscribe(x ->
                System.out.println("I am subscribed")
        );

        //2
        Observable.interval(1,TimeUnit.SECONDS).skipUntil(firstObservable).subscribe( x -> System.out.println("Recieved "+ x),Throwable::printStackTrace,()-> System.out.println("Done"));

        Take.sleep(5000);

    }


}