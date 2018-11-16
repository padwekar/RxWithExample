package observable.combining;

import io.reactivex.Observable;
import operator.suppressing.Take;

import java.util.concurrent.TimeUnit;

public class WithCombineFrom {
    /*
       WithCombineFrom : It will map each emission from one observable with the latest emission of another observable
    */
    public static void main(String... args) {
        exCombineFromOne();
    }

    private static void exCombineFromOne() {
        /*
            Here WithCombineFrom maps every emission of longObservable with the latest emission of short observable.
        */

        Observable<String> longObservable = Observable.interval(1, TimeUnit.SECONDS).map(num -> (num+1)+"s");

        Observable<String> shortObservable = Observable.interval(300, TimeUnit.MILLISECONDS).map(num -> ((num+1)*300)+"ms");

        longObservable.withLatestFrom(shortObservable,(l,s) -> l+":"+s).subscribe(System.out::println);


        Take.sleep(3100);

    }

}